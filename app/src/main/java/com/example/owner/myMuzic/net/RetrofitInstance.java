package com.example.owner.myMuzic.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Owner on 4/15/2018.
 */

public class RetrofitInstance {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if(retrofit ==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://music-api-for-tk.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
