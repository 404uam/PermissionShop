package com.muhhost.permissionshop.listeners;

import com.muhhost.permissionshop.main.PermissionShop;
import com.muhhost.permissionshop.main.Shop;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.EventHandler;

public class InventoryListener implements Listener{

    private PermissionShop pShop = PermissionShop.getInstance();
    private FileConfiguration config = pShop.getConfig();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent click)
    {
        Player player = (Player)click.getWhoClicked();
        String shopName = click.getInventory().getName();
        int itemClicked = click.getSlot();

        click.setCancelled(true);

        try {
            Shop currentShop = pShop.getListOfShops().get(pShop.indexOf(shopName));

            if(currentShop.get(itemClicked).isCategory())
            {
                //Gets the INVENTORY/SHOP from SHOP LIST
                int indexOfNextShop = pShop.indexOf(currentShop.get(itemClicked).getNextShop());
                //Opens the inventory
                player.openInventory(pShop.getShopList().get(indexOfNextShop));         //HERE Is where tuples can come to use.
                }
            else
            {
                Bukkit.dispatchCommand(player,currentShop.get(itemClicked).getCommand());
            }
            }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            System.out.println("Shop not found for some reason");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println(pShop.getListOfShops().indexOf(shopName));
            System.out.println(pShop.indexOf(shopName));
            System.out.print("wat");
        }




/*        if(category)
        {
            load list of items in shop as referenced by clicked category.
            if(list not loaded)
            {
                ERROR
            }
            else
            {
                Do stuff Maybe redraw gui.
            }
        }
        else if(item)
        {
            if(already own)
            {

            }
            else if (proper amount of money to buy)
            {

            }
        }*/
    }

    private boolean loadList(String shop)
    {
        boolean isValid = false;


        return isValid;
    }


}
