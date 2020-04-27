package com.example.mobiilitietoliikennelabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class lab1_1 extends AppCompatActivity {

    FusedLocationProviderClient mFusedLocationClient;
    double[] currentGPS = new double[2];
    String gpsChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1_1);
        Intent intent = getIntent();

        gpsChoice = intent.getStringExtra("mode");
        final TextView geoText = findViewById(R.id.gpsGeocoder);
        if(gpsChoice.equals("lab1_1")){
            geoText.setVisibility(View.INVISIBLE);
        }
        if(gpsChoice.equals("lab2_1")){
            geoText.setVisibility(View.VISIBLE);
        }
        requestNewLocationData();



        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation().addOnCompleteListener(
                new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            TextView textView = findViewById(R.id.gpsTextNewApi);
                            //currentGPS[0] =
                            //currentGPS[1] = location.getLongitude();
                            String locText = location.getLatitude()+" "+location.getLongitude();
                            textView.setText(locText);
                            //TextView geoText = findViewById(R.id.gpsGeocoder);
                            if(gpsChoice.equals("lab1_1")){
                            //    geoText.setVisibility(View.INVISIBLE);
                            }else{
                             //   geoText.setVisibility(View.VISIBLE);
                                Address address = getAddressFromLocation(location.getLatitude(),location.getLongitude());
                                String addString = address.getPostalCode() + " "+ address.getCountryName();
                                geoText.setText(addString);
                            }
                        }
                    }
                }
        );




    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            TextView textView = findViewById(R.id.gpsTextNewApi);
            String locText = mLastLocation.getLatitude()+" "+mLastLocation.getLongitude();
            textView.setText(locText);
        }
    };

    public Address getAddressFromLocation(final double latitude, final double longitude) {

        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        Address address = new Address(Locale.ENGLISH);

        try {
            List<Address> addressList = geocoder.getFromLocation(
                    latitude, longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                address = addressList.get(0);

            }
        } catch (IOException e) {
            Log.e("Bugsy", "Unable connect to Geocoder", e);
        }
        return address;


    }
}
