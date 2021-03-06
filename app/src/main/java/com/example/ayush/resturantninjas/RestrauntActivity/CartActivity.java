package com.example.ayush.resturantninjas.RestrauntActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);
                alertDialog.setTitle("NAME");
                alertDialog.setMessage("Enter Name");
                final EditText input = new EditText(CartActivity.this);
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
                        //Toast.makeText(context, "name", Toast.LENGTH_SHORT).show();
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

        List<Order> order = db.getAllOrder();
        RVCartAdapter adapter= new RVCartAdapter(this,order);
        rv.setAdapter(adapter);


    }
}
