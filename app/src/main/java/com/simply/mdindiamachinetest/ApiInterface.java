package com.simply.mdindiamachinetest;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("getLocationList?statename=Maharashtra&amp;district=Pune&amp;cityname=Pune&amp;MobileUniqId=0000002")
    Call<Responsess> downloadData();

}
