package com.example.task1.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task1.db.ApiClient;
import com.example.task1.db.modules.home.Banner;
import com.example.task1.db.modules.home.Category;
import com.example.task1.db.modules.home.Offer;
import com.example.task1.db.modules.home.Root;
import com.example.task1.db.modules.home.h_Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";
    MutableLiveData<List<Category>> categories = new MutableLiveData<>();
    MutableLiveData<List<Banner>> banners = new MutableLiveData<>();
    MutableLiveData<List<h_Product>> products = new MutableLiveData<>();
    MutableLiveData<List<Offer>> offers = new MutableLiveData<>();

    public HomeViewModel() {
    }

    public void getHomeData() {
        ApiClient.getINSTANCE().getHome().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                banners.setValue(response.body().getData().getBanner());
                categories.setValue(response.body().getData().getCategories());
                products.setValue(response.body().getData().getProducts());
                offers.setValue(response.body().getData().getOffers());


            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.d(TAG, "allam onCreateView: " + t.getMessage());

            }
        });
    }

}