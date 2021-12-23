package com.example.realproject_1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import java.util.List;

public class Map extends AppCompatActivity {

    TextView info;
    Button btn_animal_hospital, btn_animal_for_walk, back_map_home;
    Geocoder coder;
    double latitude, longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
       setContentView(R.layout.map);
        coder = new Geocoder(this);
        info = (TextView) findViewById(R.id.info);
        btn_animal_hospital= (Button)findViewById(R.id.btn_animal_hospital);
        btn_animal_for_walk= (Button)findViewById(R.id.btn_animal_for_walk);
        back_map_home=(Button)findViewById(R.id.back_map_home);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                List<Address> list =null;

                latitude = location.getLatitude();
                longitude = location.getLongitude();


                try{
                    list = coder.getFromLocation(latitude,longitude,5);
                }catch (IOException e){
                    e.printStackTrace();
                }
                if(list != null){
                    if(list.size()==0){

                    }
                    else {

                    }
                }
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
        };
        btn_animal_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(String.format("geo:%f, %f?z=15&q=동물병원", latitude, longitude));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        btn_animal_for_walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(String.format("geo:%f, %f?z=15&q=공원", latitude, longitude));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        back_map_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RealHome.class);
                startActivity(intent);
            }
        });


        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);



    }




}