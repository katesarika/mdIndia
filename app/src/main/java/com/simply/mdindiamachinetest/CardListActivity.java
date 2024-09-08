package com.simply.mdindiamachinetest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.simply.mdindiamachinetest.databinding.ActivityCardListBinding;
import com.simply.mdindiamachinetest.ui.theme.OnRecyclerItemClicked;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CardListActivity extends AppCompatActivity implements OnRecyclerItemClicked {
    ActivityCardListBinding binding;
    ApiInterface apiInterface;
    List<Fields> list;
    CheckNetworkConnection checkNetworkConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_list);
        checkNetworkConnection = new CheckNetworkConnection(this);

        if (checkNetworkConnection.checkConnectivity()) {
            getList();
        } else {
            Toast.makeText(this, "Please check internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapter(List<Fields> list) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new MyAdapter(this, this, list));

    }

    private void getList() {
        list = new ArrayList<>();
        binding.progressBar.setVisibility(View.VISIBLE);
        apiInterface = RetrofitClient.getInstance().create(ApiInterface.class);
        apiInterface.downloadData().enqueue(new Callback<Responsess>() {
            @Override
            public void onResponse(Call<Responsess> call, Response<Responsess> response) {
                if (response.isSuccessful()) {
                    Responsess responsess = response.body();
                    if (responsess != null && responsess.getData() != null) {
                        String status = responsess.getData().getStatus();
                        DataFields dataFields = responsess.getData();
                        list = dataFields.getDataFields();

                        Log.d("TAG", "Status: " + status);

//                       for (int i=0;i<list.size();i++)
//                        {
//                            String hospitalName=list.get(i).getHospitalName();
//                            String HospitalAddress=list.get(i).getHospitalAddress();
//                            String PinCode=list.get(i).getPinCode();
//                            String email=list.get(i).getE_Mail();
//  Log.d("TAG", "Response : " + hospitalName);
//                        }
                        binding.progressBar.setVisibility(View.GONE);
                        setAdapter(list);

                    }
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Log.e("TAG", "Response error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Responsess> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Log.e("TAG", "Request failed: " + t.getMessage());
            }
        });
    }

    @Override
    public void onRecyclerItemClicked(Object item, int position) {
        Fields fields = (Fields) item;
        double latitude = Double.parseDouble(fields.getLatitude().trim());
        double longitude = Double.parseDouble(fields.getLongitude().trim());
        Log.d("Map lat", latitude + "");

        redirectOnMap(latitude, longitude);


    }

    private void redirectOnMap(double latitude, double longitude) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}




