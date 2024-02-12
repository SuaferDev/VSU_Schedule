package com.suafer.vsucs.tool;


import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.suafer.vsucs.R;

public class CustomBackgroundViewer extends RecyclerView.ViewHolder {
    public ImageView image_background;
    public CustomBackgroundViewer(View itemView) {
        super(itemView);
        image_background = itemView.findViewById(R.id.image_background);

    }
}