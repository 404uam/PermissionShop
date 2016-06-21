package com.muhhost.permissionshop.main;

import com.muhhost.permissionshop.listeners.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class PermissionShop extends JavaPlugin {

    private static PermissionShop instance;
    private FileConfiguration config = super.getConfig();
    private ArrayList <Inventory> shopList = new ArrayList<Inventory>();
    private ArrayList <Shop> listOfShops = new ArrayList<>();



    public void onEnable() {
        //config = super.getConfig();
        saveDefaultConfig();
        instance = this;
        this.initializeShops();
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

    public ArrayList<Shop> getListOfShops() {
        return listOfShops;
    }

    private void initializeShops()
    {
        int i = 0;
        Queue <Inventory> nextShops = new LinkedList<Inventory>();
        Hashtable listOfNames = new Hashtable();
        Inventory categoryPage = Bukkit.createInventory(null,36,config.getString("inventoryName"));
        Shop page = new Shop(config.getString("inventoryName"));

        while(config.get("categories." + i) != null)
        {
            String prefix = "categories." + i;

            ShopItem temp = new ShopItem(config.getString(prefix + ".name"),config.getString(prefix + ".material"),config.getString(prefix+".shop"), config.getInt(prefix+ ".position"));
            categoryPage.setItem(temp.getPosition(),temp.getItem());
            page.add(temp.getPosition(),temp);
            i++;

            if(temp.getNextShop() != null || !temp.getNextShop().equals(" ") || !temp.getNextShop().equals(""))
            {
                Inventory nextShop = Bukkit.createInventory(null, InventoryType.CHEST,temp.getNextShop());
                Shop tempShop = new Shop(nextShop.getName());

                if(!listOfNames.containsValue(temp.getNextShop()))
                {
                    shopList.add(nextShop);
                    listOfShops.add(tempShop);
                    listOfNames.put(temp.getNextShop(),temp.getNextShop());
                }
            }

        }
        shopList.add(categoryPage);
        listOfShops.add(page);

        while(nextShops.peek() != null)
        {
            Inventory nextShop = nextShops.poll();
            shopList.add(nextShop);
        }

        for (int j = shopList.size() - 1 ; j >= 0; j--)
        {
            initializeShops(shopList.get(j).getTitle(),shopList.get(j),listOfShops.get(j));
        }

    }

    private void initializeShops(String name,Inventory inv,Shop shop)
    {
        int i = 0;

        while(config.get(name + "." + i) != null)
        {
            String prefix = name + "." +i;
            ShopItem item = new ShopItem(config.getString(prefix+".name"),config.getString(prefix+".material"),config.getString(prefix+".command"),config.getInt(prefix+".position"),config.getDouble(prefix+".price"));
            i++;

            inv.setItem(item.getPosition(),item.getItem());
            shop.add(item.getPosition(),item);
        }

    }

    public ArrayList<Inventory> getShopList() { return shopList; }
}
