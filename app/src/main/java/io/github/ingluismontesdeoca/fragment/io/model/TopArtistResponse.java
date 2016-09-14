package io.github.ingluismontesdeoca.fragment.io.model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.github.ingluismontesdeoca.fragment.domain.Artist;

/**
 * Created by Aministrador on 18/08/2016.
 */
public class TopArtistResponse {

    @SerializedName(JsonKeys.ARTIST_RESULTS)
    TopArtistResults results;

    public ArrayList<Artist> getArtistsList(){
        return results.Artist;
    }

    public void setArtists(ArrayList<Artist> artists){
        this.results.Artist = artists;
    }

    public class TopArtistResults{
        ArrayList<Artist> Artist;
    }
}
