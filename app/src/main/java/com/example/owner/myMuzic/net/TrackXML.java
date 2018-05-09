package com.example.owner.myMuzic.net;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Owner on 5/3/2018.
 */

@Root(name = "track", strict = false)
public class TrackXML {
    @Element(name = "location")
    public String location;
}
