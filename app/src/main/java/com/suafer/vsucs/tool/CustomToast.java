package com.suafer.vsucs.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.suafer.vsucs.R;

public class CustomToast {

    public static void showCustomToast(Context context, int duration, String text) {

        View layout = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);

        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(layout);
        TextView textView = layout.findViewById(R.id.text);
        textView.setText(text);

        toast.show();
        layout.post(() -> {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.popup_animation);
            layout.startAnimation(animation);
        });
    }

}