package com.muhhost.permissionshop.main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PermissionShop extends JavaPlugin {

    private static PermissionShop instance = null;
    private FileConfiguration config = super.getConfig();

    public void onEnable() {
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


}
