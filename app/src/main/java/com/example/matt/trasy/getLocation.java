package com.example.matt.trasy;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.*;
import android.util.Log;

public  class getLocation implements LocationListener{
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;


    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }


    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");

    }


    @Override
    public void onLocationChanged(Location location) {

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
    }


}
