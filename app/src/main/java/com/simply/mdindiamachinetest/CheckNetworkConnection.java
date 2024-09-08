package com.simply.mdindiamachinetest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

public class CheckNetworkConnection {

    Context context;

    public CheckNetworkConnection(Context context) {
        this.context = context;
    }

    public void getInstance(){
        new CheckNetworkConnection(context);
    }
    public boolean checkConnectivity() {
        boolean status =false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();


            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                status= true;
            }
            else {
                status= false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }

}
