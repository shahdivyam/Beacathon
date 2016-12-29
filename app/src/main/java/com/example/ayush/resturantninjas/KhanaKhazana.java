package com.example.ayush.resturantninjas;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class KhanaKhazana extends AppCompatActivity  implements RVFoodAdapter.ClickListner {
    public List<FoodItem> fooditem;
    Context context;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        context = this;
        Intent intent = getIntent();
        int position = intent.getIntExtra("Position", -1);
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        GridLayoutManager llm = new GridLayoutManager(this, 2);
        rv.setLayoutManager(llm);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Order> order = db.getAllOrder();
                for (Order cn : order) {
                    String log = "Id: "+cn.foodname+" ,Name: " + cn.stallname + " ,Phone: " +
                            cn.qty;
                    Log.d("Name: ", log);
                }
            }
        });
        fooditem = new ArrayList<>();
        fooditem.add(new FoodItem("Rajma Chawal", 100, "lalalala"));
        fooditem.add(new FoodItem("Dal Makhni", 100, "lalalala"));
        fooditem.add(new FoodItem("Naan", 100, "lalalala"));
        fooditem.add(new FoodItem("Chole Chawal", 100, "lalalala"));
        fooditem.add(new FoodItem("Chowmien", 100, "lalalala"));
        fooditem.add(new FoodItem("Pao Bhaji", 100, "lalalala"));

        RVFoodAdapter adapter = new RVFoodAdapter(this, fooditem);
        adapter.setClickListner(this);
        rv.setAdapter(adapter);

    }

    @Override
    public void ItemClicked(View view, int position) {

        switch (position) {
            case 0:
                db.addOrder(new Order("Khana Khazana", "Rajma Chawal", 1, 100));
                break;
            case 1:
                db.addOrder(new Order("Khana Khazana", "Dal Makhni", 1, 100));
                break;
            case 2:
                db.addOrder(new Order("Khana Khazana", "Naan", 1, 100));
                break;
            case 3:
                db.addOrder(new Order("Khana Khazana", "Chole Chawal", 1, 100));
                break;
            case 4:
                db.addOrder(new Order("Khana Khazana", "Chowmien", 1, 100));
                break;
            case 5:
                db.addOrder(new Order("Dominos", "Pao Bhaji", 1, 100));
                break;

        }
        Snackbar.make(view, "Your Order is Added ", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
