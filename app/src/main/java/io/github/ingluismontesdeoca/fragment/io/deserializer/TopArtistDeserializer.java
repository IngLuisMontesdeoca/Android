package io.github.ingluismontesdeoca.fragment.io.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.github.ingluismontesdeoca.fragment.domain.Artist;
import io.github.ingluismontesdeoca.fragment.io.model.JsonKeys;
import io.github.ingluismontesdeoca.fragment.io.model.TopArtistResponseDetail;

/**
 * Created by Aministrador on 30/08/2016.
 */
public class TopArtistDeserializer implements JsonDeserializer<TopArtistResponseDetail> {

    @Override
    public TopArtistResponseDetail deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        TopArtistResponseDetail response = gson.fromJson(json,TopArtistResponseDetail.class);
        //Obtener objeto Artists
        JsonObject artistResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.ARTIST_RESULTS);
        //Obtener el array de artistas
        JsonArray artistsArray = artistResponseData.getAsJsonArray(JsonKeys.ARTIST);
        //Convertir el JsonArray de Artist a objetos de la clase Artist
        response.setArtists(extractArtisFromJsonArray(artistsArray));
        return response;
    }

    private ArrayList<Artist> extractArtisFromJsonArray(JsonArray array){
        ArrayList<Artist> artists = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            JsonObject artistData = array.get(i).getAsJsonObject();

            String name = artistData.get(JsonKeys.ArtistName).getAsString();
            String play_count = artistData.get(JsonKeys.PLAY_COUNT).getAsString();
            String listeners = artistData.get(JsonKeys.LISTENERS).getAsString();
            //String listeners = "5564";
            JsonArray imagesArray =  artistData.getAsJsonArray(JsonKeys.IMAGE);
            String[] images = extractImagesFromJsonArray(imagesArray);


            Artist currentArtist = new Artist();
            currentArtist.setName(name);
            currentArtist.setPlayCount(play_count);
            currentArtist.setListeners(listeners);
            currentArtist.setUrlMediumImage(images[0]);
            currentArtist.seturlLargeImage(images[1]);
            artists.add(currentArtist);
        }

        return artists;
    }

    private String[] extractImagesFromJsonArray(JsonArray images){
        String[] imagesArray = new String[2];

        for (int k = 0; k < images.size(); k++) {
            JsonObject objImage = images.get(k).getAsJsonObject();
            String _url = objImage.get(JsonKeys.URL).getAsString();

            if( _url.isEmpty() )
                _url = null;

            if( objImage.get(JsonKeys.SIZE).getAsString().equals(JsonKeys.IMAGE_MEDIUM)  )
                imagesArray[0] = _url;

            else if( objImage.get(JsonKeys.SIZE).getAsString().equals(JsonKeys.IMAGE_MEGA) )
                imagesArray[1] = _url;
        }

        return imagesArray;
    }

}
