package com.suafer.vsucs.page;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.suafer.vsucs.MainActivity;
import com.suafer.vsucs.R;
import com.suafer.vsucs.schedule.Day;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class TimePage extends Fragment {

    private TextView textview_words, text_hours, text_minutes, text_seconds, text1, text2;
    Handler handler;
    int totalSeconds;


    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page_time, container, false);

        textview_words = rootView.findViewById(R.id.textview_words);
        text_hours = rootView.findViewById(R.id.text_hours);
        text_minutes = rootView.findViewById(R.id.text_minutes);
        text_seconds = rootView.findViewById(R.id.text_seconds);
        text1 = rootView.findViewById(R.id.text1);
        text2 = rootView.findViewById(R.id.text2);

        Calendar calendar = Calendar.getInstance();

        totalSeconds = (calendar.get(Calendar.HOUR_OF_DAY) * 3600) + (calendar.get(Calendar.MINUTE) * 60) + calendar.get(Calendar.SECOND);;


        handler = new Handler();
        handler.postDelayed(counterRunnable, 1000);

        return rootView;
    }

    private Runnable counterRunnable = new Runnable() {
        @Override
        public void run() {
            setTime();
            handler.postDelayed(this, 1000);
        }
    };

    private void setTime(){
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.THURSDAY) {
            setNoSubject();
        } else {
            totalSeconds = (calendar.get(Calendar.HOUR_OF_DAY) * 3600) + (calendar.get(Calendar.MINUTE) * 60) + calendar.get(Calendar.SECOND);;
            int id = getIdByDay(dayOfWeek);


            Day day = MainActivity.setting.getSchedule().getDaysByDayType(MainActivity.setting.getDayType()).get(id);

            int[] sunj = getSubjectByTime();

            if(sunj[0]==-2){

                setNoSubject();
            }else{
                if(sunj[0]==-1){
                    textview_words.setText(String.valueOf("До конца перемены"));
                    secondsToTime(sunj[1]);
                }else{
                    if(day.getSubject(sunj[0])[0]!=null && day.getSubject(sunj[0])[0].length()<2){
                        setNoSubject();
                    }else{
                        textview_words.setText(String.valueOf("До конца пары"));
                        secondsToTime(sunj[1]);
                    }
                }
            }
        }
