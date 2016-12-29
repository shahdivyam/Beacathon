package com.example.ayush.resturantninjas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ayush on 29-12-2016.
 */

public class RVFoodAdapter extends RecyclerView.Adapter<RVFoodAdapter.FoodViewHolder>{

        List<FoodItem> fooditem;
        public static Context context;
        RVFoodAdapter(Context context, List<FoodItem> fooditem)
        {
            this.context=context;
            this. fooditem= fooditem;
        }
    public static class FoodViewHolder extends RecyclerView.ViewHolder{
        TextView productname;
        TextView rate;
        TextView ratemoney;
        ImageView productImage;
        ImageView addtoCart;

        FoodViewHolder(final View itemView) {
            super(itemView);
            productname=(TextView)itemView.findViewById(R.id.ProductName);
            rate=(TextView)itemView.findViewById(R.id.Rate);
            ratemoney=(TextView)itemView.findViewById(R.id.RateMoeny);
            productImage=(ImageView)itemView.findViewById(R.id.ProductImage);
            addtoCart=(ImageView)itemView.findViewById(R.id.AddToCart);

        }

    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_menu, viewGroup, false);
        RVFoodAdapter.FoodViewHolder pvh = new FoodViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int i) {
        holder.productname.setText(fooditem.get(i).foodname);
        holder.ratemoney.setText(String.valueOf(fooditem.get(i).Price));
        holder.productImage.setImageResource(R.drawable.bebe);

    }

    @Override
    public int getItemCount() {
        return fooditem.size();
    }




}
