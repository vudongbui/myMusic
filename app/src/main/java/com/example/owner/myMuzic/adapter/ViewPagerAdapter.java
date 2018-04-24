package com.example.owner.myMuzic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.owner.myMuzic.fragment.DownloadFragment;
import com.example.owner.myMuzic.fragment.FavouriteFragment;
import com.example.owner.myMuzic.fragment.MusicTypesFragment;

/**
 * Created by Owner on 4/15/2018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new MusicTypesFragment();
            case 1: return new FavouriteFragment();
            case 2: return new DownloadFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
