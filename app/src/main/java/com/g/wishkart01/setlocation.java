package com.g.wishkart01;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class setlocation extends FragmentActivity implements OnMapReadyCallback, LocationListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {
    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private GoogleApiClient client;
    private double latitude;
    private double longitude;
    private EditText locationEditText;
    private static final String TAG = "MainActivity";
    private Geocoder geocoder;
    private List<Address> addresses;
    private Location location;
    private Marker marker;
    private Button setLocationButton;
    private static final String KEY_Name = "NAME";
    private static final String KEY_EMAIL = "EMAIL";
    private static final String KEY_PASSWORD = "PASSWORD";
    private static final String KEY_LOCATION = "LOCATION";
    public static final String EXTRA_NAME="com.g.wishkart01.EXTRA_NAME";
    public static final String EXTRA_LOCATION="com.g.wishkart01.EXTRA_LOCATION";
    public static final String EXTRA_EMAIL="com.g.wishkart01.EXTRA_EMAIL";
    public static final String EXTRA_PASSWORD="com.g.wishkart01.EXTRA_PASSWORD";
    public static final String EXTRA_PHONE="com.g.wishkart01.EXTRA_PHONE";
    public static final String EXTRA_PROFESSION="com.g.wishkart01.EXTRA_PROFESSION";
    private FirebaseFirestore firestoreRef = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    String address;
    String profession;
    String email;
    String final_location;
    String name_str;
    String phone_str;
    String email_str;
    String password_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setlocation);
        Intent inte = getIntent();
        profession = inte.getStringExtra(phone_for_otp.EXTRA_PROFESSION);
        name_str = inte.getStringExtra(phone_for_otp.EXTRA_NAME);
        phone_str = inte.getStringExtra(phone_for_otp.EXTRA_PHONE);
        email_str = inte.getStringExtra(phone_for_otp.EXTRA_EMAIL);
        password_str = inte.getStringExtra(phone_for_otp.EXTRA_PASSWORD);
        email=inte.getStringExtra(setlocation.EXTRA_EMAIL);
        locationEditText = (EditText) findViewById(R.id.locationname_editText);
        locationEditText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        geocoder = new Geocoder(this, Locale.getDefault());
        setLocationButton=findViewById(R.id.loc_button);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
        setLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final_location=addresses.get(0).getAddressLine(0);
                if(profession.equals("Customer")){

                    String user_id=mAuth.getCurrentUser().getUid();
                    Map<String,Object> customer_map=new HashMap<>();
                    customer_map.put(KEY_Name,name_str);
                    customer_map.put(KEY_EMAIL,email_str);
                    customer_map.put(KEY_PASSWORD,password_str);
                    customer_map.put(KEY_LOCATION,final_location);
                    firestoreRef.collection("Customer").document(user_id).set(customer_map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(setlocation.this,"Registered Succesfully",Toast.LENGTH_LONG).show();

                                    Intent bottom_nav=new Intent(setlocation.this,CustomerHomeActivity.class);
                                    startActivity(bottom_nav);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(setlocation.this,"Registered Failed",Toast.LENGTH_LONG).show();
                                    Log.d(TAG, e.toString());

                                }
                            });
                }
                else if(profession.equals("Delivery Boy")){
                    Map<String,Object> deliveryboy_map=new HashMap<>();
                    deliveryboy_map.put(KEY_Name,name_str);
                    deliveryboy_map.put(KEY_EMAIL,email_str);
                    deliveryboy_map.put(KEY_PASSWORD,password_str);
                    deliveryboy_map.put(KEY_LOCATION,final_location);
                    firestoreRef.collection("Delivery Boys").document(email_str).set(deliveryboy_map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(setlocation.this,"Registered Succesfully",Toast.LENGTH_LONG).show();
                                    Intent bottom_nav=new Intent(setlocation.this,bottom_navigation.class);
                                    bottom_nav.putExtra(EXTRA_LOCATION,final_location);
                                    startActivity(bottom_nav);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(setlocation.this,"Registered Failed",Toast.LENGTH_LONG).show();
                                    Log.d(TAG, e.toString());

                                }
                            });
                }
                else {
                    Map<String,Object> shopowner_map=new HashMap<>();
                    shopowner_map.put(KEY_Name,name_str);
                    shopowner_map.put(KEY_EMAIL,email_str);
                    shopowner_map.put(KEY_PASSWORD,password_str);
                    shopowner_map.put(KEY_LOCATION,final_location);
                    firestoreRef.collection("Shop Owners").document(email_str).set(shopowner_map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(setlocation.this,"Registered Succesfully",Toast.LENGTH_LONG).show();
                                    Intent bottom_nav=new Intent(setlocation.this,bottom_navigation.class);
                                    bottom_nav.putExtra(EXTRA_LOCATION,final_location);
                                    startActivity(bottom_nav);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(setlocation.this,"Registered Failed",Toast.LENGTH_LONG).show();
                                    Log.d(TAG, e.toString());

                                }
                            });

                }


            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        highlight();
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, longitude);
        marker=mMap.addMarker(new MarkerOptions().position(sydney).title("Your Location"));
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location1));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 20.4f));

        try {
            locationEditText.setText(addresses.get(0).getAddressLine(0));
            address = addresses.get(0).getAddressLine(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();

                // highlight();
                try {

                    Log.d("zoom", String.valueOf(mMap.getCameraPosition().zoom));
                    marker=mMap.addMarker(new MarkerOptions().position(latLng).title("Location"));
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location1));
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    String locality = addresses.get(0).getLocality();
                    String postalcode = addresses.get(0).getPostalCode();
                    if (locality != null && postalcode != null) {
                        locationEditText.setText(addresses.get(0).getAddressLine(0));
                    } else if (locality != null) {
                        locationEditText.setText(addresses.get(0).getPostalCode());
                    } else {
                        locationEditText.setText(null);
                        locationEditText.setHint("Location not known");
                    }

                } catch (IOException e) {
                    locationEditText.setText(null);
                    locationEditText.setHint("Location not known");
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e) {
                    locationEditText.setText(null);
                    e.printStackTrace();
                    locationEditText.setHint("Location not known");

                }
            }
        });

        //  highlight();


    }






    public void highlight() {
        LatLng loc = new LatLng(latitude, longitude);
        marker=mMap.addMarker(new MarkerOptions().position(loc).title("Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location1));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return false;
            }
        });




    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Toast.makeText(this, "OnConnected", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Suspended", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }


}
