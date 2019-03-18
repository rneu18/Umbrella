package com.example.umbrella.Pojo;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.umbrella.R;

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.CurrentViewHolder> {
    private Context context;
    private ArrayList<ArrayList<String>> items;

    public CurrentAdapter(Context context, ArrayList<ArrayList<String>> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public CurrentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new CurrentViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CurrentViewHolder currentViewHolder, int i) {
        ArrayList<String> item = items.get(i);
        currentViewHolder.currentTime.setText(items.get(i).toString());
        currentViewHolder.currentTemp.setText(items.get(i).toString());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CurrentViewHolder extends RecyclerView.ViewHolder{
        ImageView currentImage;
        TextView currentTime;
        TextView currentTemp;



        public CurrentViewHolder(View itemView) {
            super(itemView);
            currentImage = (ImageView) itemView.findViewById(R.id.today_icon);
            currentTemp = (TextView) itemView.findViewById(R.id.details_today);
            currentTime = (TextView) itemView.findViewById(R.id.today_time);
        }
    }
}
