package com.example.umbrella;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
       // Context context = currentViewHolder.currentImage.getContext();

     //  List<String> item = items.get(0).get(i);
           currentViewHolder.currentTime.setText("Time: "+ items.get(0).get(i*3));
           currentViewHolder.currentTemp.setText("Temp: "+items.get(0).get(i*3+2));
           String imageUri = "https://openweathermap.org/img/w/"+items.get(0).get(i*3+1)+".png";

           //items.get(0).get(i*3+1)
        try{
            Picasso.with(currentViewHolder.itemView.getContext()).load(imageUri).into(currentViewHolder.currentImage);
            System.out.println("try");
        }catch (Exception e) {

        }

        String array1[]= items.get(0).get(i*3+2).split(" ");
        double temp = Double.parseDouble(array1[0]);
        String unit = array1[1];
        if (unit.equals("°C")){
            if (temp < 15.0){

                currentViewHolder.cardLayout.setBackgroundColor(Color.parseColor("#1e90ff"));


            }
            if (temp > 30.0){

                currentViewHolder.cardLayout.setBackgroundColor(Color.parseColor("#ffa500"));

            }

            if (temp <= 86.0
                    && temp >= 15.0){
                currentViewHolder.cardLayout.setBackgroundColor(Color.parseColor("#38CDB3"));
            }

        }else {
            if (temp < 59.0){
                currentViewHolder.cardLayout.setBackgroundColor(Color.parseColor("#1e90ff"));

            }
            if (temp > 86.0){
                currentViewHolder.cardLayout.setBackgroundColor(Color.parseColor("#ffa500"));

            }

            if (temp <= 86.0
                    && temp >= 59.0){
                currentViewHolder.cardLayout.setBackgroundColor(Color.parseColor("#38CDB3"));
                //currentViewHolder.currentTemp.setTextColor(Color.parseColor("#8b8989"));
            }

        }


    }

    @Override
    public int getItemCount() {
        return items.get(0).size()/3;

    }

    public class CurrentViewHolder extends RecyclerView.ViewHolder{

        ImageView currentImage;
        TextView currentTime;
        TextView currentTemp;
        LinearLayoutCompat cardLayout;



        public CurrentViewHolder(View itemView) {
            super(itemView);
            cardLayout = (LinearLayoutCompat) itemView.findViewById(R.id.details_today);
            currentImage = (ImageView) itemView.findViewById(R.id.today_icon);
            currentTemp = (TextView) itemView.findViewById(R.id.today_temp);
            currentTime = (TextView) itemView.findViewById(R.id.today_time);
        }
    }
}
