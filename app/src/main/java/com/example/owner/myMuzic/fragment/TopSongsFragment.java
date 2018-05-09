package com.example.owner.myMuzic.fragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.myMuzic.R;
import com.example.owner.myMuzic.adapter.TopSongAdapter;
import com.example.owner.myMuzic.databasees.MusicTypeModel;
import com.example.owner.myMuzic.databasees.TopSongModel;
import com.example.owner.myMuzic.net.MusicService;
import com.example.owner.myMuzic.net.RetrofitInstance;
import com.example.owner.myMuzic.net.TopSongResponse;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongsFragment extends Fragment {

    private static final String TAG = "TopSongsFragment";

    @BindView(R.id.iv_favourite)
    ImageView ivFavourite;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rv_top_song)
    RecyclerView rvTopSong;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Unbinder unbinder;
    @BindView(R.id.iv_music_type)
    ImageView ivMusicType;
    @BindView(R.id.tv_music_type)
    TextView tvMusicType;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    TopSongAdapter topSongAdapter;
    List<TopSongModel> topSongModels = new ArrayList<>();
    MusicTypeModel musicTypeModel;
    @BindView(R.id.av_loading)
    AVLoadingIndicatorView avLoading;

    public TopSongsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_songs, container, false);
        unbinder = ButterKnife.bind(this, view);
        musicTypeModel = (MusicTypeModel) getArguments().getSerializable("music_type_model");

        Picasso.get().load(musicTypeModel.imageID).into(ivMusicType);
        tvMusicType.setText(musicTypeModel.name);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    toolbar.setBackground(getResources().getDrawable(R.drawable.custom_gradient_text_2));
                } else {
                    toolbar.setBackground(null);
                }
            }
        });


        topSongAdapter = new TopSongAdapter(topSongModels);
        rvTopSong.setAdapter(topSongAdapter);
        rvTopSong.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTopSong.setItemAnimator(new SlideInRightAnimator());

        loadTopSongs();
        return view;
    }

    private void loadTopSongs() {
        MusicService musicService = RetrofitInstance
                .getRetrofitInstance()
                .create(MusicService.class);
        musicService.getTopSong(musicTypeModel.id)
                .enqueue(new Callback<TopSongResponse>() {
                    @Override
                    public void onResponse(Call<TopSongResponse> call, Response<TopSongResponse> response) {
                        avLoading.hide();
                        List<TopSongResponse.Entry> entries = response.body().feed.entry;
                        for (TopSongResponse.Entry entry : entries) {
                            TopSongModel topSongModel = new TopSongModel(
                                    ""
                                    , entry.image.get(2).label
                                    , entry.name.label
                                    , entry.artist.label
                            );
                            topSongModels.add(topSongModel);
                            topSongAdapter.notifyItemChanged(entries.indexOf(entry));
                        }
                    }

                    @Override
                    public void onFailure(Call<TopSongResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {

    }
}
