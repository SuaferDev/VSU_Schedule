package com.suafer.vsucs.tool;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suafer.vsucs.MainActivity;
import com.suafer.vsucs.R;

import java.util.List;

public class CustomBackgroundAdapter extends RecyclerView.Adapter<CustomBackgroundViewer> {
    private final int[] itemList;
    private final Context context;

    public CustomBackgroundAdapter(int[] itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomBackgroundViewer onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CustomBackgroundViewer(inflater.inflate(R.layout.custom_element_background, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomBackgroundViewer holder, int position) {
        holder.image_background.setImageResource(itemList[position]);
        holder.image_background.setOnClickListener(view ->{
            MainActivity.setting.setBackground(position);
            MainActivity.setting.setPathBackground(null);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.length;
    }
}