package com.example.owner.myMuzic.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Owner on 4/26/2018.
 */

public class TopSongResponse {
    public Feed feed;

    public class Feed{
        public List<Entry> entry;
    }
    public class Entry{
        @SerializedName("im:name")
        public Name name;
        @SerializedName("im:image")
        public List<Image> image;
        @SerializedName("im:artist")
        public Artist artist;

        public class Name{
            public String label;
        }
        public  class Image{
            public String label;
        }
        public class Artist{
            public String label;
        }
    }

}