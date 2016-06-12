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

    class ShopCommand implements CommandExecutor {

    private PermissionShop pShop = PermissionShop.getInstance();
    private FileConfiguration config = pShop.getConfig();

    public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {

        if(sender instanceof Player)
        {
            Player player = (Player)sender;

            openGUI(player);
        }
        else
            Bukkit.getLogger().info("Command can only be activated by a player!");


        return true;
    }

    private void openGUI(Player player)
    {
        Inventory shop = Bukkit.createInventory(player,36,config.getString("inventoryName"));


        if((Material.matchMaterial(config.getString("categories.0.material")) == null))
        {
            System.out.println("No such material");
        }
        else
        {
            ItemStack temp = new ItemStack(Material.matchMaterial(config.getString("categories.0.material")));
            ItemMeta tempMeta = temp.getItemMeta();
            tempMeta.setDisplayName(config.getString("categories.0.name"));
            temp.setItemMeta(tempMeta);

            shop.setItem(config.getInt("categories.0.position"),temp);
        }

        player.openInventory(shop);

    }
}
