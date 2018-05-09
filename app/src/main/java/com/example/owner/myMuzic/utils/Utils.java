package com.example.owner.myMuzic.utils;

import android.animation.Animator;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.owner.myMuzic.databasees.TopSongModel;
import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListener;
import com.thin.downloadmanager.ThinDownloadManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.owner.myMuzic.utils.MusicHandle.getSearchSong;

/**
 * Created by Owner on 4/22/2018.
 */

public class Utils {
    private static boolean keepUpdate = true;
    private static String downloadLink;
    private static ThinDownloadManager thinDownloadManager;
    public static void openFragment(FragmentManager fragmentManager
            , int layoutID, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public static String formatTime(int time){
        int min = time/60000;
        int sec = (time/1000)%60;

        return String.format("%02d:%02d",min,sec);
    }
    public static void rotateImage(ImageView imageView, boolean isRotate){
        RotateAnimation rotateAnimation = new RotateAnimation(0f,359f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(10000);
        rotateAnimation.setInterpolator(new LinearInterpolator());

        if (isRotate){
            if (imageView.getAnimation() == null){
                imageView.startAnimation(rotateAnimation);
            }
        } else {
            imageView.setAnimation(null);
        }
    }

    public static List<TopSongModel> getListDownloadSong()
    {
        List<TopSongModel> topSongModels = new ArrayList<>();

        return topSongModels;
    }

    public static File[] getDownloadedFiles(Context context){
        File folder = new File(context.getExternalCacheDir().toString()+"/Download");
        if(!folder.exists())
            folder.mkdirs();

        return folder.listFiles();
    }

}
