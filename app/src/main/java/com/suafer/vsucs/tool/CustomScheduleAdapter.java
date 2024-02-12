package com.suafer.vsucs.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.suafer.vsucs.R;
import com.suafer.vsucs.schedule.Day;

import java.util.List;

public class CustomScheduleAdapter extends ArrayAdapter {

    private final List<Day> week;
    private Context mContext;

    public CustomScheduleAdapter(Context context, List<Day> week) {
        super(context, R.layout.custom_element_schedule, week);
        this.week = week;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_element_schedule, parent, false);
        }
        TextView text_day_name = view.findViewById(R.id.text_day_name);
        text_day_name.setText(week.get(position).getName());
        
        
        TextView text_subject1 = view.findViewById(R.id.text_subject1); TextView text_subject1_cabinet = view.findViewById(R.id.text_subject1_cabinet);
        TextView text_subject2 = view.findViewById(R.id.text_subject2); TextView text_subject2_cabinet = view.findViewById(R.id.text_subject2_cabinet);
        TextView text_subject3 = view.findViewById(R.id.text_subject3); TextView text_subject3_cabinet = view.findViewById(R.id.text_subject3_cabinet);
        TextView text_subject4 = view.findViewById(R.id.text_subject4); TextView text_subject4_cabinet = view.findViewById(R.id.text_subject4_cabinet);
        TextView text_subject5 = view.findViewById(R.id.text_subject5); TextView text_subject5_cabinet = view.findViewById(R.id.text_subject5_cabinet);
        TextView text_subject6 = view.findViewById(R.id.text_subject6); TextView text_subject6_cabinet = view.findViewById(R.id.text_subject6_cabinet);
        TextView text_subject7 = view.findViewById(R.id.text_subject7); TextView text_subject7_cabinet = view.findViewById(R.id.text_subject7_cabinet);
        text_day_name.setText(AppStore.getDayName(position));

        setDay(text_subject1,text_subject1_cabinet,  week.get(position),0);
        setDay(text_subject2,text_subject2_cabinet, week.get(position),1);
        setDay(text_subject3, text_subject3_cabinet, week.get(position),2);
        setDay(text_subject4, text_subject4_cabinet, week.get(position),3);
        setDay(text_subject5, text_subject5_cabinet, week.get(position),4);
        setDay(text_subject6, text_subject6_cabinet,week.get(position),5);
        setDay(text_subject7, text_subject7_cabinet, week.get(position),6);
        return view;
    }
    
    private void setDay(TextView textView, TextView cabinet,  Day day, int id){
        if(day.getSubject(id)[0]==null){
            textView.setText("-");
        }else{
            textView.setText(day.getSubject(id)[0]);
        }
        if(day.getSubject(id)[1]==null){
            cabinet.setText("");
        }else{
            cabinet.setText(day.getSubject(id)[1]);
        }
    }
}
