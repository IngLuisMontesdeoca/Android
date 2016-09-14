package io.github.ingluismontesdeoca.fragment.admin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

import io.github.ingluismontesdeoca.fragment.R;
import io.github.ingluismontesdeoca.fragment.lib.map.MapLib;
import io.github.ingluismontesdeoca.fragment.lib.map.Properties;

public class Map extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private MapLib mapLib;
    private SupportMapFragment mapFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        loadMap();
        Debug.mkToast("onCreate - Map",Map.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Debug.mkToast("onStart - Map",Map.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
        Debug.mkToast("onStop - Map",Map.this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (checkPermission())
            return;
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        //getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Debug.mkToast("onConnectionSuspended - Map",Map.this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        this.mapLib = new MapLib(mMap);
        this.getLocationServices();
        Debug.mkToast("onMapReady - Map",Map.this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Debug.mkToast("Error onConnectionFailed - Map",Map.this);
    }


    private void getLocation(){
        if( mLastLocation != null) {
            Toast.makeText(Map.this, mLastLocation.getLongitude()+","+mLastLocation.getLatitude() , Toast.LENGTH_SHORT).show();
            this.makeLocation();
        }else
            Toast.makeText(Map.this, "UNABLE TO GET LOCATION", Toast.LENGTH_SHORT).show();
        Debug.mkToast("onConnected - Map",Map.this);
    }

    private boolean checkPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return true;
        }
        return false;
    }

    private void loadMap(){
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Properties.mapProperties mapProperties = new Properties().new mapProperties();
        mapFragment = SupportMapFragment.newInstance(mapProperties.getMapOptions());
        fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map,mapFragment,"map");
        fragmentTransaction.commit();
        mapFragment.getMapAsync(this);
    }
    private void getLocationServices(){
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
    }

    private void makeLocation(){
        /*
        LatLng sydney = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney") );
        */

        //SETEANDO PROPIEDADES POR MEDIO DE HASHMAP
        Properties.markerProperties mapMarkerProperties = new Properties().new markerProperties();
        HashMap properties = new HashMap<String,Object>();
        properties.put("long",mLastLocation.getLongitude());
        properties.put("lat",mLastLocation.getLongitude());
        properties.put("icon",R.drawable.ic_att);
        properties.put("title","Tu Ubicación");
        properties.put("snipplet","Tu Ubicación");
        properties.put("draggable",false);
        properties.put("alpha",5);
        properties.put("rotation",0);
        properties.put("zIndex",100);
        mapLib.geoelement.marker(mapMarkerProperties.getMarkerOptions(properties),true);

        //SETEANDO PROPIEDADES DEFAULT
        Properties.circleProperties circleProperties = new Properties().new circleProperties();
        circleProperties.center = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        circleProperties.radio = 50;
        circleProperties.fillColor = R.color.map_geo_fill;
        circleProperties.strokeColor = Color.BLACK;
        circleProperties.strokeWidth = 3;
        mapLib.geoelement.circle(circleProperties.getCircleOptions(),false);
    }

    //create
    //start
    //ready
    //conected
}
