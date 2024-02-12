package com.suafer.vsucs.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.suafer.vsucs.R;
import com.suafer.vsucs.tool.AppStore;
import com.suafer.vsucs.tool.CustomScheduleAdapter;

public class AdditionalPage extends Fragment {

    private CustomScheduleAdapter adapter;
    private Animation mFadeInAnimation;
    private Intent intent = new Intent(Intent.ACTION_VIEW);

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page_additional, container, false);

        LinearLayout linear_schedule = rootView.findViewById(R.id.linear_schedule);
        LinearLayout linear_google_disk = rootView.findViewById(R.id.linear_google_disk);
        LinearLayout linear_base_answer = rootView.findViewById(R.id.linear_base_answer);
        LinearLayout linear_update = rootView.findViewById(R.id.linear_update);

        linear_schedule.setOnClickListener(view ->{
            String url = "https://docs.google.com/spreadsheets/d/1bKinOo7qlOW2zSFKqofkCZ9ufpCAesO964AUIHH7JEM/edit#gid=2090530136";
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        linear_google_disk.setOnClickListener(view ->{
            String url = "https://drive.google.com/drive/folders/1_DuKDkAMblO7nSir9n8hav6HGuTktZJl";
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        linear_base_answer.setOnClickListener(view ->{
            String url = "https://udvsu.narod.ru/1/";
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        linear_update.setOnClickListener(view ->{
            String url = "https://drive.google.com/drive/folders/1q3p9dmscSj6iKWwzy6iJAISRcHLkqr2Q";
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        return rootView;
    }
}