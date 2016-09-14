package io.github.ingluismontesdeoca.fragment.io;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.ingluismontesdeoca.fragment.domain.Artist;
import io.github.ingluismontesdeoca.fragment.io.deserializer.HypedArtistsDeserializer;
import io.github.ingluismontesdeoca.fragment.io.deserializer.TopArtistDeserializer;
import io.github.ingluismontesdeoca.fragment.io.deserializer.getArtistDeserializer;
import io.github.ingluismontesdeoca.fragment.io.model.TopArtistResponse;
import io.github.ingluismontesdeoca.fragment.io.model.TopArtistResponseDetail;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Aministrador on 20/08/2016.
 */
public class LastFMAPIAdapter {

    private static LastFMAPIService API_SERVICE;

    public static LastFMAPIService getAPIService(){
        if( API_SERVICE == null ){
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(APIConstants.URL_BASE)
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .setConverter(buildTopArtistConverter())
                    .build();
            API_SERVICE = adapter.create(LastFMAPIService.class);
        }
        return API_SERVICE;
    }

    private static GsonConverter buildTopArtistConverter(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TopArtistResponse.class , new HypedArtistsDeserializer())
                .registerTypeAdapter(TopArtistResponseDetail.class, new TopArtistDeserializer())
                .registerTypeAdapter(Artist.class,new getArtistDeserializer())
                .create();
        return new GsonConverter(gson);
    }

    //private static String getApiKey(){return BuildConfig.LAST_FM_API_KEY;}

}
