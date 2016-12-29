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

        Intent intent=getIntent();
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
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BurgerKing.this);
                alertDialog.setTitle("NAME");
                alertDialog.setMessage("Enter Name");
                final EditText input = new EditText(BurgerKing.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setIcon(R.drawable.cart);
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name=input.getText().toString();
                        Toast.makeText(context, "name", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),FinalActivity.class);
                        intent.putExtra("Name",name);
                        startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();

                List<Order> order = db.getAllOrder();
                for (Order cn : order) {
                    String log = "Id: "+cn.foodname+" ,Name: " + cn.stallname + " ,Phone: " +
                            cn.qty;
                    Log.d("Name: ", log);
                }

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
