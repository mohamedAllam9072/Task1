package com.example.task1.db;

import com.example.task1.db.modules.home.Root;
import com.example.task1.db.modules.productDetails.RootProductDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiServices {
    @GET("home")
    Call<Root> getHome();

    @GET("product/{id}")
    Call<RootProductDetails> getProductDetails(@Path("id") int product_id);


}
