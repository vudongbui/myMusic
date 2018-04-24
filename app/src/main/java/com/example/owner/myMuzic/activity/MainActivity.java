package com.example.owner.myMuzic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.owner.myMuzic.R;
import com.example.owner.myMuzic.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.tl_main)
    TabLayout tlMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.ll_main)
    RelativeLayout llMain;
    @BindView(R.id.iv_test)
    ImageView ivTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
    }

    @OnClick(R.id.iv_test)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this,PlayActivity.class);
        startActivity(intent);
        Log.d(TAG, "onViewClicked: wtf??");
    }
}
