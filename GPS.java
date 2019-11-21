package com.example.ecosavertest;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;
import android.app.Service;
import android.os.IBinder;
import android.content.Intent;
import android.os.Bundle;
import java.lang.Math;

public class GPSLocation extends Service implements LocationListener {
        private final Context inContext;
        private double latitude;
        private double longitude;
        private Location Loc;
        public GPSLocation(Context mContext) {
            this.inContext = mContext;
           GPSCreate();
        }
        public double GpsGetLatitude(){
            return this.latitude;
        }
        public double GpsGetLongitude(){
            return this.longitude;
        }
        public double GpsDistanceTo(GPSLocation loc2){
            double deltaLong = loc2.longitude - this.longitude;
            double deltaLat = loc2.latitude - this.latitude;
            double a;
            double c;
            a = Math.pow(Math.sin(deltaLat/2),2) + Math.cos(this.latitude) * Math.cos(loc2.latitude) + Math.pow(Math.sin(deltaLong/2),2);
            c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            return 3963 * c;
        }
        private void GPSCreate() {
            LocationManager locMan = (LocationManager) inContext.getSystemService(Context.LOCATION_SERVICE);
            //NEED TO ADD ERROR CHECKING FOR PHONE PERMISSIONS
			locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            Loc = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            this.latitude = Loc.getLatitude();
           this.longitude = Loc.getLongitude();
        }

        //NOT NEEDED, BUT NEEDS TO BE INCLUDED FOR INTEGRATION w/ BUILT IN API TO WORK

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}


