package com.example.newsing;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class newsAdapter extends ArrayAdapter<news> {

    public newsAdapter(Context context, ArrayList<news> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        news n1 = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item_view,parent,false);
        }

        TextView headline = (TextView) convertView.findViewById(R.id.TitleTextViewOfitem);
        TextView description = (TextView) convertView.findViewById(R.id.DescriptionTextViewOfitem);

        headline.setText(n1.getHeadLine());
        description.setText(n1.getDescription());

        return convertView;
    }
}
