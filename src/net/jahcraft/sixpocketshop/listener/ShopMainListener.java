package net.jahcraft.sixpocketshop.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import net.jahcraft.sixpocketshop.gui.ShopMain;
import net.jahcraft.sixpocketshop.gui.SubShop;
import net.jahcraft.sixpocketshop.main.Main;


public class ShopMainListener implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Inventory inv = ShopMain.inv;
		
		if (event.getClickedInventory() == null) return;
		if (!event.getInventory().equals(inv)) return;
		event.setCancelled(true);
		if (!event.getClickedInventory().equals(inv)) return;
		if (event.getCurrentItem() == null) return;
		if (event.getCurrentItem().getItemMeta() == null) return;
		if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		
		
		Player player = (Player) event.getWhoClicked();
		
		if (event.getSlot() == 31 && event.getClickedInventory() == inv) {
			
			player.closeInventory();
			
		}
		
//		if (event.getSlot() == 4 && event.getClickedInventory() == inv) {
//			
//			player.openInventory(me.Jamboxman5.JahFish.gui.FishExchange.createInv(player));
//
//						
//		}
		
//		if (event.getSlot() == 22 && event.getClickedInventory() == inv) {
//			
//			if (InfoStorage.getAdmins().contains(player.getName())) {
//				player.openInventory(me.Jamboxman5.JahPelts.gui.PeltExchange.createInv(player));
//
//			}
//						
//		}
		
		if (event.getSlot() >= 9 && event.getSlot() <= 17) {
			Main.shopStorage.pageStorage.put(player, 1);
			Main.shopStorage.shopNoStorage.put(player, event.getSlot()-8);
			player.openInventory(SubShop.createInv(player, event.getSlot()-8, 1));
			SubShopListener.shoppers.add(player);
		}		
		
	}

}
