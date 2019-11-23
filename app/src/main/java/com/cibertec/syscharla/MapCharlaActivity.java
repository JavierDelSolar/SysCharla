package com.cibertec.syscharla;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapCharlaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Variables objUtil= Variables.getInstance();
    double latitude = Double.parseDouble(objUtil.charla.getLatitud().toString());
    double longitude = Double.parseDouble(objUtil.charla.getLongitud().toString());

    private static final int REQUEST_PERMISO_LOCATION = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_charla);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 16f;
        float overlaySize = 100f;
        // Add a marker in Sydney and move the camera
        LatLng charlaMap = new LatLng(latitude, longitude);

        mMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(charlaMap, zoomLevel));
        mMap.addMarker(new MarkerOptions().position(charlaMap));

        enableMyLocation();
    }

    private void enableMyLocation(){
        if(isPermissionGranted()){
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(MapCharlaActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISO_LOCATION);
        }
    }

    private boolean isPermissionGranted(){
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISO_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                enableMyLocation();
            }
        }
    }
}
