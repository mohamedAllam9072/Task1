package com.example.task1.db.modules.productDetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("bio")
    private String bio;
    @SerializedName("image")
    private String image;
    @SerializedName("price")
    private String price;
    @SerializedName("discount_price")
    private String discount_price;
    @SerializedName("rate")
    private int rate;
    @SerializedName("isOffer")
    private boolean isOffer;
    @SerializedName("images")
    private List<images> images;
    @SerializedName("colors")
    private List<colors> colors;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public int getRate() {
        return rate;
    }

    public boolean isOffer() {
        return isOffer;
    }

    public List<com.example.task1.db.modules.productDetails.images> getImages() {
        return images;
    }

    public List<com.example.task1.db.modules.productDetails.colors> getColors() {
        return colors;
    }
}

