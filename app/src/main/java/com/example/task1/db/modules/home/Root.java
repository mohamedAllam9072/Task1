package com.example.task1.db.modules.home;

import com.google.gson.annotations.SerializedName;

public class Root {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }
}
