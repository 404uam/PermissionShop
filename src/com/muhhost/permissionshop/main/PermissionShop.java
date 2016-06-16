package com.muhhost.permissionshop.main;

import com.muhhost.permissionshop.listeners.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

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
        Queue <Inventory> nextShops = new PriorityQueue<>();

        Inventory categoryPage = Bukkit.createInventory(null,36,config.getString("inventoryName"));

        while(config.get("categories." + i) != null)
        {
            ShopItem temp = new ShopItem(config.getString("categories." + i + "name"),config.getString("categories." + i + "material"), config.getString("categoris."+i+"shop"), config.getInt("categories." + i + "position"));
            categoryPage.setItem(temp.getPosition(),temp.getItem());
            i++;
            shopList.add(categoryPage);
            if(temp.getNextShop() != null || !temp.getNextShop().equals(" ") || !temp.getNextShop().equals(""))
            {
                Inventory nextShop = Bukkit.createInventory(null, InventoryType.CHEST,temp.getNextShop());
                shopList.add(nextShop);
                nextShops.add(nextShop);
            }
        }

        while(nextShops.peek() != null)
        {
            shopList.add(nextShops.poll());
        }

        for (int j = 1; j<shopList.size(); j++)
        {

        }

    }

    private void initilizeShops(String name)
    {
        int i = 0;

        while(config.get(name + "." + i) != null)
        {
            String prefix = name + "." +i;
            ShopItem item = new ShopItem(config.getString(prefix+"name"),config.getString(prefix+"material"),config.getString(prefix+"command"),config.getInt(prefix+"position"),config.getDouble(prefix+"price"));
            i++;
        }


    }
}
