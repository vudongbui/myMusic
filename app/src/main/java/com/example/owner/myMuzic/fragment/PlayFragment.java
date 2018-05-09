package com.example.owner.myMuzic.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.owner.myMuzic.R;
import com.example.owner.myMuzic.databasees.TopSongModel;
import com.example.owner.myMuzic.events.OnClickTopSong;
import com.example.owner.myMuzic.utils.MusicHandle;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {


    @BindView(R.id.iv_back1)
    ImageView ivBack1;
    @BindView(R.id.tv_song1)
    TextView tvSong1;
    @BindView(R.id.tv_artist1)
    TextView tvArtist1;
    @BindView(R.id.iv_download1)
    ImageView ivDownload1;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.iv_next_left)
    ImageView ivNextLeft;
    @BindView(R.id.iv_button_play)
    ImageView ivButtonPlay;
    @BindView(R.id.iv_next_right)
    ImageView ivNextRight;
    @BindView(R.id.rl_3_buton_play)
    RelativeLayout rl3ButonPlay;
    @BindView(R.id.tv_min_start)
    TextView tvMinStart;
    @BindView(R.id.tv_min_finish)
    TextView tvMinFinish;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.rl_min_play)
    RelativeLayout rlMinPlay;
    @BindView(R.id.iv_bigpic)
    ImageView ivBigpic;
    Unbinder unbinder;
    TopSongModel topSongModel;
    @BindView(R.id.fb_play11)
    FloatingActionButton fbPlay11;

    public PlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Subscribe(sticky = true)
    public void onReceivedTopSong(OnClickTopSong onClickTopSong) {
        topSongModel = onClickTopSong.topSongModel;
        tvSong1.setText(topSongModel.song);
        tvArtist1.setText(topSongModel.artist);
        Picasso.get().load(topSongModel.image)
                .transform(new CropCircleTransformation())
                .into(ivBigpic);
        MusicHandle.updateRealtimeUI(seekBar,fbPlay11,tvMinStart,tvMinFinish,ivBigpic);
    }

    @OnClick({R.id.iv_back1, R.id.iv_download1, R.id.iv_next_left, R.id.fb_play11, R.id.iv_next_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back1:
                getActivity().onBackPressed();
                break;
            case R.id.iv_download1:
                break;
            case R.id.iv_next_left:
                break;
            case R.id.fb_play11:
                MusicHandle.playPause();
                break;
            case R.id.iv_next_right:
                break;
        }
    }
}
