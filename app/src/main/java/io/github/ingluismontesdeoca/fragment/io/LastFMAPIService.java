package io.github.ingluismontesdeoca.fragment.io;

import io.github.ingluismontesdeoca.fragment.domain.Artist;
import io.github.ingluismontesdeoca.fragment.io.model.TopArtistResponse;
import io.github.ingluismontesdeoca.fragment.io.model.TopArtistResponseDetail;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Aministrador on 18/08/2016.
 */
public interface LastFMAPIService {

    @GET(APIConstants.URL_HYPED_ARTISTS)
    rx.Observable<TopArtistResponse> getTopArtist();

    @GET(APIConstants.URL_TOP_ARTISTS)
    rx.Observable<TopArtistResponseDetail> getTopArtistDetail();

    @GET(APIConstants.URL_GET_ARTIST)
    rx.Observable<Artist> getArtistInfo(@Query(APIConstants.PARAM_ARTIST) String name);

}
