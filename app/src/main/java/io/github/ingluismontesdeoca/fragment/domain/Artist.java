package io.github.ingluismontesdeoca.fragment.domain;

import com.google.gson.annotations.SerializedName;

import io.github.ingluismontesdeoca.fragment.io.model.JsonKeys;

/**
 * Created by Aministrador on 02/08/2016.
 */
public class Artist {
    @SerializedName(JsonKeys.ArtistName)
    String name;
    String urlMediumImage;
    String urlLargeImage;
    String playCount;
    String listeners;


    public Artist() {

    }

    public Artist(String name) {
        this.name = name;
    }

    public Artist(String name,String urlMediumImage, String urlLargeImage){
        this.name = name;
        this.urlMediumImage = urlMediumImage;
        this.urlLargeImage = urlLargeImage;
    }

    public String getName() {
        return name;
    }

    public String getUrlMediumImage(){
        return this.urlMediumImage;
    }

    public String getUrlLargeImage(){        return this.urlLargeImage; }

    public String getPlayCount(){        return this.playCount; }

    public String getListeners(){
        return this.listeners;
    }

    public void setName(String name) {    this.name = name; }

    public void setUrlMediumImage(String urlMediumImage){
        this.urlMediumImage = urlMediumImage;
    }

    public void seturlLargeImage(String urlLargeImage){
        this.urlLargeImage = urlLargeImage;
    }

    public void setPlayCount(String playCount){
        this.playCount = playCount;
    }

    public void setListeners(String listeners){
        this.listeners = listeners;
    }

}
