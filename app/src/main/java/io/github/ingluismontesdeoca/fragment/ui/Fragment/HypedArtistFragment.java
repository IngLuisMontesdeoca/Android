package io.github.ingluismontesdeoca.fragment.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.github.ingluismontesdeoca.fragment.R;
import io.github.ingluismontesdeoca.fragment.domain.Artist;
import io.github.ingluismontesdeoca.fragment.io.LastFMAPIAdapter;
import io.github.ingluismontesdeoca.fragment.io.model.TopArtistResponse;
import io.github.ingluismontesdeoca.fragment.io.model.TopArtistResponseDetail;
import io.github.ingluismontesdeoca.fragment.ui.HypedItemDecoration;
import io.github.ingluismontesdeoca.fragment.ui.adapter.HypedArtistAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Aministrador on 25/07/2016.
 */
public class HypedArtistFragment extends Fragment implements Callback<TopArtistResponse> {

    private static final int NUM_COLUMNS = 2;

    private RecyclerView mHypedArtistList;
    private HypedArtistAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HypedArtistAdapter( getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        //getToArtists();
        //getFirstTopArtist();
        requestInfoForEachArtist();
    }

    private void getToArtists() {
        LastFMAPIAdapter.getAPIService()
                .getTopArtist()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TopArtistResponse>() {
                    @Override
                    public void call(TopArtistResponse topArtistResponse) {
                        adapter.addAll(topArtistResponse.getArtistsList());
                    }
                });
    }

    private void getFirstTopArtist() {
        LastFMAPIAdapter.getAPIService()
                //.getArtistInfo("Ariana Grande")
                .getTopArtistDetail()
                .flatMap(new Func1<TopArtistResponseDetail, Observable<Artist>>() {
                    @Override
                    public Observable<Artist> call(TopArtistResponseDetail topArtistResponseDetail) {
                        Artist firstArtist = topArtistResponseDetail.getArtistsList().get(0);
                        return LastFMAPIAdapter.getAPIService().getArtistInfo(firstArtist.getName());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Artist>() {
                    @Override
                    public void call(Artist artist) {
                        adapter.addItem(artist);
                    }
                });
    }


    private void requestInfoForEachArtist() {
        LastFMAPIAdapter.getAPIService()
                //.getArtistInfo("Ariana Grande")
                .getTopArtistDetail()
                .flatMap(new Func1<TopArtistResponseDetail, Observable<Artist>>() {
                    @Override
                    public Observable<Artist> call(TopArtistResponseDetail topArtistResponseDetail) {
                        return Observable.from(topArtistResponseDetail.getArtistsList());
                    }
                })
                .flatMap(new Func1<Artist, Observable<Artist>>() {
                    @Override
                    public Observable<Artist> call(Artist artist) {
                        //Artist firstArtist = topArtistResponseDetail.getArtistsList().get(0);
                        return LastFMAPIAdapter.getAPIService().getArtistInfo(artist.getName());
                    }
                })
                .filter(new Func1<Artist, Boolean>() {
                    @Override
                    public Boolean call(Artist artist) {
                        return (Integer.parseInt(artist.getListeners()) > 4000000);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Artist>() {
                    @Override
                    public void call(Artist artist) {
                        adapter.addItem(artist);
                    }
                });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hyped_artist,container,false);
        //Llenar RecyclerView
        mHypedArtistList = (RecyclerView) root.findViewById(R.id.hypedArtistList);
        setupArtistList();
        //fillDummyData();
        return root;
    }

    private void fillDummyData(){
        ArrayList<Artist> artists = new ArrayList<>();
        for(int i = 0; i<10; i++){
            artists.add(new Artist("Artista"+i));
        }
        adapter.addAll(artists);
    }

    private void setupArtistList(){
        mHypedArtistList.setLayoutManager(new GridLayoutManager(getActivity(), NUM_COLUMNS));
        mHypedArtistList.setAdapter(adapter);
        mHypedArtistList.addItemDecoration(new HypedItemDecoration(getActivity(),R.integer.offset));
    }

    @Override
    public void success(TopArtistResponse topArtistResponse, Response response) {
        //adapter.addAll(topArtistResponse.getArtistsList());
    }

    @Override
    public void failure(RetrofitError error) {
        error.printStackTrace();
    }

}
