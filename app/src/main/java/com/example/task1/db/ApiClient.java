package com.example.task1.db;


import com.example.task1.db.modules.home.Root;
import com.example.task1.db.modules.productDetails.RootProductDetails;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://testsore.online/JiV4/api/";
    private ApiServices apiServices;
    private static ApiClient INSTANCE;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);
    }

    public static ApiClient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }

    public Call<Root> getHome() {
        return apiServices.getHome();
    }

    public Call<RootProductDetails> getProductDetails(int ProductID) {
        return apiServices.getProductDetails(ProductID);
    }

}
