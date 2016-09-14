package io.github.ingluismontesdeoca.fragment.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.github.ingluismontesdeoca.fragment.R;
import io.github.ingluismontesdeoca.fragment.admin.Debug;
import io.github.ingluismontesdeoca.fragment.domain.Artist;

/**
 * Created by Aministrador on 30/08/2016.
 */
public class TopArtistAdapter extends RecyclerView.Adapter<TopArtistAdapter.TopArtistViewHolder> {
    ArrayList<Artist> artists;
    Context context;

    public TopArtistAdapter(Context context) {
        this.context = context;
        this.artists = new ArrayList<>();
    }

    @Override
    public TopArtistAdapter.TopArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_artist, parent, false);
        return new TopArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopArtistAdapter.TopArtistViewHolder holder, int position) {
        Artist currentArtist = artists.get(position);
        holder.setArtistName(currentArtist.getName());
        holder.setPlayCount(currentArtist.getPlayCount());
        holder.setListeners(currentArtist.getListeners());

        if (currentArtist.getUrlMediumImage() == null)
            holder.setArtistDefaultImage();
        else
            holder.setArtistImage(currentArtist.getUrlMediumImage());
    }

    public void addAll(@NonNull ArrayList<Artist> artist) {
        if (artist == null)
            throw new NullPointerException("El arreglo no puede ser nulo");

        this.artists.addAll(artist);
        //Notificar de cambios en el list
        notifyDataSetChanged();
    }

    public void addItem(@NonNull Artist artist){
        this.artists.add(artist);
        Debug.mkToast(artist.getName(),this.context);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }


    public class TopArtistViewHolder extends RecyclerView.ViewHolder {
        ImageView artistImage;
        TextView artistName;
        TextView artistPlayCount;
        TextView artistListeners;

        public TopArtistViewHolder(View itemView) {
            super(itemView);
            artistImage = (ImageView) itemView.findViewById(R.id.img_artist);
            artistName = (TextView) itemView.findViewById(R.id.txt_name);
            artistPlayCount = (TextView) itemView.findViewById(R.id.txt_playcount);
            artistListeners = (TextView) itemView.findViewById(R.id.txt_listeners);
        }

        public void setPlayCount(String playcount) {
            this.artistPlayCount.setText(playcount);
        }

        public void setListeners(String listerners) {
            this.artistListeners.setText(listerners);
        }

        public void setArtistName(String name) {
            this.artistName.setText(name);
        }

        public void setArtistImage(String url) {
            Picasso.with(context)
                    .load(url)
                    .placeholder(R.drawable.artist_placeholder)
                    .into(artistImage);
        }

        public void setArtistDefaultImage() {
            Picasso.with(context)
                    .load(R.drawable.cast_album_art_placeholder)
                    .into(artistImage);
        }
    }

}

