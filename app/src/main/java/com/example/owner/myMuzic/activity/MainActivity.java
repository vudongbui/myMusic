package com.example.owner.myMuzic.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.owner.myMuzic.R;
import com.example.owner.myMuzic.adapter.ViewPagerAdapter;
import com.example.owner.myMuzic.databasees.TopSongModel;
import com.example.owner.myMuzic.events.OnClickTopSong;
import com.example.owner.myMuzic.fragment.PlayFragment;
import com.example.owner.myMuzic.utils.MusicHandle;
import com.example.owner.myMuzic.utils.Utils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.tl_main)
    TabLayout tlMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.ll_main)
    RelativeLayout llMain;
    @BindView(R.id.iv_top_song)
    ImageView ivTopSong;
    @BindView(R.id.tv_song)
    TextView tvSong;
    @BindView(R.id.tv_artist)
    TextView tvArtist;
    @BindView(R.id.fb_play)
    FloatingActionButton fbPlay;
    @BindView(R.id.sb_mini)
    SeekBar sbMini;
    @BindView(R.id.rl_mini)
    RelativeLayout rlMini;
    @BindView(R.id.ll_main1)
    RelativeLayout llMain1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);
        tlMain.getTabAt(0).getIcon().setAlpha(255);
        tlMain.getTabAt(1).getIcon().setAlpha(111);
        tlMain.getTabAt(2).getIcon().setAlpha(111);

        tlMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
                vpMain.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(111);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(222);
            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vpMain.setAdapter(viewPagerAdapter);
        vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMain));

        sbMini.setPadding(0, 0, 0, 0);
    }

    @Subscribe (sticky = true)
    public void onReceivedTopSong(OnClickTopSong onClickTopSong) {
        TopSongModel topSongModel = onClickTopSong.topSongModel;

        Log.d(TAG, "onReceivedTopSong: ");
        rlMini.setVisibility(View.VISIBLE);
        tvSong.setText(topSongModel.song);
        tvArtist.setText(topSongModel.artist);
        Picasso.get().load(topSongModel.image)
                .transform(new CropCircleTransformation())
                .into(ivTopSong);
        MusicHandle.getSearchSong(topSongModel, this);
        MusicHandle.updateRealtimeUI(sbMini, fbPlay,null,null,ivTopSong);
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            super.onBackPressed();
        } else {
            moveTaskToBack(true);
        }
    }

    @OnClick({R.id.fb_play, R.id.rl_mini})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fb_play:
                MusicHandle.playPause();
                break;
            case R.id.rl_mini:
                Utils.openFragment(getSupportFragmentManager()
                        ,R.id.ll_main
                        ,new PlayFragment());
                break;
        }
    }
}
