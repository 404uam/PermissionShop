package com.muhhost.permissionshop.main;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopItem {
    private String name;
    private String material;
    private String command;
    private int position;
    private double price;
    private ItemStack item;
    private boolean isCategory;

    public ShopItem()
    {
    }
    public ShopItem(String name,String material, String command, int position, double price, boolean isCategory)
    {
        this.name = name;
        this.material = material;
        this.command = command;
        this.position = position;
        this.price = price;
        this.isCategory = isCategory;

        if(Material.matchMaterial(material) != null)
        {
            item = new ItemStack(Material.matchMaterial(material));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
    }
    public  ShopItem(String name, Material material, String command, int position, double price, boolean isCategory)
    {
        this.name = name;
        this.material = material.name();
        this.command = command;
        this.position = position;
        this.price = price;
        this.isCategory = isCategory;

        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
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

    public ItemStack getItem() { return item; }

    public boolean isCategory() { return isCategory; }

}
