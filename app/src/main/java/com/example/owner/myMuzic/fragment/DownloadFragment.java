package com.example.owner.myMuzic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.owner.myMuzic.R;
import com.example.owner.myMuzic.adapter.TopSongAdapter;
import com.example.owner.myMuzic.databasees.TopSongModel;
import com.example.owner.myMuzic.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {

    List<TopSongModel> topSongModelList;
    Unbinder unbinder;
    @BindView(R.id.rv_download)
    RecyclerView rvDownload;

    public DownloadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_download, container, false);
        unbinder = ButterKnife.bind(this, view);
        File[] downloadedFiles = Utils.getDownloadedFiles(getContext());
        topSongModelList = new ArrayList<>();
        for (File file : downloadedFiles) {
            if(!file.getName().contains("--")) continue;  //??
            topSongModelList.add(new TopSongModel(
                    file.getAbsolutePath(),
                    null,
                    file.getName().split("--")[0],
                    file.getName().split("--")[1].split(".mp3")[0]) //dat ten

            );
        }
        TopSongAdapter topSongAdapter = new TopSongAdapter(topSongModelList);
        rvDownload.setLayoutManager(
                new LinearLayoutManager(getContext())
        );
        rvDownload.setAdapter(topSongAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
