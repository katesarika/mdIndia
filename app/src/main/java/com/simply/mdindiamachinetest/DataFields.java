package com.simply.mdindiamachinetest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataFields {
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Fields> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Fields> getDataFields() {
        return data;
    }

    public void setDataFields(List<Fields> data) {
        this.data = data;
    }

}
