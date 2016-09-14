package io.github.ingluismontesdeoca.fragment.io.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import io.github.ingluismontesdeoca.fragment.domain.Artist;
import io.github.ingluismontesdeoca.fragment.io.model.JsonKeys;

/**
 * Created by Aministrador on 05/09/2016.
 */
public class getArtistDeserializer implements JsonDeserializer<Artist>{

    @Override
    public Artist deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        //Obtener objeto Artists
        JsonObject artistResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.ARTIST);
        //Convertir el JsonObject de Artist a un objeto de la clase Artist
        return extractArtisFromJson(artistResponseData);
    }

    private Artist extractArtisFromJson(JsonObject artistData) {
        String name = artistData.get(JsonKeys.ArtistName).getAsString();
        JsonObject stats = artistData.get(JsonKeys.STATS).getAsJsonObject();
        String listeners = stats.get(JsonKeys.LISTENERS).getAsString();
        String playcount = stats.get(JsonKeys.PLAY_COUNT).getAsString();

        JsonArray imagesArray = artistData.getAsJsonArray(JsonKeys.IMAGE);
        String[] images = extractImagesFromJsonArray(imagesArray);

        Artist currentArtist = new Artist();
        currentArtist.setName(name);
        currentArtist.setPlayCount(playcount);
        currentArtist.setListeners(listeners);
        currentArtist.setUrlMediumImage(images[0]);
        currentArtist.seturlLargeImage(images[1]);

        return currentArtist;
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
