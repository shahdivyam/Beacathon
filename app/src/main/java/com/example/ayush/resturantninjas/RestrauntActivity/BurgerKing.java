package com.example.ayush.resturantninjas.RestrauntActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ayush.resturantninjas.Main.Order;
import com.example.ayush.resturantninjas.R;

import java.util.ArrayList;
import java.util.List;

public class BurgerKing extends AppCompatActivity implements RVBurgerKingAdapter.ClickListner {
    public List<FoodItem> fooditem;
    Context context;
    DatabaseHandler db=new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        context=this;

        final Intent intent=getIntent();
        int position=intent.getIntExtra("Position",-1);

        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        GridLayoutManager llm=new GridLayoutManager(this,1);
        rv.setLayoutManager(llm);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent1);

            }
        });
        fooditem=new ArrayList<>();
        fooditem.add(new FoodItem("Chicken Grill",100,"lalalala"));
        fooditem.add(new FoodItem("McAloo",100,"lalalala"));
        fooditem.add(new FoodItem("McChicken",100,"lalalala"));
        fooditem.add(new FoodItem("McPuff",100,"lalalala"));
        fooditem.add(new FoodItem("McPaneer",100,"lalalala"));
        fooditem.add(new FoodItem("McSwirl",100,"lalalala"));

        RVBurgerKingAdapter adapter=new RVBurgerKingAdapter(this,fooditem);
        adapter.setClickListner(this);
        rv.setAdapter(adapter);
    }

    @Override
    public void ItemClicked(View view, int position) {

        switch (position)
        {
            case 0:db.addOrder(new Order("McDonalds","Chicken Grll",1,100));
                break;
            case 1: db.addOrder(new Order("McDonalds","McAloo",1,100));
                break;
            case 2: db.addOrder(new Order("McDonalds","McChicken",1,100));
                break;
            case 3:db.addOrder(new Order("McDonalds","McPuff",1,100));
                break;
            case 4: db.addOrder(new Order("McDonalds","McPaneer",1,100));
                break;
            case 5: db.addOrder(new Order("McDonalds","McSwirl",1,100));
                break;

        }
        Snackbar.make(view, "Your Order is Added ", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();


    }
}
