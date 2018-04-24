package com.example.owner.myMuzic.net;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Owner on 4/15/2018.
 */

public interface MusicService {
    @GET("api")
    Call<MusicTypesResponse> getListMusicTypes();
}
