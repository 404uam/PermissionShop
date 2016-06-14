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
    private ArrayList categories;


    public void onEnable() {
        saveDefaultConfig();
        instance = this;
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

        Inventory shop = Bukkit.createInventory(null,36,config.getString("inventoryName"));
        while(config.get("categories." + i) != null)
        {
            if((Material.matchMaterial(config.getString("categories." + i + ".material")) == null))
            {
                System.out.println("No such material");
            }
            else{

                ItemStack temp = new ItemStack(Material.matchMaterial(config.getString("categories." + i + ".material")));
                ItemMeta tempMeta = temp.getItemMeta();
                tempMeta.setDisplayName(config.getString("categories." + i + ".name"));
                temp.setItemMeta(tempMeta);

                shop.setItem(config.getInt("categories."+i+".position"),temp);
            }
            i++;
        }

    }



}
