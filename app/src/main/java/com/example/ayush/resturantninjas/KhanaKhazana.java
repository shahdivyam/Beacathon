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
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class KhanaKhazana extends AppCompatActivity {
    public List<FoodItem> fooditem;
    Context context;
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fooditem=new ArrayList<>();
        fooditem.add(new FoodItem("Rajma Chawal",100,"lalalala"));
        fooditem.add(new FoodItem("Dal Makhni",100,"lalalala"));
        fooditem.add(new FoodItem("Naan",100,"lalalala"));
        fooditem.add(new FoodItem("Chole Chawal",100,"lalalala"));
        fooditem.add(new FoodItem("Chowmien",100,"lalalala"));
        fooditem.add(new FoodItem("Pao Bhaji",100,"lalalala"));

        RVFoodAdapter adapter=new RVFoodAdapter(this,fooditem);
        rv.setAdapter(adapter);

    }
}
