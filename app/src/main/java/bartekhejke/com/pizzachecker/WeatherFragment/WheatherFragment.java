package bartekhejke.com.pizzachecker.WeatherFragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import bartekhejke.com.pizzachecker.R;
import bartekhejke.com.pizzachecker.WeatherFragment.data.Channel;
import bartekhejke.com.pizzachecker.WeatherFragment.service.OpenWeatherMapService;
import bartekhejke.com.pizzachecker.WeatherFragment.service.WeatherServiceCallback;


/**
 * Created by Bartek on 08.01.2019.
 */

public class WheatherFragment extends Fragment implements WeatherServiceCallback {

    private static final int REQUEST_CODE = 1000;
    private OpenWeatherMapService service;
    private TextView temperature;
    private TextView location;
    private ImageView weatherIcon;
    private int temp;
    private int resourceId;
    private String loc;
    private ProgressDialog dialog;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {

                    }
                }
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        temperature = (TextView) view.findViewById(R.id.temperatureTextView);
        location = (TextView) view.findViewById(R.id.locationTextView);
        weatherIcon = (ImageView) view.findViewById(R.id.weatherIcon);

        //font
        Typeface MainFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ITCKRIST.ttf");
        temperature.setTypeface(MainFont);
        location.setTypeface(MainFont);

        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        service = new OpenWeatherMapService(this);

        if (savedInstanceState != null){
            dialog.hide();
            temperature.setText(savedInstanceState.getInt("temperature")+"\u00B0");
            location.setText(savedInstanceState.getString("location"));
            weatherIcon.setImageResource(savedInstanceState.getInt("resourceId"));
        } else {
            dialog.show();
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {
            //if permission is granted
            buildLocationRequest();
            buildLocationCallBack();

            //Create FusedProviderClient
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                return view;
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

        }
        return view;
    }

    private void buildLocationCallBack() {

        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (Location locationData: locationResult.getLocations()){

                    service.setLatitude(locationData.getLatitude());
                    service.setLongitude(locationData.getLongitude());
                    service.refreshWeather();
                }
            }
        };
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10);
    }


    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        this.temp = channel.getMain().getTemperature()-273;
        this.resourceId = getResources().getIdentifier("drawable/icon_"+ channel.getWeather().getIcon(), "drawable", getActivity().getPackageName());
        this.loc = channel.getLocation();

        temperature.setText(temp+"\u00B0"+"C");
        location.setText(loc);
        weatherIcon.setImageResource(resourceId);
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(getContext(),exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("temperature",temp);
        outState.putInt("resourceId", resourceId);
        outState.putString("location", loc);

    }
}
