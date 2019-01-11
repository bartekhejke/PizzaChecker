package bartekhejke.com.pizzachecker.WeatherFragment.service;

import bartekhejke.com.pizzachecker.WeatherFragment.data.Channel;

/**
 * Created by Bartek on 09.01.2019.
 */

public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
