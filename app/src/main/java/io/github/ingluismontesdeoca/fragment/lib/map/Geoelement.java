package io.github.ingluismontesdeoca.fragment.lib.map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class Geoelement{

    public GoogleMap map;
    public Properties.markerProperties mapMarkerProperties;
    public Properties.circleProperties mapCircleProperties;
    public Properties.polygonProperties mapPolygonProperties;
    public Properties.polylineProperties mapPolylineProperties;

    public Geoelement(GoogleMap map){
        this.map = map;
        this.mapMarkerProperties = new Properties().new markerProperties();
        this.mapCircleProperties = new Properties().new circleProperties();
        this.mapPolygonProperties = new Properties().new polygonProperties();
        this.mapPolylineProperties = new Properties().new polylineProperties();
    }

    public Marker marker(MarkerOptions options, boolean center){
        try{
            if( options == null )
                options = this.mapMarkerProperties.getMarkerOptions();
            if( center )
                this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(options.getPosition(),19));

            return this.map.addMarker(options);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Circle circle(CircleOptions options, boolean center){
        try{
            if( options == null )
                options = this.mapCircleProperties.getCircleOptions();
            if( center )
                this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(options.getCenter(),19));

            return this.map.addCircle(options);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Polyline polyline(PolylineOptions options, boolean center){
        try{
            if( options == null )
                options = this.mapPolylineProperties.getPolylineOptions();
            return this.map.addPolyline(options);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Polygon polygon(PolygonOptions options, boolean center){
        try{
            if( options == null )
                options = this.mapPolygonProperties.getPolygonOptions();
            return this.map.addPolygon(options);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*Adicionales*/
    public void markerGroups(){        }
    public void tooltip(){        }
}
