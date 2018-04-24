package com.example.owner.myMuzic.databasees;

import java.io.Serializable;

/**
 * Created by Owner on 4/15/2018.
 */

public class MusicTypeModel implements Serializable{
    public String id;
    public String name;
    public int imageID;

    public MusicTypeModel(String id, String name, int imageID) {
        this.id = id;
        this.name = name;
        this.imageID = imageID;
    }
}
