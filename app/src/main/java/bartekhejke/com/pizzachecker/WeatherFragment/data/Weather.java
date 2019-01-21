package bartekhejke.com.pizzachecker.WeatherFragment.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Bartek on 09.01.2019.
 */

public class Weather implements JSONPopulator {


    private String icon;

    public String getIcon() {
        return icon;
    }

    @Override
    public void populate(JSONObject data) {
        JSONArray array = data.optJSONArray("weather");
        JSONObject object = array.optJSONObject(0);

        icon = object.optString("icon");

    }
}
