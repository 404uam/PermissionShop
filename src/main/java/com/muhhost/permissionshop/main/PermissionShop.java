package com.muhhost.permissionshop.main;

import com.muhhost.permissionshop.listeners.InventoryListener;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Logger;

public class PermissionShop extends JavaPlugin {

    private static PermissionShop instance;
    private FileConfiguration config = super.getConfig();
    private ArrayList <Inventory> shopList = new ArrayList<Inventory>();
    private ArrayList <Shop> listOfShops = new ArrayList<>();
    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;


    public void onEnable() {
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        saveDefaultConfig();
        instance = this;
        this.initializeShops();
        getServer().getPluginManager().registerEvents(new InventoryListener(),this);
        this.getCommand("pshop").setExecutor(new ShopCommand());
    }

    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
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

    public ArrayList<Inventory> getShopList() { return shopList; }

    public int indexOf(String name)
    {
        int index = -1;

        for(int i =  0; i < listOfShops.size(); i++)
        {
            if(name.equals(listOfShops.get(i).getName()))
            {
                index = i;
            }
        }

        return index;
    }
    private void initializeShops()
    {
        int i = 0;
        Queue <Inventory> nextShops = new LinkedList<Inventory>();
        ArrayList<String> listOfNames = new ArrayList<>();
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

                if(!listOfNames.contains(temp.getNextShop()))
                {
                    shopList.add(nextShop);
                    listOfShops.add(tempShop);

                    listOfNames.add(temp.getNextShop());
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

    private boolean setupEconomy(){
        boolean isSetup = true;

        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if(rsp == null)
        {
            isSetup = false;
        }
        econ = rsp.getProvider();

        return (isSetup && econ != null);
    }

    private boolean setupChat(){
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();

        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
}
