package net.jahcraft.sixpocketshop.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.jahcraft.sixpocketshop.gui.ShopMain;
import net.jahcraft.sixpocketshop.gui.SubShop;
import net.jahcraft.sixpocketshop.main.Main;
import net.md_5.bungee.api.ChatColor;


public class SubShopListener implements Listener {
	
	private Main plugin;
	public SubShopListener(Main plugin) {
		this.plugin = plugin;
	}
		
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		if (event.getClickedInventory() == null) return;
		if (event.getClickedInventory().equals(ShopMain.inv)) return;
		if (event.getClickedInventory().getSize() < 54) return;
		if (!Main.shopStorage.markerStorage.contains(event.getClickedInventory().getItem(45))) return;
//		if (event.getClickedInventory().contains(me.Jamboxman5.JahFish.gui.FishExchange.i)) return;
//		if (event.getClickedInventory().contains(me.Jamboxman5.JahPelts.gui.PeltExchange.i)) return;
		if (event.getCurrentItem() == null) return;
		if (event.getCurrentItem().getItemMeta() == null) return;
		if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		if (event.getClickedInventory() == null) return;
		
		event.setCancelled(true);
		
		Player player = (Player) event.getWhoClicked();
		
		//ITEMS

		if (event.getSlot() < 45) {
			
			checkClicks(event);
			
		}
		
