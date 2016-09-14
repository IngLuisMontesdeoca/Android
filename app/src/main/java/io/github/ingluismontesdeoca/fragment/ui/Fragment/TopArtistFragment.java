package io.github.ingluismontesdeoca.fragment.ui.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.ingluismontesdeoca.fragment.R;
import io.github.ingluismontesdeoca.fragment.domain.Artist;
import io.github.ingluismontesdeoca.fragment.io.LastFMAPIAdapter;
import io.github.ingluismontesdeoca.fragment.io.model.TopArtistResponseDetail;
import io.github.ingluismontesdeoca.fragment.ui.adapter.TopArtistAdapter;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopArtistFragment extends Fragment {
    private RecyclerView artistList;
    private TopArtistAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TopArtistAdapter(getActivity());
    }

    public TopArtistFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getTopArtists();
        //getFirstTopArtist();
    }

    private void getTopArtists() {
        Subscription subscribe = LastFMAPIAdapter.getAPIService()
                .getTopArtistDetail()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TopArtistResponseDetail>() {
                    @Override
                    public void call(TopArtistResponseDetail topArtistResponseDetail) {
                        adapter.addAll(topArtistResponseDetail.getArtistsList());
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_top_artist, container, false);
        artistList = (RecyclerView) root.findViewById(R.id.top_artist_list);
        setUpArtistList();
        return root;
    }

    public void setUpArtistList() {
        artistList.setLayoutManager(new LinearLayoutManager(getActivity()));
        artistList.setAdapter(adapter);
    }

}
