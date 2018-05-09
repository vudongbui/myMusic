package com.example.owner.myMuzic.utils;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.owner.myMuzic.R;
import com.example.owner.myMuzic.databasees.TopSongModel;
import com.example.owner.myMuzic.net.LocationResponse;
import com.example.owner.myMuzic.net.MusicService;
import com.example.owner.myMuzic.net.RetrofitInstance;
import com.example.owner.myMuzic.net.SearchSongResponse;

import hybridmediaplayer.HybridMediaPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Owner on 5/3/2018.
 */

public class MusicHandle {
    private static  HybridMediaPlayer hybridMediaPlayer;
    private static boolean keepUpdate = true;
    private static final String TAG = "MusicHandle";
    public static void getSearchSong(TopSongModel topSongModel, final Context context){
        MusicService musicService = RetrofitInstance.getRetrofitInstance()
                .create(MusicService.class);
        musicService.getSearchSong(topSongModel.song+" "+ topSongModel.artist)
                .enqueue(new Callback<SearchSongResponse>() {
                    @Override
                    public void onResponse(Call<SearchSongResponse> call, Response<SearchSongResponse> response) {
                        String url = response.body().data.url;
                        getLocationSong(url,context);
                    }

                    @Override
                    public void onFailure(Call<SearchSongResponse> call, Throwable t) {

                    }
                });
    }
    public static void getLocationSong(String url, final Context context) {
        MusicService musicService = RetrofitInstance.getRetrofitIXMLnstance()
                .create(MusicService.class);
        musicService.getLocation(url.split("=")[1]).enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                //Log.d(TAG, "onResponse: "+response.body().trackXML.location);
                if (hybridMediaPlayer!=null){
                    if (hybridMediaPlayer.isPlaying()){
                        hybridMediaPlayer.pause();
                    }
                    hybridMediaPlayer.release();
                }

                hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
                hybridMediaPlayer.setDataSource(response.body().trackXML.location.trim());
                hybridMediaPlayer.prepare();
                hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
                        hybridMediaPlayer.play();
                    }
                });
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {

            }
        });
    }
    public static void playPause(){
        if (hybridMediaPlayer!=null){
            if (hybridMediaPlayer.isPlaying()){
                hybridMediaPlayer.pause();
            } else {
                hybridMediaPlayer.play();
            }
        }
    }

    public static void updateRealtimeUI(final SeekBar seekBar,
                                        final FloatingActionButton floatingActionButton,
                                        final TextView tvSt,
                                        final TextView tvFn,
                                        final ImageView imageView1){
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // update seekbar and button
                if (hybridMediaPlayer!=null && keepUpdate){
                    seekBar.setMax(hybridMediaPlayer.getDuration());
                    seekBar.setProgress(hybridMediaPlayer.getCurrentPosition());
                    if (hybridMediaPlayer.isPlaying()){
                        floatingActionButton.setImageResource(R.drawable.ic_pause_black_24dp);
                    } else {
                        floatingActionButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    }

                    if (tvSt !=null){
                        tvSt.setText(Utils.formatTime(hybridMediaPlayer.getCurrentPosition()));
                        tvFn.setText(Utils.formatTime(hybridMediaPlayer.getDuration()));
                    }
                    Utils.rotateImage(imageView1,hybridMediaPlayer.isPlaying());
                }
                // run code again
                handler.postDelayed(this,100); // 100ms
            }
        };
        runnable.run();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                keepUpdate = false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                hybridMediaPlayer.seekTo(seekBar.getProgress());
                keepUpdate = true;
            }
        });
    }
}