		//GUI CONTROLS
		if (event.getSlot() == 49) {
			player.closeInventory();
		}
		if (event.getSlot() == 45) {
			if (Main.shopStorage.pageStorage.get(player) <= 1) {
				player.openInventory(ShopMain.inv);	
			} else {
				Main.shopStorage.pageStorage.put(player, Main.shopStorage.pageStorage.get(player)-1);
				player.openInventory(SubShop.createInv(player, Main.shopStorage.shopNoStorage.get(player), 0));
			}
						
		}
		if (event.getSlot() == 53) {
			if (event.getClickedInventory().getItem(53).getType() != Material.EMERALD_BLOCK) return;
			Main.shopStorage.pageStorage.put(player, Main.shopStorage.pageStorage.get(player)+1);
			player.openInventory(SubShop.createInv(player, Main.shopStorage.shopNoStorage.get(player), 0));
					
		}
		
	}

	private void checkClicks(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (event.getClick().equals(ClickType.SWAP_OFFHAND)) {
			sellAllItem(player, event);
			return;
		}
		
		if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {
			sellItem(player, event, event.getCurrentItem().getMaxStackSize());
			return;
		}
		
		if (event.getClick().equals(ClickType.SHIFT_LEFT)) {
			buyItem(player, event, event.getCurrentItem().getMaxStackSize());
			return;
		}
		
		if (event.getClick().equals(ClickType.RIGHT)) {
			sellItem(player, event, 1);
			return;
		}
		
		if (event.getClick().equals(ClickType.LEFT)) {
			buyItem(player, event, 1);
			return;
		}
		
	}

	private void sellAllItem(Player p, InventoryClickEvent e) {
		
		ItemStack i = new ItemStack(e.getCurrentItem().getType());
		int itemCounter = 0;
		double moneyMade = 0;
		boolean itemSold = false;
		
		for (ItemStack item : p.getInventory().getContents()) {
			if (item != null ) {
				if (item.getType().equals(i.getType())) {
					
					ItemStack temp = new ItemStack(item.getType());
					temp.setAmount(item.getAmount());
					temp.setItemMeta(item.getItemMeta());
					p.getInventory().removeItem(item);
					if (item == p.getInventory().getItemInOffHand())
						p.getInventory().setItemInOffHand(null);
					
					itemCounter += item.getAmount();
							
					double price = getSellPrice(p, i.getType()) * item.getAmount();
					moneyMade += price;
					
					plugin.eco.depositPlayer(p, price);
					itemSold = true;
						
				}
				
			}	
			
		}
		
		if (!itemSold) {
			p.sendMessage(ChatColor.RED + "You do not have enough of that item to sell!");
		} else {
			p.sendMessage(ChatColor.AQUA + "You traded " + ChatColor.of("#FFDF00") + "" + ChatColor.BOLD + itemCounter + " " + e.getCurrentItem().getItemMeta().getDisplayName() + 
					ChatColor.AQUA + " for " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + String.format("%,.2f", moneyMade) );
			
//			for (String n : Main.infoStorage.getAdmins()) {
//				if (Bukkit.getPlayer(n) != null && 
//					p.getName() != Bukkit.getServer().getPlayer(n).getName()) {
//					Bukkit.getServer().getPlayer(n).sendMessage(ChatColor.RED + p.getName() + ChatColor.RED + 
//					" sold " + itemCounter + " " + i.getType().toString().toLowerCase() + " for $" + String.format("%,.2f", moneyMade));
//				}
//				
//			}
			
			Bukkit.getLogger().info(p.getName() + " has sold " + itemCounter + " " + i.getType().toString().toLowerCase() + " for $" + String.format("%,.2f", moneyMade));
			
		}
		
	}

	private void buyItem(Player player, InventoryClickEvent e, int amount) {
		if (!(plugin.eco.getBalance(player) < (getBuyPrice(e.getCurrentItem().getType()) * amount))) {
			
			if (player.getInventory().firstEmpty() == -1 ) {
				
				player.sendMessage(ChatColor.RED + "Your inventory is full!");
				
			} else {
				
				ItemStack item = new ItemStack(e.getCurrentItem().getType());
				item.setAmount(amount);
				player.getInventory().addItem(item);
				plugin.eco.withdrawPlayer(player, (getBuyPrice(e.getCurrentItem().getType()) * amount));
				player.sendMessage(ChatColor.AQUA + "You traded " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + (getBuyPrice(e.getCurrentItem().getType())*amount) + 
						ChatColor.AQUA + "" + ChatColor.BOLD + " for " + ChatColor.of("#FFDF00") + "" + ChatColor.BOLD + amount + " " + e.getCurrentItem().getItemMeta().getDisplayName() );
				Bukkit.getLogger().info(player.getName() + " has bought " + amount + " " + e.getCurrentItem().getType().toString().toLowerCase() + " for $" + (getBuyPrice(e.getCurrentItem().getType()) * amount));
				
			}
			
		} else {
			
			player.sendMessage(ChatColor.RED + "You can't afford that!");
			
		}
		
	}

	private void sellItem(Player player, InventoryClickEvent e, int amount) {
		
		ItemStack item = new ItemStack(e.getCurrentItem().getType());
		if (player.getInventory().containsAtLeast(item, amount)) {

			item.setAmount(amount);
			player.getInventory().removeItem(item);
			plugin.eco.depositPlayer(player, (getSellPrice(player, e.getCurrentItem().getType()) * amount));
			player.sendMessage(ChatColor.AQUA + "You traded " + ChatColor.of("#FFDF00") + "" + ChatColor.BOLD + amount + " " + e.getCurrentItem().getItemMeta().getDisplayName() + 
					ChatColor.AQUA + " for " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + (getSellPrice(player, e.getCurrentItem().getType())*amount) );
			Bukkit.getLogger().info(player.getName() + " has sold " + amount + " " + e.getCurrentItem().getType().toString().toLowerCase() + " for $" + (getSellPrice(player, e.getCurrentItem().getType()) * amount));
			
			if (e.getCurrentItem().getType() == Material.DIAMOND ||
				e.getCurrentItem().getType() == Material.DIAMOND_BLOCK ||
				e.getCurrentItem().getType() == Material.EMERALD ||
				e.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
				
//				for (String n : Main.infoStorage.getAdmins()) {
//					if (Bukkit.getPlayer(n) != null && 
//						player.getDisplayName() != Bukkit.getServer().getPlayer(n).getDisplayName()) {
//						Bukkit.getServer().getPlayer(n).sendMessage(ChatColor.RED + player.getDisplayName() + ChatColor.RED + " sold " + amount + " " + e.getCurrentItem().getType().toString().toLowerCase() + 
//						ChatColor.RED + " for $" + (getSellPrice(player, e.getCurrentItem().getType()) * amount));
//					}
//					
//				}
				
			}
			
			
		} else {
			
			player.sendMessage(ChatColor.RED + "You do not have enough of that item to sell!");
			
		}
		
	}
	
	public static double getSellPrice(Player p, Material m) {
		double multiplier = 1;
		if (p != null) {
			if (p.hasPermission("group.default")) multiplier = .8;
			if (p.hasPermission("group.resident")) multiplier = .9;
			if (p.hasPermission("group.citizen")) multiplier = 1;
			if (p.hasPermission("group.veteran")) multiplier = 1.2;
			if (p.hasPermission("group.decorated")) multiplier = 1.4;
			if (p.hasPermission("group.nobility")) multiplier = 1.6;
			if (p.hasPermission("group.scholar")) multiplier = 1.8;
			if (p.hasPermission("group.hero")) multiplier = 2;
			if (p.hasPermission("group.admin")) multiplier = 2;
			if (p.hasPermission("group.owner")) multiplier = 2;
		}
		
		double price = Main.data.getPriceConfig().getDouble("Prices." + m.toString() + ".sell");
		price = Math.round(price*multiplier*100.0)/100.0;
		return price;
	}

	private static double getBuyPrice(Material m) {
		double price = Main.data.getPriceConfig().getDouble("Prices." + m.toString() + ".buy");
		return price;
	}

}
