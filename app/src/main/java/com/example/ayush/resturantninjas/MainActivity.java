package com.example.ayush.resturantninjas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RVAdapter.ClickListner {
    Resturant resturant;
    Context context;
    public List<Resturant> resturants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        resturants = new ArrayList<>();
        resturants.add(new Resturant("bebe","Dominos", 0));
        resturants.add(new Resturant("bebe","Khana Khazana", 0));
        resturants.add(new Resturant("bebe","Mc Donalds", 0));

        RVAdapter adapter = new RVAdapter(this,resturants);
        adapter.setClickListner(this);
        rv.setAdapter(adapter);

    }


    @Override
    public void ItemClicked(View view, int position) {
        Intent intent;
        switch (position)
        {
            case 0:intent = new Intent(getApplicationContext(), ClickActivity.class);
                    intent.putExtra("Position",position);
                    startActivity(intent);
                    break;
            case 1: intent = new Intent(getApplicationContext(),KhanaKhazana.class);
                    intent.putExtra("Position",position);
                    startActivity(intent);
                    break;
            case 2:intent = new Intent(getApplicationContext(),BurgerKing.class);
                    intent.putExtra("Position",position);
                    startActivity(intent);
                    break;


        }


        

    }
}
