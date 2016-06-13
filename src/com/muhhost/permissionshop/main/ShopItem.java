package com.muhhost.permissionshop.main;

import org.bukkit.Material;

public class ShopItem {
    private String name;
    private String material;
    private String command;
    private int position;
    private double price;

    public ShopItem()
    {

    }
    public ShopItem(String name,String material, String command, int position, double price)
    {
        this.name = name;
        this.material = material;
        this.command = command;
        this.position = position;
        this.price = price;
    }
    public  ShopItem(String name, Material material, String command, int position, double price)
    {
        this.name = name;
        this.material = material.name();
        this.command = command;
        this.position = position;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
