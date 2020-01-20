package com.shegi.surveyour;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap map;
    private static final int LOCATION_REQUEST = 101;
    private GoogleMap mMap;
    Button pilihlokasi;
    LocationManager locationManager;
    private FusedLocationProviderClient client;
    double lat= -5.135399;
    double lon = 119.423790;
    TextView latitudee,longtitudee;
//    double latt = Double.valueOf(String.valueOf(latitudee));
//    double lonn = Double.valueOf(String.valueOf(longtitudee));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        client = LocationServices.getFusedLocationProviderClient(this);
        pilihlokasi = findViewById(R.id.pilihlokasi);

        latitudee = findViewById(R.id.latitude);
        longtitudee = findViewById(R.id.longitude);




        Toast.makeText(MapsActivity.this,"Silahkan klik tombol pojok kanan paling atas untuk mendapatkan lokasi",Toast.LENGTH_SHORT).show();

        LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        pilihlokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MapsActivity.this);
                dialog.setTitle("Posisi Telah Di Temukan");
                dialog.setMessage("Klik Ya Untuk Melanjutkan");
                dialog.setCancelable(true);
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MapsActivity.this,surveyorumum.class);
                        String latitudeee = latitudee.getText().toString();
                        String longtitudeee = longtitudee.getText().toString();
                        intent.putExtra("Latitude",latitudeee);
                        intent.putExtra("Longtitude",longtitudeee);
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();




                if (ActivityCompat.checkSelfPermission(MapsActivity.this, ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                        (MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                client.getLastLocation().addOnSuccessListener(MapsActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null){
                            onLocationChanged(location);

                        }
                    }
                });

            }
        });


    }


    public void onLocationChanged(Location location){
        double longtitude = location.getLongitude();
        double latitude = location.getLatitude();
        String finallong = new Double(longtitude).toString();
        String finallat = new Double(latitude).toString();
        latitudee.setText(finallat);
        longtitudee.setText(finallong);

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    public void onProviderEnabled(String provider) {

    }


    public void onProviderDisabled(String provider) {

    }





    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        LatLng latLng = new LatLng(lat,lon);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));


        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        switch (requestCode) {
            case LOCATION_REQUEST:
                if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
                break;
        }


    }
}