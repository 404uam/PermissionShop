package com.muhhost.permissionshop.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.EventHandler;

public class InventoryListener implements Listener{


    @EventHandler
    public void onInventoryClick(InventoryClickEvent click)
    {
        click.setCancelled(true);

        click.getCurrentItem().getItemMeta().getDisplayName();
    }


}
