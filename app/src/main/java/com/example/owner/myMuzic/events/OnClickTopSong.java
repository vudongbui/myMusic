package com.example.owner.myMuzic.events;

import com.example.owner.myMuzic.databasees.TopSongModel;

/**
 * Created by Owner on 5/3/2018.
 */

public class OnClickTopSong {
    public TopSongModel topSongModel;

    public OnClickTopSong(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }
}
