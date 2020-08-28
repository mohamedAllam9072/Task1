package com.example.task1.ui.ProductDetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task1.db.ApiClient;
import com.example.task1.db.modules.home.Banner;
import com.example.task1.db.modules.productDetails.Related;
import com.example.task1.db.modules.productDetails.RootProductDetails;
import com.example.task1.db.modules.productDetails.colors;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsViewModel extends ViewModel {

    private static final String TAG = "ProductDetailsViewModel";
    MutableLiveData<List<Related>> related = new MutableLiveData<>();
    MutableLiveData<String> bio = new MutableLiveData<>();
    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<String> price = new MutableLiveData<>();
    MutableLiveData<Integer> rate = new MutableLiveData<>();
    MutableLiveData<List<colors>> colors = new MutableLiveData<>();
    MutableLiveData<List<Banner>> images = new MutableLiveData<>();

    public ProductDetailsViewModel() {

    }

//    public void getPD1() {
//        ApiClient.getINSTANCE().getProductDetails1().enqueue(new Callback<RootProductDetails>() {
//            @Override
//            public void onResponse(Call<RootProductDetails> call, Response<RootProductDetails> response) {
//                related.setValue(response.body().getData().getRelated());
//                bio.setValue(response.body().getData().getProduct().getBio());
//                colors.setValue(response.body().getData().getProduct().getColors());
//                name.setValue(response.body().getData().getProduct().getName());
//                price.setValue(response.body().getData().getProduct().getPrice());
//                rate.setValue(response.body().getData().getProduct().getRate());
//                images.setValue(response.body().getData().getProduct().getImages());
//            }
//
//            @Override
//            public void onFailure(Call<RootProductDetails> call, Throwable t) {
//            }
//        });
//    }

    public void getProductDetails(int ProductID) {
        ApiClient.getINSTANCE().getProductDetails(ProductID).enqueue(new Callback<RootProductDetails>() {
            @Override
            public void onResponse(Call<RootProductDetails> call, Response<RootProductDetails> response) {
                related.setValue(response.body().getData().getRelated());
                bio.setValue(response.body().getData().getProduct().getBio());
                colors.setValue(response.body().getData().getProduct().getColors());
                name.setValue(response.body().getData().getProduct().getName());
                price.setValue(response.body().getData().getProduct().getPrice());
                rate.setValue(response.body().getData().getProduct().getRate());
                images.setValue(response.body().getData().getProduct().getImages());
            }

            @Override
            public void onFailure(Call<RootProductDetails> call, Throwable t) {
            }
        });
    }

}