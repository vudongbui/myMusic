package com.example.owner.myMuzic.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.myMuzic.R;
import com.example.owner.myMuzic.activity.MainActivity;
import com.example.owner.myMuzic.databasees.MusicTypeModel;
import com.example.owner.myMuzic.fragment.TopSongsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;

/**
 * Created by Owner on 4/15/2018.
 */

public class MusicTypesAdapter extends RecyclerView.Adapter<MusicTypesAdapter.MusicTypesViewHolder> {
    List<MusicTypeModel> musicTypeModels = new ArrayList<>();
    Context context;


    public MusicTypesAdapter(List<MusicTypeModel> musicTypeModels, Context context) {
        this.musicTypeModels = musicTypeModels;
        this.context = context;
    }

    //tao itemview luc bat dau vao rView
    @Override
    public MusicTypesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_music,parent,false);
        return new MusicTypesViewHolder(itemView);
    }

    //load data len itemview
    @Override
    public void onBindViewHolder(MusicTypesViewHolder holder, int position) {
        holder.setData(musicTypeModels.get(position));
    }

    @Override
    public int getItemCount() {
        return musicTypeModels.size();
    }

    public class MusicTypesViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_music_type)
        ImageView ivMusicType;
        @BindView(R.id.tv_music_type)
        TextView tvMusicType;
        public MusicTypesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        //load Data ntn??
        public void setData(final MusicTypeModel musicTypeModel){
            ivMusicType.setBackgroundResource(musicTypeModel.imageID);
            tvMusicType.setText(musicTypeModel.name);

            ivMusicType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TopSongsFragment topSongsFragment = new TopSongsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("music_type_model", musicTypeModel);
                    topSongsFragment.setArguments(bundle);


                    com.example.owner.myMuzic.utils.Utils.openFragment(
                            ((MainActivity) context).getSupportFragmentManager()
                            ,R.id.ll_main
                            ,topSongsFragment);
                }
            });
        }
    }
}
