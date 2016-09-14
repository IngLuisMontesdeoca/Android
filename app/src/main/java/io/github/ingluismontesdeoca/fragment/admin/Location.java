package io.github.ingluismontesdeoca.fragment.admin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;

import io.github.ingluismontesdeoca.fragment.R;
import io.github.ingluismontesdeoca.fragment.io.model.TopArtistResponse;
import io.github.ingluismontesdeoca.fragment.lib.map.MapLib;
import io.github.ingluismontesdeoca.fragment.lib.map.Properties;
import io.github.ingluismontesdeoca.fragment.ui.HypedItemDecoration;
import io.github.ingluismontesdeoca.fragment.ui.adapter.HypedArtistAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Location extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        Callback<TopArtistResponse>,
        OnMapReadyCallback,
                GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private static final int NUM_COLUMNS = 2;

    private RecyclerView mHypedArtistList;
    private HypedArtistAdapter adapter;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ListView navigationOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        initListView();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        adapter = new HypedArtistAdapter( this );
        loadMap();


        //Crear Fragmento
        /*
        MapFragment mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mapArea, mMapFragment);
        fragmentTransaction.commit();*/

    }

    private void initListView(){
        navigationOptions = (ListView) findViewById(R.id.nav_options);
    }

    private void loadPTNs(){
        ArrayList<String> dns = new ArrayList<String>();
        dns.add("5563022778");
        dns.add("5563022778");
        dns.add("5563022778");
        dns.add("5563022778");
        dns.add("5563022778");
        dns.add("5563022778");
        navigationOptions.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item,dns));
    }

    private void loadPOIS(){
        ArrayList<String> dns = new ArrayList<String>();
        dns.add("POI 1");
        dns.add("POI 2");
        dns.add("POI 3");
        dns.add("POI 4");
        dns.add("POI 5");
        dns.add("POI 6");
        navigationOptions.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item,dns));
    }

    private void loadGeofence(){
        ArrayList<String> dns = new ArrayList<String>();
        dns.add("GEO 1");
        dns.add("GEO 2");
        dns.add("GEO 3");
        dns.add("GEO 4");
        dns.add("GEO 5");
        dns.add("GEO 6");
        navigationOptions.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item,dns));
    }

    private void loadInfo( int id){
        switch(id){
            case R.id.loc_equipos:
                loadPTNs();
                break;
            case R.id.geo_poi:
                loadPOIS();
                break;
            case R.id.geo_geoc:
                loadGeofence();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(navigationView)) {
            drawer.closeDrawer(navigationView);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        loadInfo(item.getItemId());
        drawer.closeDrawer(navigationView);
        drawer.openDrawer(navigationOptions);
        return true;
    }

    private void setupArtistList(){
        Toast.makeText(Location.this,"setupArtistList",Toast.LENGTH_LONG).show();
        mHypedArtistList.setLayoutManager(new GridLayoutManager(this, NUM_COLUMNS));
        mHypedArtistList.setAdapter(adapter);
        mHypedArtistList.addItemDecoration(new HypedItemDecoration(this,R.integer.offset));
    }

    @Override
    public void success(TopArtistResponse topArtistResponse, Response response) {
        Toast.makeText(Location.this,"success",Toast.LENGTH_LONG).show();
        adapter.addAll(topArtistResponse.getArtistsList());
    }

    @Override
    public void failure(RetrofitError error) {
        error.printStackTrace();
    }

    /******** METODOS DE CARGA DEL MAPA ***********
     *
     *
     *
     *
     *
    **********************************************/

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private android.location.Location mLastLocation;
    private MapLib mapLib;
    private SupportMapFragment mapFragment;
    private FragmentTransaction fragmentTransaction;
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (checkPermission())
            return;
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Debug.mkToast("onConnectionSuspended - Map",Location.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        this.mapLib = new MapLib(mMap);
        this.getLocationServices();
        Debug.mkToast("onMapReady - Map",Location.this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Debug.mkToast("Error onConnectionFailed - Map",Location.this);
    }

    private void getLocation(){
        if( mLastLocation != null) {
            Toast.makeText(Location.this, mLastLocation.getLongitude()+","+mLastLocation.getLatitude() , Toast.LENGTH_SHORT).show();
            this.makeLocation();
        }else
            Toast.makeText(Location.this, "UNABLE TO GET LOCATION", Toast.LENGTH_SHORT).show();
        Debug.mkToast("onConnected - Map",Location.this);
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

        /*
        Properties.markerProperties mapMarkerProperties = new Properties().new markerProperties();
        mapMarkerProperties.position = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        mapMarkerProperties.icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_att);
        */


        //SETEANDO PROPIEDADES POR MEDIO DE HASHMAP
        Properties.markerProperties mapMarkerProperties = new Properties().new markerProperties();
        HashMap properties = new HashMap<String,Object>();
        properties.put("long",mLastLocation.getLongitude());
        properties.put("lat",mLastLocation.getLatitude());
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
}
