package com.example.owner.myMuzic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.myMuzic.R;
import com.example.owner.myMuzic.databasees.TopSongModel;
import com.example.owner.myMuzic.events.OnClickTopSong;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;

/**
 * Created by Owner on 4/22/2018.
 */

public class TopSongAdapter extends RecyclerView.Adapter<TopSongAdapter.TopSongViewHolder> {
    List<TopSongModel> topSongModels = new ArrayList<>();
    String urlSong;

    public TopSongAdapter(List<TopSongModel> topSongModels) {
        this.topSongModels = topSongModels;
    }

    @Override
    public TopSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_topsong,parent,false);
        return new TopSongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopSongViewHolder holder, int position) {
        holder.setData(topSongModels.get(position));
    }

    @Override
    public int getItemCount() {
        return topSongModels.size();
    }


    public class TopSongViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_top_song)
        ImageView ivTopSong;
        @BindView(R.id.tv_song)
        TextView tvSong;
        @BindView(R.id.tv_artist)
        TextView tvArtist;

        View view;
        public TopSongViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            view = itemView;

        }

        public void setData(final TopSongModel topSongModel) {
            Picasso.get()
                    .load(topSongModel.image)
                    .transform(new CropCircleTransformation())
                    .into(ivTopSong);
            tvSong.setText(topSongModel.song);
            tvArtist.setText(topSongModel.artist);
//            urlSong.toString(topSongModel.url);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().postSticky(new OnClickTopSong(topSongModel));
                }
            });



        }
    }
}
