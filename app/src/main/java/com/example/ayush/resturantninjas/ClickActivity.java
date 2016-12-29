package com.example.ayush.resturantninjas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ClickActivity extends AppCompatActivity implements RVFoodAdapter.ClickListner {
    public List<FoodItem> fooditem;
    Context context;
    DatabaseHandler db=new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        context=this;
        Intent intent=getIntent();
        int position=intent.getIntExtra("Position",-1);

        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        GridLayoutManager llm=new GridLayoutManager(this,2);
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
        fooditem=new ArrayList<>();
        fooditem.add(new FoodItem("Choclate Pizza",100,"lalalala"));
        fooditem.add(new FoodItem("Paneer Pizza",100,"lalalala"));
        fooditem.add(new FoodItem("Cheeseburst",100,"lalalala"));
        fooditem.add(new FoodItem("Mexican green Wave",100,"lalalala"));
        fooditem.add(new FoodItem("Spicy Chiecken",100,"lalalala"));
        fooditem.add(new FoodItem("Random Pizza",100,"lalalala"));

        RVFoodAdapter adapter=new RVFoodAdapter(this,fooditem);
        adapter.setClickListner(this);
        rv.setAdapter(adapter);

        /* DatabaseHandler db=new DatabaseHandler(this);
        db.addOrder(new Order("Dominos","Chocolate Pizza",1,100));
        db.addOrder(new Order("Dominos","Chocolate Pizza",1,100));
        db.addOrder(new Order("Dominos","Chocolate Pizza",1,100));
        db.addOrder(new Order("Dominos","Chocolate Pizza",1,100));
        Log.d("Reading: ", "Reading all contacts..");
        List<Order> order = db.getAllOrder();
        for (Order cn : order) {
            String log = "Id: "+cn.foodname+" ,Name: " + cn.stallname + " ,Phone: " +
                    cn.qty;
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
        */

    }

    @Override
    public void ItemClicked(View view, int position) {
        switch (position)
        {
            case 0:db.addOrder(new Order("Dominos","Chocolate Pizza",1,100));
                    break;
            case 1: db.addOrder(new Order("Dominos","Paneer Pizza",1,100));
                    break;
            case 2: db.addOrder(new Order("Dominos","Cheese burst",1,100));
                    break;
            case 3:db.addOrder(new Order("Dominos","Mexican Green Wave",1,100));
                break;
            case 4: db.addOrder(new Order("Dominos","Spicy Chicken",1,100));
                break;
            case 5: db.addOrder(new Order("Dominos","Bruger Pizza",1,100));
                break;

        }
        Snackbar.make(view, "Your Order is Added ", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
