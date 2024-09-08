package com.simply.mdindiamachinetest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responsess {
    @SerializedName("response")
    @Expose
    private DataFields data;

    public DataFields getData() {
        return data;
    }

    public void setData(DataFields response) {
        this.data = response;
    }

}


