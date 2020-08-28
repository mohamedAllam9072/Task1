package com.example.task1.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task1.db.ApiClient;
import com.example.task1.db.modules.home.Banner;
import com.example.task1.db.modules.home.Home;
import com.example.task1.db.modules.productDetails.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    MutableLiveData<ArrayList<Banner>> banner = new MutableLiveData<>();
    MutableLiveData<String> statue = new MutableLiveData<>();
    MutableLiveData<ArrayList<Product>> products = new MutableLiveData<>();

    public HomeViewModel() {

    }

    public void getHome() {
        ApiClient.getINSTANCE().getHome().enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                statue.setValue(response.body().getMessage());
//                products.setValue(response.body().getData().getProducts());
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {


            }
        });
    }
}