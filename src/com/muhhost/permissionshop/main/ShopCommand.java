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

    public Inventory getShop() {return shop;}

    private void openGUI(Player player)
    {
        shop = Bukkit.createInventory(player,36,config.getString("inventoryName"));
        int i = 0;

        while(config.get("categories." + i) != null)
        {
            if((Material.matchMaterial(config.getString("categories." + i + ".material")) == null))
            {
                System.out.println("No such material");
                Bukkit.getLogger().severe("No such material");
            }
            else{
                String categoryName = config.getString("categories." + i + ".name");
                String categoryMaterial = config.getString("categories." + i + ".material");

                ShopItem item = new ShopItem(categoryName,categoryMaterial,"trails",6);

                ItemStack temp = new ItemStack(Material.matchMaterial(config.getString("categories." + i + ".material")));
                ItemMeta tempMeta = temp.getItemMeta();
                tempMeta.setDisplayName(config.getString("categories." + i + ".name"));
                temp.setItemMeta(tempMeta);

                shop.setItem(config.getInt("categories."+i+".position"),temp);
            }
            i++;
        }
        player.openInventory(shop);
    }
}
