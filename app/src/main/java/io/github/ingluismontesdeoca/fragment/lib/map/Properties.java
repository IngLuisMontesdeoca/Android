package io.github.ingluismontesdeoca.fragment.lib.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.HashMap;

import io.github.ingluismontesdeoca.fragment.R;

/**
 * Created by Aministrador on 31/08/2016.
 */
public class Properties {

    public Properties(){

    }

    public class mapProperties{
        //Propiedades del mapa
        public int zoom = 11;
        public LatLng position = new LatLng(19.41281849460692, -99.13204990246524);
        public int mapType = GoogleMap.MAP_TYPE_NORMAL;
        private boolean mapToolBarEnabled = false;
        private boolean liteMode = false;
        private float maxZoomPreference = 19;
        private float minZoomPreference = 1;
        private boolean zoomControlsEnabled = true;
        private boolean zoomGesturesEnabled = true;
        private boolean zOrderOnTop = false;
        //private LatLngBounds latLngBoundsForCameraTarget;
        //private boolean rotateGesturesEnabled;
        //private boolean scrollGesturesEnabled;
        //private boolean tiltGesturesEnabled;
        //private boolean ambientEnabled;

        public GoogleMapOptions getMapOptions(){
            return new GoogleMapOptions()
                    .camera( CameraPosition.fromLatLngZoom(this.position,this.zoom))
                    .mapType(this.mapType)
                    .mapToolbarEnabled(this.mapToolBarEnabled)
                    .liteMode(this.liteMode)
                    .maxZoomPreference(this.maxZoomPreference)
                    .minZoomPreference(this.minZoomPreference)
                    .zoomControlsEnabled(this.zoomControlsEnabled)
                    .zoomGesturesEnabled(this.zoomGesturesEnabled)
                    .zOrderOnTop(this.zOrderOnTop);
                    //.latLngBoundsForCameraTarget(this.latLngBoundsForCameraTarget)
                    //.rotateGesturesEnabled(this.rotateGesturesEnabled)
                    //.scrollGesturesEnabled(this.scrollGesturesEnabled)
                    //.tiltGesturesEnabled(this.tiltGesturesEnabled)
                    //.ambientEnabled(this.ambientEnabled);
        }
    }

    //Propiedades de marca
    public class markerProperties{
        //Propiedades de marca default
        public String snipplet = "snippelt";
        public String title = "title";
        public LatLng position = new LatLng(19.41281849460692, -99.13204990246524);
        public double anchorX = 0.50;
        public double anchorY = 0.50;
        public double anchorInfoWindowX = 0.50;
        public double anchorInfoWindowY = 0.50;
        public boolean draggable = false;
        public BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_att);
        public float alpha = 5;
        public boolean flat = false;
        public float rotation = 0;
        public boolean visible = true;
        public float zIndex = 100;

        //SETEAR PROPIEDADES POR HASHMAP
        public MarkerOptions getMarkerOptions(HashMap<String,Object> properties){
            return new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(properties.get("long").toString()), Double.parseDouble(properties.get("lat").toString())) )
                    .title(properties.get("title").toString())
                    .snippet(properties.get("snipplet").toString())
                    .icon(BitmapDescriptorFactory.fromResource(Integer.parseInt(properties.get("icon").toString())))
                    .draggable(Boolean.parseBoolean(properties.get("draggable").toString()))
                    .alpha(Integer.parseInt(properties.get("alpha").toString()))
                    .rotation(Integer.parseInt(properties.get("rotation").toString()))
                    .zIndex(Integer.parseInt(properties.get("zIndex").toString()));
                    //.anchor(this.anchorX,this.anchorY)
                    //.infoWindowAnchor(this.anchorX,this.anchorY)
        }

        //SETEAR PROPIEDADES DEFAULT
        public MarkerOptions getMarkerOptions(){
            return new MarkerOptions()
                    .position(this.position)
                    .title(this.title)
                    .snippet(this.snipplet)
                    .icon(this.icon)
                    .draggable(this.draggable)
                    .alpha(this.alpha)
                    .rotation(this.rotation)
                    .zIndex(this.zIndex);
            //.anchor(this.anchorX,this.anchorY)
            //.infoWindowAnchor(this.anchorX,this.anchorY)
        }
    }

    public class circleProperties{
        public LatLng center = new LatLng(19.41281849460692, -99.13204990246524);
        public double radio = 50;
        public float strokeWidth = 2;
        public float zIndex = 100;
        public int strokeColor = 1;
        public int fillColor = 1;
        public boolean visible = true;

        public CircleOptions getCircleOptions(){
            return new CircleOptions()
                    .center(this.center)
                    .radius(this.radio)
                    .strokeColor(this.strokeColor)
                    .strokeWidth(this.strokeWidth)
                    .fillColor(this.fillColor)
                    .zIndex(this.zIndex)
                    .visible(this.visible);
        }
    }

    public class polylineProperties{
        public PolylineOptions getPolylineOptions(){ return new PolylineOptions(); }
    }

    public class polygonProperties{
        public PolygonOptions getPolygonOptions(){ return new PolygonOptions(); }
    }

}
