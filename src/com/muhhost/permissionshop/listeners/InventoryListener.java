package com.muhhost.permissionshop.listeners;

import com.muhhost.permissionshop.main.PermissionShop;
import com.muhhost.permissionshop.main.Shop;
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


        if(pShop.getListOfShops().contains(shopName))
        {
            try {
                Shop shop = pShop.getListOfShops().get(pShop.getListOfShops().indexOf(shopName));
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
                System.out.println("Shop not found for some reason");
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
