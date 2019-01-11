package bartekhejke.com.pizzachecker.WeatherFragment.service;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import bartekhejke.com.pizzachecker.WeatherFragment.data.Channel;

/**
 * Created by Bartek on 09.01.2019.
 */

public class OpenWeatherMapService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;
    private static final String API_KEYS = "49617c567ed7c4a91149e6a52eb1b58f";

    public OpenWeatherMapService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(String l){
        this.location = l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {


                String endpoint = String.format("http://api.openweathermap.org/data/2.5/weather?q=Brodnica,pl&appid=49617c567ed7c4a91149e6a52eb1b58f");

                try {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    System.out.println("W try od czytania linii&&&&&&&&&&");
                    return result.toString();

                } catch (Exception e) {
                    error = e;
                }

                return null;
            }
            @Override
            protected void onPostExecute(String s) {

                if (s == null && error != null){
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    System.out.println("POczątek try w postExecute");
                    JSONObject data = new JSONObject(s);

                    int cod = data.optInt("cod");
                    System.out.println(cod);
                    if (cod != 200){
                        callback.serviceFailure(new LocationWeatherException("No weather information found for "+location));
                        return;
                    }
                    Channel channel = new Channel();
                    channel.populate(data);
                    callback.serviceSuccess(channel);
                    System.out.println("callback success");
                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }

                super.onPostExecute(s);
            }
        }.execute(location);
    }

    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String message) {
            super(message);
        }
    }
}