/*
        totalSeconds = (calendar.get(Calendar.HOUR_OF_DAY) * 3600) + (calendar.get(Calendar.MINUTE) * 60) + calendar.get(Calendar.SECOND);;
        if(totalSeconds<(8*3600) || totalSeconds > (21*3600+30*60)){
            textview_words.setText(String.valueOf("Сейчас пар нет"));
            return;
        }

        if(totalSeconds>=(8*3600) && totalSeconds<(9*3600+35*60)){
            textview_words.setText(String.valueOf("До конца пары"));
            secondsToTime(9*3600+35*60);
            return;
        }
        if(totalSeconds>=(9*3600+35*60) && totalSeconds<(9*3600+45*60)){
            textview_words.setText(String.valueOf("До конца перемены"));
            secondsToTime(9*3600+45*60);
            return;
        }

        if(totalSeconds>=(9*3600+45*60) && totalSeconds<(11*3600+20*60)){
            textview_words.setText(String.valueOf("До конца пары"));
            secondsToTime(11*3600+20*60);
            return;
        }
        if(totalSeconds>=(11*3600+20*60) && totalSeconds<(11*3600+30*60)){
            textview_words.setText(String.valueOf("До конца перемены"));
            secondsToTime(11*3600+30*60);
            return;
        }

        if(totalSeconds>=(11*3600+30*60) && totalSeconds<(13*3600+5*60)){
            textview_words.setText(String.valueOf("До конца пары"));
            secondsToTime(13*3600+5*60);
            return;
        }
        if(totalSeconds>=(13*3600+5*60) && totalSeconds<(13*3600+25*60)){
            textview_words.setText(String.valueOf("До конца перемены"));
            secondsToTime(13*3600+25*60);
            return;
        }

        if(totalSeconds>=(13*3600+25*60) && totalSeconds<(15*3600)){
            textview_words.setText(String.valueOf("До конца пары"));
            secondsToTime(15*3600);
            return;
        }
        if(totalSeconds>=(15*3600+1) && totalSeconds<(15*3600+10*60)){
            textview_words.setText(String.valueOf("До конца перемены"));
            secondsToTime(15*3600+10*60);
            return;
        }

        if(totalSeconds>=(15*3600+10*60) && totalSeconds<(16*3600+45*60)){
            textview_words.setText(String.valueOf("До конца пары"));
            secondsToTime(16*3600+45*60);
            return;
        }
        if(totalSeconds>=(16*3600+45*60) && totalSeconds<(16*3600+55*60)){
            textview_words.setText(String.valueOf("До конца перемены"));
            secondsToTime(16*3600+55*60);
            return;
        }

        if(totalSeconds>=(16*3600+55*60) && totalSeconds<(18*3600+30*60)){
            textview_words.setText(String.valueOf("До конца пары"));
            secondsToTime(18*3600+30*60);
            return;
        }
        if(totalSeconds>=(18*3600+30*60) && totalSeconds<(18*3600+40*60)){
            textview_words.setText(String.valueOf("До конца перемены"));
            secondsToTime(18*3600+40*60);
            return;
        }

        if(totalSeconds>=(18*3600+40*60) && totalSeconds<(20*3600)){
            textview_words.setText(String.valueOf("До конца пары"));
            secondsToTime(20*3600);
            return;
        }
        if(totalSeconds>=(20*3600) && totalSeconds<(20*3600+10*60)){
            textview_words.setText(String.valueOf("До конца перемены"));
            secondsToTime(20*3600+10*60);
            return;
        }

        if(totalSeconds>=(20*3600+10*60) && totalSeconds<(21*3600+30*60)){
            textview_words.setText(String.valueOf("До конца пары"));
            secondsToTime(21*3600+30*60);
        }*/
    }

    private void setNoSubject(){
        textview_words.setText(String.valueOf("Сейчас пар нет"));
        text_hours.setVisibility(View.INVISIBLE);
        text_minutes.setVisibility(View.INVISIBLE);
        text_seconds.setVisibility(View.INVISIBLE);
        text1.setVisibility(View.INVISIBLE);
        text2.setVisibility(View.INVISIBLE);
        text_hours.setWidth(0);text_hours.setHeight(0);
        text_minutes.setWidth(0);text_minutes.setHeight(0);
        text_seconds.setWidth(0);text_seconds.setHeight(0);
        text1.setWidth(0);text1.setHeight(0);
        text2.setWidth(0);text2.setHeight(0);
    }

    private void setSubjectTime(){
        text_hours.setVisibility(View.VISIBLE);
        text_minutes.setVisibility(View.VISIBLE);
        text_seconds.setVisibility(View.VISIBLE);
        text1.setVisibility(View.VISIBLE);
        text2.setVisibility(View.VISIBLE);
        text_hours.setWidth(0);text_hours.setHeight(0);
        text_minutes.setWidth(0);text_minutes.setHeight(0);
        text_seconds.setWidth(0);text_seconds.setHeight(0);
        text1.setWidth(0);text1.setHeight(0);
        text2.setWidth(0);text2.setHeight(0);
    }

    private int getIdByDay(int dayOfWeek){
        if(dayOfWeek==Calendar.MONDAY){
            return 0;
        }
        if(dayOfWeek==Calendar.THURSDAY){
            return 1;
        }
        if(dayOfWeek==Calendar.WEDNESDAY ){
            return 2;
        }
        return 4;
    }

    private int[] getSubjectByTime(){
        if(totalSeconds<(8*3600) || totalSeconds > (20*3600)){
            return new int[]{-2,0};
        }else{
            if(totalSeconds>=(8*3600) && totalSeconds<(9*3600+35*60)){return new int[]{0,(9*3600+35*60)} ;}

            if(totalSeconds>=(9*3600+35*60) && totalSeconds<(9*3600+45*60)){return new int[]{-1,(9*3600+45*60)};}

            if(totalSeconds>=(9*3600+45*60) && totalSeconds<(11*3600+20*60)){return new int[]{1,(11*3600+20*60)} ;}

            if(totalSeconds>=(11*3600+20*60) && totalSeconds<(11*3600+30*60)){return new int[]{-1, (11*3600+30*60)};}

            if(totalSeconds>=(11*3600+30*60) && totalSeconds<(13*3600+5*60)){return new int[]{2,(13*3600+5*60)};}

            if(totalSeconds>=(13*3600+5*60) && totalSeconds<(13*3600+25*60)){return new int[]{-1,(13*3600+25*60)};}

            if(totalSeconds>=(13*3600+25*60) && totalSeconds<(15*3600)){return new int[]{3, (15*3600)};}

            if(totalSeconds>=(15*3600) && totalSeconds<(15*3600+10*60)){return new int[]{-1, (15*3600+10*60)};}

            if(totalSeconds>=(15*3600+10*60) && totalSeconds<(16*3600+45*60)){return new int[]{4,(16*3600+45*60)};}

            if(totalSeconds>=(16*3600+45*60) && totalSeconds<(16*3600+55*60)){
                return new int[]{-1, (16*3600+55*60)};
            }

            if(totalSeconds>=(16*3600+55*60) && totalSeconds<(18*3600+30*60)){
                return new int[]{5, (18*3600+30*60)};
            }
            if(totalSeconds>=(18*3600+30*60) && totalSeconds<(18*3600+40*60)){
                return new int[]{-1, (18*3600+40*60)};
            }

            if(totalSeconds>=(18*3600+40*60) && totalSeconds<(20*3600)){
                return new int[]{6, (20*3600)};
            }

            return new int[]{-2,0};
        }
    }

    private void secondsToTime(int lastSeconds){
        int waitsecond = lastSeconds - totalSeconds;
        int hours = waitsecond / 3600;
        int minutes = (waitsecond % 3600) / 60;
        int seconds = waitsecond % 60;
        text_hours.setText(String.valueOf(setPrettyValue(hours)));
        text_minutes.setText(String.valueOf(setPrettyValue(minutes)));
        text_seconds.setText(String.valueOf(setPrettyValue(seconds)));
    }

    private String setPrettyValue(int value){
        if(value>=10){
            return String.valueOf(value);
        }else{
            return String.valueOf("0"+value);
        }
    }
}
