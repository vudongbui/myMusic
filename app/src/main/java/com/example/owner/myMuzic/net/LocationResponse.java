package com.example.owner.myMuzic.net;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Owner on 5/3/2018.
 */
@Root(name = "tracklist", strict = false)

public class LocationResponse {
    @Element(name = "track")
    public TrackXML trackXML;

}
