package com.muhhost.permissionshop.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.EventHandler;

public class InventoryListener implements Listener{


    @EventHandler
    public void onClick(InventoryClickEvent click)
    {
        System.out.println("Hello using event");
        System.out.print(click.getSlot());
        click.setCancelled(true);
    }


}
