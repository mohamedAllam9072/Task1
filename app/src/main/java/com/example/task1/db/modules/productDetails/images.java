package com.example.task1.db.modules.productDetails;

import com.google.gson.annotations.SerializedName;

public class images {
    @SerializedName("id")
    private int id;
    @SerializedName("image")
    private String image;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
