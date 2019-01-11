package bartekhejke.com.pizzachecker.WeatherFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import bartekhejke.com.pizzachecker.R;
import bartekhejke.com.pizzachecker.WeatherFragment.data.Channel;
import bartekhejke.com.pizzachecker.WeatherFragment.service.OpenWeatherMapService;
import bartekhejke.com.pizzachecker.WeatherFragment.service.WeatherServiceCallback;

/**
 * Created by Bartek on 08.01.2019.
 */

public class WheatherFragment extends Fragment implements WeatherServiceCallback {

    private OpenWeatherMapService service;
    private TextView temperature;
    private TextView location;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        temperature = (TextView) view.findViewById(R.id.temperatureTextView);
        location = (TextView) view.findViewById(R.id.locationTextView);

        service = new OpenWeatherMapService(this);
        service.refreshWeather("Brodnica,pl");
        return view;
    }

    @Override
    public void serviceSuccess(Channel channel) {
        int temp = channel.getMain().getTemperature()-273;
        temperature.setText(temp+"\u00B0");

        location.setText(service.getLocation());

    }

    @Override
    public void serviceFailure(Exception exception) {
        Toast.makeText(getContext(),exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
