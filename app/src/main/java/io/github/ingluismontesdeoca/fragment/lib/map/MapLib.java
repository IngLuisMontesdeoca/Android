package io.github.ingluismontesdeoca.fragment.lib.map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Aministrador on 31/08/2016.
 */
public class MapLib {

    public GoogleMap map;
    public Geoelement geoelement;
    public Properties.mapProperties mapProperties;

    public MapLib(GoogleMap map){
        this.map = map;
        this.mapInitOptions();
    }

    public void setMap(GoogleMap map){   this.map = map; }

    public GoogleMap getMap(){ return this.map; }

    public void setZoom(int level){ this.map.moveCamera(CameraUpdateFactory.zoomTo(15));  }

    public void setMapProperties(){ }

    public void mapInitOptions(){ this.geoelement = new Geoelement(this.map); mapProperties = new Properties().new mapProperties(); }


}
