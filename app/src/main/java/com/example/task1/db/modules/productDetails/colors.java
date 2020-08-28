package com.example.task1.db.modules.productDetails;

import com.google.gson.annotations.SerializedName;

public class colors {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
