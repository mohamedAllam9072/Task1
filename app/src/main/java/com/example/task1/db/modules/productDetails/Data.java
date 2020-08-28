package com.example.task1.db.modules.productDetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("product")
    private Product product;
    @SerializedName("related")
    private List<Related> related;

    public Product getProduct() {
        return product;
    }

    public List<Related> getRelated() {
        return related;
    }
}
