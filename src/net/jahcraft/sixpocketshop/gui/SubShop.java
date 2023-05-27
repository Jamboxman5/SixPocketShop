package net.jahcraft.sixpocketshop.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.jahcraft.sixpocketshop.main.Main;
import net.md_5.bungee.api.ChatColor;

public class SubShop {

	
	public static Inventory inv;
	public static ItemStack i; 
	
	public static Inventory createInv(Player p, int shopNo, int page) {
		
		int maxPage = 1;
		while (Main.data.getItemConfig().getString("shops.shop" + shopNo + ".Page" + Integer.toString(maxPage+1) + ".Slot1.Material") != null) {
			maxPage += 1;
		}
		
		if (maxPage > 1) {
			inv = Bukkit.createInventory(null, 54, ChatColor.of(Main.data.getItemConfig().getString("shops.ShopTitleColor")) + 
					"" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.shop" + shopNo + ".Title") + " (Page " + Main.shopStorage.pageStorage.get(p) + ")");
		} else {
			inv = Bukkit.createInventory(null, 54, ChatColor.of(Main.data.getItemConfig().getString("shops.ShopTitleColor")) + 
					"" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.shop" + shopNo + ".Title"));
		}
			
		int pageNo;
		
		if (p == null) {
			pageNo = page;
		} else {
			pageNo = Main.shopStorage.pageStorage.get(p);
		}
		
		ItemStack item = new ItemStack(Material.BARRIER);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		// Close shop button
		
		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Close the Shop Menu");
		lore.add(ChatColor.GRAY + "Click to close the menu.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(49, item);
		
		//ITEMS
		
		for (int i = 0;  i < 45; i++) {
			assembleItem(p, i, shopNo, page);
		}
		
		// Borders
		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.BorderMaterial")));
		meta.setDisplayName(" ");
		meta.setLore(null);
		item.setItemMeta(meta);
		
		inv.setItem(52, item);
		inv.setItem(51, item);
		inv.setItem(50, item);
		inv.setItem(48, item);
		inv.setItem(47, item);
		inv.setItem(46, item);
		
		//NEXT BUTTON
		if (pageNo < maxPage) {
			item.setType(Material.EMERALD_BLOCK);
			meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Next");
			item.setItemMeta(meta);
			inv.setItem(53, item);
		} else {
			inv.setItem(53, item);
		}
		
		
		
		
		//BACK BUTTON
		item.setType(Material.REDSTONE_BLOCK);
		if (pageNo <= 1) {
			meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Return to Main Menu");
		} else {
			meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Back");
		}
		item.setItemMeta(meta);
		inv.setItem(45, item);
		i = item;
				
		return inv; 
		
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

	private static void assembleItem(Player p, int slot, int shopNo, int page) {
		int pageNo;
		if (p == null) {
			pageNo = page;
		} else {
			pageNo = Main.shopStorage.pageStorage.get(p);
		}
		//PULL FROM CONFIG
		if (Main.data.getItemConfig().getString("shops.shop" + shopNo + ".Page" + pageNo + ".Slot" + Integer.toString(slot + 1) + ".Material") == null) return;
		Material m = Material.valueOf(Main.data.getItemConfig().getString("shops.shop" + shopNo + ".Page" + pageNo + ".Slot" + Integer.toString(slot + 1) + ".Material"));
		String name = Main.data.getItemConfig().getString("shops.shop" + shopNo + ".Page" + pageNo + ".Slot" + Integer.toString(slot + 1) + ".DisplayName");
		
		//DECLARE ITEM
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		
		//BUILD LORE
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "Left-click to " + ChatColor.of("#FFDF00") + "" + ChatColor.BOLD + "BUY");
		lore.add(ChatColor.GRAY + "Right-click to " + ChatColor.of("#FFDF00") + "" + ChatColor.BOLD + "SELL");
		lore.add(ChatColor.GRAY + "Press F to " + ChatColor.of("#FFDF00") + "" + ChatColor.BOLD + "SELL ALL");
		lore.add(ChatColor.GRAY + "Buy: " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + String.format("%,.2f", getBuyPrice(m)) + ChatColor.GRAY + 
				" | Sell: " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + String.format("%,.2f", getSellPrice(p, m)));
		lore.add(ChatColor.GRAY + "Shift: Buy/Sell Full Stack");
		meta.setLore(lore);
		
		//FINISHING TOUCHES
		meta.setDisplayName(ChatColor.of(Main.data.getItemConfig().getString("shops.ItemNameColor")) + "" + ChatColor.BOLD + name);
		item.setItemMeta(meta);
		inv.setItem(slot, item);
		
	}
	
}
