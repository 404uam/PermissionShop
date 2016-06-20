package com.muhhost.permissionshop.main;


import java.util.ArrayList;

/**
 * Created by Louis on 6/20/2016.
 */
public class Shop {

    private ArrayList<ShopItem> items;
    private String name;

    public Shop (String name)
    {
        items = new ArrayList<>();
        this.name = name;
    }

    public void add(int index,ShopItem item)
    {
        items.add(index,item);
    }

    public ShopItem get(int index)
    {
        return items.get(index);
    }

    @Override
    public String toString() {
        return name;
    }
}
