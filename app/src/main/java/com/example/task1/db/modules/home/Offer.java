package com.example.task1.db.modules.home;

import com.google.gson.annotations.SerializedName;

public class Offer {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("discount_price")
    private String discount_price;
    @SerializedName("image")
    private String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public String getImage() {
        return image;
    }
}
