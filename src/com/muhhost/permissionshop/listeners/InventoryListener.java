package com.muhhost.permissionshop.listeners;

import com.muhhost.permissionshop.main.PermissionShop;
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

        click.setCancelled(true);

        String shop = click.getInventory().getName();
        int itemClicked = click.getSlot();

        if(pShop.getListOfShops().contains(shop))
        {
            if (pShop.getListOfShops().get(itemClicked) != null)
            {

            }
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
