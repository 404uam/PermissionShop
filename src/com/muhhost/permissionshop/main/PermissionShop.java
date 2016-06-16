package com.muhhost.permissionshop.main;

import com.muhhost.permissionshop.listeners.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class PermissionShop extends JavaPlugin {

    private static PermissionShop instance;
    private FileConfiguration config = super.getConfig();
    private ArrayList <Inventory> shopList;


    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        this.intilizeShops();
        getServer().getPluginManager().registerEvents(new InventoryListener(),this);
        this.getCommand("shop").setExecutor(new ShopCommand());
    }

    public void onDisable() {

    }

    public static PermissionShop getInstance() {
        if (instance == null) {
            instance = new PermissionShop();
        }

        return instance;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    private void intilizeShops()
    {
        int i = 0;

        Inventory categoryPage = Bukkit.createInventory(null,36,config.getString("inventoryName"));

        while(config.get("categories." + i) != null)
        {
            ShopItem temp = new ShopItem(config.getString("categories." + i + "name"), config.getString("categories." + i + "material"),null,config.getInt("categoris." + i + "position"),0.0,true);
            categoryPage.setItem(temp.getPosition(),temp.getItem());
            i++;
        }

        shopList.add(categoryPage);





    }



}
