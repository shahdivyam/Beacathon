package com.example.ayush.resturantninjas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayush on 29-12-2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "order";
    private static final String TABLE_NAME = "myorder";
    private static final String STALLNAME= "stallname";
    private static final String FOODNAME = "foodname";
    private static final String QTY = "qty";
    private static final String PRICE = "price";
    private static final String TOTALPRICE = "totalprice";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + "UID" + " INTEGER PRIMARY KEY AUTOINCREMENT," + STALLNAME + " TEXT,"
                + FOODNAME + " TEXT,"+ QTY + " INTEGER,"+ PRICE + " INTEGER,"+ TOTALPRICE + " INTEGER " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addOrder(Order order)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(STALLNAME,order.stallname);
        values.put(FOODNAME,order.foodname);
        values.put(QTY,order.qty);
        values.put(PRICE,order.priceone);
        values.put(TOTALPRICE,order.totalprice);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    Order getOrder(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] {STALLNAME,
                        FOODNAME, QTY }, "UID" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Order order = new Order();
        order.stallname=cursor.getString(0);
        order.foodname=cursor.getString(1);
        // return contact
        return order;
    }
    public List<Order> getAllOrder() {
        List<Order> ordersist = new ArrayList<Order>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Order order=new Order();
                order.stallname=cursor.getString(1);
                order.foodname=cursor.getString(2);
                order.qty= Integer.parseInt(cursor.getString(3));
                order.priceone= Integer.parseInt(cursor.getString(4));
                order.totalprice= Integer.parseInt(cursor.getString(5));
                // Adding contact to list
                ordersist.add(order);
            } while (cursor.moveToNext());
        }

        // return contact list
        return ordersist;
    }
}
