package bartekhejke.com.pizzachecker.WeatherFragment.data;

import org.json.JSONObject;

/**
 * Created by Bartek on 09.01.2019.
 */

public class Main implements JSONPopulator {

    private int temperature;

    public int getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optInt("temp");
        System.out.println(temperature);

    }
}
