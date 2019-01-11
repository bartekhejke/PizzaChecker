package bartekhejke.com.pizzachecker.WeatherFragment.data;

import org.json.JSONObject;

/**
 * Created by Bartek on 10.01.2019.
 */

public class Channel implements JSONPopulator {
    private Main main;
    private Weather weather;

    public Main getMain() {
        return main;
    }

    public Weather getWeather() {
        return weather;
    }

    @Override
    public void populate(JSONObject data) {
        main = new Main();
        main.populate(data.optJSONObject("main"));
        weather = new Weather();
        weather.populate(data);

    }
}
