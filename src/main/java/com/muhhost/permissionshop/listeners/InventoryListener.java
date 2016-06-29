package com.muhhost.permissionshop.listeners;

import com.muhhost.permissionshop.main.PermissionShop;
import com.muhhost.permissionshop.main.Shop;
import com.muhhost.permissionshop.main.ShopItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

import static com.muhhost.permissionshop.main.PermissionShop.econ;

public class InventoryListener implements Listener{

    private PermissionShop pShop = PermissionShop.getInstance();
    private FileConfiguration config = pShop.getConfig();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent click)
    {
        Player player = (Player)click.getWhoClicked();
        String shopName = click.getInventory().getName();
        int itemClicked = click.getSlot();

        if (pShop.indexOf(shopName) > -1 || click.getInventory().getTitle().contains(pShop.getConfig().getString("inventoryName")))
        {
            Shop currentShop = null;
            click.setCancelled(true);

            if (click.getCurrentItem() == null || click.getCurrentItem() == new ItemStack(Material.AIR))
            {
                return; //BAD PRACTICE
            }

            if(pShop.indexOf(shopName) == -1)
            {
                pShop.getLogger().severe("Shop not found!");
                return; //BAD PRACTICE
            }
            else {
                currentShop = pShop.getListOfShops().get(pShop.indexOf(shopName));
            }

            ShopItem currentItem = currentShop.get(click.getSlot());

            if(click.getCurrentItem().getItemMeta().getDisplayName().equals(currentItem.getName())) {
                if (currentItem.isCategory()) {
                    //Gets the INVENTORY/SHOP from SHOP LIST
                    int indexOfNextShop = pShop.indexOf(currentShop.get(itemClicked).getNextShop());
                    //Opens the inventory
                    player.openInventory(pShop.getShopList().get(indexOfNextShop));         //HERE Is where tuples can come to use.
                } else {
                    if ((econ.getBalance(player) - currentItem.getPrice()) < 0.0) {
                        player.sendMessage("Not enough money to buy this item!");
                    } else {
                        if (click.getCurrentItem().getItemMeta().getDisplayName().equals(currentItem.getName())) {
                            econ.withdrawPlayer(player, currentItem.getPrice());
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), currentItem.getCommand().substring(1, currentItem.getCommand().length() - 1));
                        } else {
                            return;
                        }

                    }
                }
            }



        }




        /*try {
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
                System.out.println(econ.getBalance(player));
                String s = currentShop.get(itemClicked).getCommand().substring(1,currentShop.get(itemClicked).getCommand().length()-1);
                System.out.println(currentShop.get(itemClicked).getCommand().replace('[','\0').replace(']','\0'));
                Bukkit.dispatchCommand(player,s);
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
*/



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


}
