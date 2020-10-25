package com.example.hw3_special_offer;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

class MyLocListener implements LocationListener {
    private static final String TAG = "MyLocListener";
    private MapsActivity mapsActivity;
    public MyLocListener(MapsActivity mapsActivity) {
        this.mapsActivity=mapsActivity;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: "+location);
        mapsActivity.updateLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
