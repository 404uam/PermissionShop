package com.muhhost.permissionshop.main;

import com.muhhost.permissionshop.listeners.InventoryListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PermissionShop extends JavaPlugin {

    private static PermissionShop instance = null;
    private FileConfiguration config = super.getConfig();

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new InventoryListener(),this);
        this.getCommand("shop").setExecutor(new ShopCommand());
        addDefaultConfig();

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


    private void addDefaultConfig()
    {



    }


}
