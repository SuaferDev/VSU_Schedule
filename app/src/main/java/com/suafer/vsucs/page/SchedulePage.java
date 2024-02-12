package com.suafer.vsucs.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.suafer.vsucs.MainActivity;
import com.suafer.vsucs.enumpack.DayType;
import com.suafer.vsucs.tool.CustomScheduleAdapter;
import com.suafer.vsucs.R;
import com.suafer.vsucs.tool.AppStore;

public class SchedulePage extends Fragment {

    private CustomScheduleAdapter adapter;
    private Animation mFadeInAnimation;
    private TextView textview_nominator, textview_denominator;
    private ListView listView;


    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page_schedule, container, false);

        ImageView image_menu = rootView.findViewById(R.id.image_menu);
        listView = rootView.findViewById(R.id.listView);
        textview_nominator = rootView.findViewById(R.id.textview_nominator);
        textview_denominator = rootView.findViewById(R.id.textview_denominator);


        if(MainActivity.setting.getDayType()==DayType.Numerator){
            setChoose(textview_nominator, textview_denominator, rootView);
        }else{
            setChoose(textview_denominator, textview_nominator, rootView);
        }

        adapter = new CustomScheduleAdapter(rootView.getContext(), MainActivity.setting.getSchedule().getDaysByDayType(MainActivity.setting.getDayType()));
        listView.setAdapter(adapter);

        textview_denominator.setOnClickListener(view -> {
            MainActivity.setting.setDayType(DayType.Denominator);
            setChoose(textview_denominator, textview_nominator, rootView);
        });

        textview_nominator.setOnClickListener(view ->{
            MainActivity.setting.setDayType(DayType.Numerator);
            setChoose(textview_nominator, textview_denominator, rootView);
        });

        return rootView;
    }

    Animation.AnimationListener animationFadeInListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {}

        @Override
        public void onAnimationRepeat(Animation animation) {}

        @Override
        public void onAnimationStart(Animation animation) {}
    };

    private void setChoose(TextView textViewChoose, TextView textViewUnChoose, ViewGroup viewGroup){
        textViewChoose.setTextColor(0xFF212121);
        textViewChoose.setBackgroundResource(R.drawable.background1234);

        textViewUnChoose.setBackgroundColor(0xFF212121);
        textViewUnChoose.setTextColor(0xFFFFFFFF);

        adapter = new CustomScheduleAdapter(viewGroup.getContext(), MainActivity.setting.getSchedule().getDaysByDayType(MainActivity.setting.getDayType()));
        mFadeInAnimation = AnimationUtils.loadAnimation(viewGroup.getContext(), R.anim.fade_in);
        mFadeInAnimation.setAnimationListener(animationFadeInListener);
        listView.startAnimation(mFadeInAnimation);
        listView.setAdapter(adapter);
    }
}
