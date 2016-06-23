package com.muhhost.permissionshop.main;


import java.util.ArrayList;

public class Shop {

    private ArrayList<ShopItem> items;
    private ShopItem[] test = new ShopItem[36];
    private String name;

    public Shop (String name)
    {
        items = new ArrayList<>();
        this.name = name;
    }

    public void add(int index,ShopItem item)
    {
        test[index] = item;
    }

    public ShopItem get(int index)
    {
        return items.get(index);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
