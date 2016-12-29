package com.example.ayush.resturantninjas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
Resturant resturant;
    public List<Resturant> resturants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        resturants = new ArrayList<>();
        resturants.add(new Resturant("bebe","Dominos", 0));
        resturants.add(new Resturant("bebe","Dominos", 0));
        resturants.add(new Resturant("bebe","Dominos", 0));

        RVAdapter adapter = new RVAdapter(resturants);
        rv.setAdapter(adapter);
    }



}
