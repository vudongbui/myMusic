package com.example.owner.myMuzic.net;

import java.util.List;

/**
 * Created by Owner on 4/15/2018.
 */

public class MusicTypesResponse {
    public List<MusicTypeJSON> subgenres;

    public class MusicTypeJSON{
        public String id;
        public String translation_key;

        @Override
        public String toString() {
            return "MusicTypeJSON{" +
                    "id='" + id + '\'' +
                    ", translation_key='" + translation_key + '\'' +
                    '}';
        }
    }
}
