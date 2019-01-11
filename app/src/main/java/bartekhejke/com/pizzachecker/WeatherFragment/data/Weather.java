package bartekhejke.com.pizzachecker.WeatherFragment.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Bartek on 09.01.2019.
 */

public class Weather implements JSONPopulator {

    private String description;
    private int id;

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    @Override
    public void populate(JSONObject data) {
        JSONArray array = data.optJSONArray("weather");
        JSONObject object = array.optJSONObject(0);

        description = object.optString("description");
        id = object.optInt("id");

        System.out.println(description);
        System.out.println(id);
    }
}
