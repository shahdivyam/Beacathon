package com.example.ayush.resturantninjas.RestrauntActivity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ayush.resturantninjas.Main.MainActivity;
import com.example.ayush.resturantninjas.Main.Order;
import com.example.ayush.resturantninjas.R;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    DatabaseHandler db= new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        GridLayoutManager llm=new GridLayoutManager(this,1);
        rv.setLayoutManager(llm);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(CartActivity.this, "PAYMENT SUCESFULL", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        List<Order> order = db.getAllOrder();
        RVCartAdapter adapter= new RVCartAdapter(this,order);
        rv.setAdapter(adapter);


    }
}
