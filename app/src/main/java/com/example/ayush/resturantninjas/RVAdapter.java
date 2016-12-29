package com.example.ayush.resturantninjas;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ayush on 29-12-2016.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView resturantname;
        TextView resturantdistance;
        ImageView resturantPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            resturantname = (TextView)itemView.findViewById(R.id.name);
            resturantdistance = (TextView)itemView.findViewById(R.id.distance);
            resturantPhoto = (ImageView)itemView.findViewById(R.id.resturantphoto);
        }
    }

    List<Resturant> resturants;

    RVAdapter(List<Resturant> resturants){
        this.resturants = resturants;
    }
    @Override
    public RVAdapter.PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resturant_ui, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;

    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.resturantname.setText(resturants.get(i).name);
        personViewHolder.resturantdistance.setText(String.valueOf(resturants.get(i).distance));
        personViewHolder.resturantPhoto.setImageResource(R.drawable.bebe);
    }
    @Override
    public int getItemCount() {
        return resturants.size();
    }
}
