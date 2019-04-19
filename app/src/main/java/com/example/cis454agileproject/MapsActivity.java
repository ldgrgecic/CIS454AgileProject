package com.example.cis454agileproject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AppCompatActivity myActivity;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2002;
    private Marker serviceLocation = null;
    private final LatLng SYRACUSE_U_COORDS = new LatLng(43.03767, -76.13399);

    private String address = null;
    private LatLng serviceCoords = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        myActivity = this;

        this.setTitle("Please Select the Service Location");

        // Get the button, and set what happens when clicked
        Button btnConfirmLocation = findViewById(R.id.btn_map_confirm);
        btnConfirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // IF a pin has been placed (an address has been selected),
                // start a new intent that launches the CreateServiceActivity
                // bundle in the lat, lng, and address of pin location
                // ELSE notify the user they must select the service location
                if (serviceLocation == null || address == null) {
                    Toast.makeText(getApplicationContext(), "Please Drop a Pin at a Valid Service Location", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), CreateServiceActivity.class);
                    Bundle extras = new Bundle();

                    extras.putDouble("lat", serviceCoords.latitude);
                    extras.putDouble("lng", serviceCoords.longitude);
                    extras.putString("address", address);

                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // First check if the API version being used supports location services
        // API must be >= 25
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            // TODO: check if this works and change functionality
            // next check if the user has granted the application permission to use their FINE location
            int hasFineLocationPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (hasFineLocationPermission == PackageManager.PERMISSION_DENIED) {
                // If they have not allowed location services, place a marker in syracuse University
                mMap.addMarker(new MarkerOptions().position(SYRACUSE_U_COORDS).title("Marker in Syracuse University")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            } else {
                // Enable map features
                mMap.setMyLocationEnabled(true);
            }
        }
        // Enable map features
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);
        // TODO: change camera view to user's current location
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(SYRACUSE_U_COORDS));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(SYRACUSE_U_COORDS, 12.0f));

        // set a map click listener
        // When the user clicks on the map, get the location of that point
        // Get the address from the lat/lng and if its not valid, display an error
        // If this is the first time the user clicks the map (i.e. no pin has been dropped),
        // create a new pin at the point location
        // otherwise, set the position of the already created pin to the new point location
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                serviceCoords = new LatLng(point.latitude, point.longitude);
                address = getAddress(serviceCoords.latitude, serviceCoords.longitude);
                if (address == null) {
                    Toast.makeText(getApplicationContext(), "Please select a valid address", Toast.LENGTH_SHORT).show();
                } else {
                    if (serviceLocation == null) {
                        MarkerOptions options = new MarkerOptions().position(serviceCoords).title("Location of Service");
                        serviceLocation = mMap.addMarker(options);
                        serviceLocation.isDraggable();
                    } else {
                        serviceLocation.setPosition(serviceCoords);
                    }
                    Toast.makeText(getApplicationContext(), address, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Returns the address given a latitude and longitude using GeoCoder
    // If any error occurs or an address is not valid, it returns null
    public String getAddress(double lat, double lng) {
        //convert GPS info into address
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
        } catch (IOException ioException) {
            // Network issue, return null
            return null;
        }
        if (addresses == null || addresses.size() == 0) {
            // No addresses found, return null
            return null;
        }

        // Extract the actual address, and return it
        String addr = addresses.get(0).getAddressLine(0);
        return addr;
    }
}
