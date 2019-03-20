package com.example.umbrella;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.CurrentViewHolder> {
    private Context context;
    private List<List<String>> items;

    public CurrentAdapter(Context context, List<List<String>> items) {
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
       // List<String> item = items.get(i);
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
            currentTemp = (TextView) itemView.findViewById(R.id.today_temp);
            currentTime = (TextView) itemView.findViewById(R.id.today_time);
        }
    }
}