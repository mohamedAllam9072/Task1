package com.example.task1.db.modules.productDetails;

import com.google.gson.annotations.SerializedName;

public class ProductDetails {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }
}

