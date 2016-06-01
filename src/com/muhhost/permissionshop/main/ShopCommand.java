package com.muhhost.permissionshop.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {

        if(sender instanceof Player)
        {
            Player player = (Player)sender;
            Inventory shop = Bukkit.createInventory(player,36,"Shop");

            ItemStack trails = new ItemStack(Material.STRING);
            ItemMeta trailsMeta = trails.getItemMeta();

            trailsMeta.setDisplayName("Trails");
            trails.setItemMeta(trailsMeta);

            shop.setItem(11,trails);

            player.openInventory(shop);
        }
        else
            Bukkit.getLogger().info("Command can only be activated by a player!");


        return true;
    }
}
