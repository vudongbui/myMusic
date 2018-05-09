package com.example.owner.myMuzic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.owner.myMuzic.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by Owner on 4/25/2018.
 */

public class PlayActivity extends AppCompatActivity {
    @BindView(R.id.iv_back1)
    ImageView ivBack1;
    @BindView(R.id.tv_song1)
    TextView tvSong1;
    @BindView(R.id.tv_artist1)
    TextView tvArtist1;
    @BindView(R.id.iv_download1)
    ImageView ivDownload1;
    @BindView(R.id.iv_next_left)
    ImageView ivNextLeft;
    @BindView(R.id.iv_button_play)
    ImageView ivButtonPlay;
    @BindView(R.id.iv_next_right)
    ImageView ivNextRight;
    @BindView(R.id.tv_min_start)
    TextView tvMinStart;
    @BindView(R.id.tv_min_finish)
    TextView tvMinFinish;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.iv_bigpic)
    ImageView ivBigpic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_play);
        ButterKnife.bind(this);
        creat();
    }

    private void creat() {
        Picasso.get()
                .load("https://is1-ssl.mzstatic.com/image/thumb/Music128/v4/e0/71/9d/e0719d4d-9eb4-7eae-4347-57c28b37688b/00602567660262.rgb.jpg/55x55bb-85.png"
        ).transform(new CropCircleTransformation())
                .into(ivBigpic);
    }

    @OnClick({R.id.iv_back1, R.id.iv_download1, R.id.iv_next_left, R.id.iv_button_play, R.id.iv_next_right, R.id.tv_min_start, R.id.tv_min_finish, R.id.seekBar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back1:
                break;
            case R.id.iv_download1:
                break;
            case R.id.iv_next_left:
                break;
            case R.id.iv_button_play:
                break;
            case R.id.iv_next_right:
                break;
            case R.id.tv_min_start:
                break;
            case R.id.tv_min_finish:
                break;
            case R.id.seekBar:
                break;
        }
    }
}
