package com.suafer.vsucs.tool;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.suafer.vsucs.R;
import com.suafer.vsucs.page.Page;

import java.util.List;

public class CustomFragmentAdapter extends ArrayAdapter {

    private final List<Page> list;
    private final Activity context;

    public CustomFragmentAdapter(List<Page> list, Activity context) {
        super(context, R.layout.custom_element_setting_fragment, list);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.custom_element_setting_fragment, null, true);

        ImageView image_status = row.findViewById(R.id.image_status);
        TextView text_name = row.findViewById(R.id.text_name);

        text_name.setText(AppStore.getPageName(list.get(position).getPageType()));
        if(!list.get(position).getStatus()){
            image_status.setImageResource(R.drawable.icon_null_box);
        }else{
            image_status.setImageResource(R.drawable.icon_ok_box);
        }
        return  row;
    }
}
