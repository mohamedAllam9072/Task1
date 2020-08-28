package com.example.task1.db;

import com.example.task1.db.modules.home.Home;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiServices {
    @GET("home")
    Call<Home> getHome();

//    //https://newsapi.org/v2/
//    @GET("top-headlines?country=us/status&apiKey=331303f4d4a7435fa9ee432a8ab905ed")
//    Call<String> getStatus();
//
//    @GET("sources?apiKey=331303f4d4a7435fa9ee432a8ab905ed")
//    Call<Sources> getSources();
//
//    @GET("top-headlines")
//    Call<Headline> getHeadLineWithSource(@Query("sources") String source, @Query("apiKey") String apiKey);
//    //bbc-news
}
