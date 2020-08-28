package com.example.task1.db.modules.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("banner")
    private List<Banner> Banner;
    @SerializedName("categories")
    private List<Category> Categories;
    @SerializedName("products")
    private List<h_Product> Products;
    @SerializedName("offers")
    private List<Offer> Offers;

    public List<Banner> getBanner() {
        return Banner;
    }

    public List<Category> getCategories() {
        return Categories;
    }

    public List<h_Product> getProducts() {
        return Products;
    }

    public List<Offer> getOffers() {
        return Offers;
    }
}
