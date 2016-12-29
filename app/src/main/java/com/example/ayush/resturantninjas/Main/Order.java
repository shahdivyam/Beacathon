package com.example.ayush.resturantninjas.Main;

/**
 * Created by Ayush on 29-12-2016.
 */

public class Order {

    public String stallname;
    public String foodname;
    public int qty;
    public int priceone;
    public int totalprice;

    public Order()
    {

    }

    public Order(String stallname,String foodname, int qty,int priceone)
    {
        this.stallname=stallname;
        this.foodname=foodname;
        this.qty=qty;
        this.priceone=priceone;
        this.totalprice=priceone*qty;
    }


}
