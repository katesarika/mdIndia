package com.simply.mdindiamachinetest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.simply.mdindiamachinetest.databinding.ItemLayoutBinding;
import com.simply.mdindiamachinetest.ui.theme.OnRecyclerItemClicked;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    OnRecyclerItemClicked listener;
    private  List<Fields> list;
    Activity activity;
    private final int[] colors = {R.color.color_red, R.color.color_green, R.color.color_blue};


    public MyAdapter(OnRecyclerItemClicked listener,Activity activity, List<Fields> list) {
     this.activity=activity;
     this.listener=listener;
        if (list != null) {
            this.list = list;
        } else {
            this.list = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.item_layout, parent, false);
//        return new ViewHolder(view);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemLayoutBinding itemLayoutBinding = ItemLayoutBinding.inflate(inflater, parent, false);
        return new MyAdapter.ViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Fields fields = list.get(position);
        holder.binding.tvName.setText(fields.getHospitalName());
        holder.binding.tvAddress.setText(fields.getAddress());
        holder.binding.tvPin.setText(fields.getPinCode());
        holder.binding.tvContact.setText(fields.getE_Mail());
        holder.binding.imgMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecyclerItemClicked(list.get(position),position);
            }
        });


        int colorResId = colors[position % colors.length];
        holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), colorResId));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemLayoutBinding binding;
        ViewHolder(ItemLayoutBinding binding ) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
