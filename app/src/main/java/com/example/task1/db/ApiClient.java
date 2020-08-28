package com.example.task1.db;


import com.example.task1.db.modules.home.Home;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://testsore.online/JiV4/api/";
    private static ApiClient INSTANCE;
    private ApiServices apiServices;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);
    }

    public static ApiClient getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }

    public Call<Home> getHome() {
        return apiServices.getHome();
    }


//    public Call<String> getState() {
//        return apiServices.getStatus();
//    }
//
//    public Call<Sources> getSources() {
//        return apiServices.getSources();
//    }
//
//    public Call<Headline> getHeadlineWithSource(String source, String apiKey) {
//        return apiServices.getHeadLineWithSource(source, apiKey);
//    }

}
