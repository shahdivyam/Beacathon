package com.example.ayush.resturantninjas;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RVAdapter.ClickListner {
    Resturant resturant;
    Context context;
    public List<Resturant> resturants;
    private static final int PERMISSIONS_REQUEST_CODE = 100 ;
    private static String[] mPermissions = { Manifest.permission.ACCESS_FINE_LOCATION};
    private static final String TAG = MainActivity.class.getSimpleName();

    public Button mButton;
    public TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        mButton = (Button) findViewById(R.id.bt_1);
        mTextView = (TextView) findViewById(R.id.tv_1);


        if (!havePermissions()) {
            Log.i("Permission", "Requesting permissions needed for this app.");
            requestPermissions();
        }

        if(!isBlueEnable()){
            Intent bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(bluetoothIntent);
        }


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

        mButton.setOnClickListener(new View.OnClickListener() {
            String str="";
            @Override
            public void onClick(View v) {
                if (!MyApp.getInstance().regionList.isEmpty()){
                    for(int i=0;i<MyApp.getInstance().regionList.size();i++){
                        str+=MyApp.getInstance().regionNameList.get(i)+" , ";
                    }
                    mTextView.setText(str);

                }
            }

        });

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
    private boolean isBlueEnable() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        return bluetoothAdapter.isEnabled();

    }
    private boolean havePermissions() {
        for(String permission:mPermissions){
            if(ActivityCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                return  false;
            }
        }
        return true;
    }
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,mPermissions, PERMISSIONS_REQUEST_CODE);
    }
}
