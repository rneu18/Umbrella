package com.example.umbrella;

import android.content.Context;
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
        //Context context = currentViewHolder.currentImage.getContext();

     //  List<String> item = items.get(0).get(i);
           currentViewHolder.currentTime.setText("Time: "+ items.get(0).get(i*3));
           currentViewHolder.currentTemp.setText("Temp: "+items.get(0).get(i*3+2));
           String imageUri = "openweathermap.org/img/w/10d.png";
           //items.get(0).get(i*3+1)
        try{
            Picasso.with(context).load(imageUri).into(currentViewHolder.currentImage);
        }catch (Exception e) {

        }


//        if (Integer.parseInt(items.get(0).get(i*3+2)) < 288.0){
//         //   currentViewHolder.topLayout.setBackgroundColor(context.getResources().getColor(R.color.colorDBlue));
//
//        }
//        if (Integer.parseInt(items.get(0).get(i*3+2)) > 303.0){
//           // currentViewHolder.topLayout.setBackgroundColor(context.getResources().getColor(R.color.colorOrange));
//
//        }
//
//        if (Integer.parseInt(items.get(0).get(i*3+2)) <= 303.0
//                && Integer.parseInt(items.get(0).get(i*3+2)) >= 288.0){
//          //  currentViewHolder.topLayout.setBackgroundColor(context.getResources().getColor(R.color.colorMyGrey));
//        }






    }

    @Override
    public int getItemCount() {
        return items.get(0).size()/3;

    }

    public class CurrentViewHolder extends RecyclerView.ViewHolder{

        ImageView currentImage;
        TextView currentTime;
        TextView currentTemp;
       // LinearLayout topLayout;



        public CurrentViewHolder(View itemView) {
            super(itemView);
          //  topLayout = (LinearLayout) itemView.findViewById(R.id.details_today);
            currentImage = (ImageView) itemView.findViewById(R.id.today_icon);
            currentTemp = (TextView) itemView.findViewById(R.id.today_temp);
            currentTime = (TextView) itemView.findViewById(R.id.today_time);
        }
    }
}
