package com.muhhost.permissionshop.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopCommand implements CommandExecutor {

    private PermissionShop pShop = PermissionShop.getInstance();
    private FileConfiguration config = pShop.getConfig();
    private Inventory shop;
    private ArrayList<ShopItem> items;

    public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {

        if(sender instanceof Player)
        {
            Player player = (Player)sender;
            player.openInventory(pShop.getShopList().get(pShop.getShopList().size()-1));
        }
        else
            Bukkit.getLogger().info("Command can only be activated by a player!");


        return true;
    }
}
