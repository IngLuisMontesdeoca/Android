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
import io.github.ingluismontesdeoca.fragment.domain.Artist;

/**
 * Created by Aministrador on 06/08/2016.
 */
public class HypedArtistAdapter extends RecyclerView.Adapter<HypedArtistAdapter.HypedArtistViewHolder> {
    ArrayList<Artist> artists;
    Context context;

    public HypedArtistAdapter(Context context) {
        this.context = context;
        this.artists = new ArrayList<>();
    }

    @Override
    public HypedArtistAdapter.HypedArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hyped_artist, parent, false);
        return new HypedArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HypedArtistAdapter.HypedArtistViewHolder holder, int position) {
        Artist currentArtist = artists.get(position);
        holder.setArtistName(currentArtist.getName());

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
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }


    public class HypedArtistViewHolder extends RecyclerView.ViewHolder {
        ImageView artistImage;
        TextView artistName;

        public HypedArtistViewHolder(View itemView) {
            super(itemView);
            artistImage = (ImageView) itemView.findViewById(R.id.img_artist);
            artistName = (TextView) itemView.findViewById(R.id.txt_name);
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
