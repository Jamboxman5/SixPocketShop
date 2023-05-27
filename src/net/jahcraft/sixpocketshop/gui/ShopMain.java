package net.jahcraft.sixpocketshop.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.jahcraft.sixpocketshop.main.Main;
import net.md_5.bungee.api.ChatColor;

public class ShopMain {
	
	public static Inventory inv;

	public static void createInv() {
		
		inv = Bukkit.createInventory(null, 36, ChatColor.of(Main.data.getItemConfig().getString("shops.ShopTitleColor")) + "" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.main.Title"));
		
		ItemStack item = new ItemStack(Material.BARRIER);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		// Close shop button
		
		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Close the Shop Menu");
		lore.add("");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(31, item);
		
		// FishShop
		
//		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.main.FishShop.Material")));
//		meta.setDisplayName(ChatColor.of(Main.data.getItemConfig().getString("shops.FishExchangeColor")) + "" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.main.FishShop.DisplayName"));
//		lore.set(0, ChatColor.GRAY + "Click to exchange your caught fish!");
//		meta.setLore(lore);
//		item.setItemMeta(meta);
//		inv.setItem(4, item);
		
		// PeltShop
		
//		item.setType(Material.LEATHER);
//		meta.setDisplayName(ChatColor.of("#DD7700") + "" + ChatColor.BOLD + "Animal Pelt Exchange");
//		lore.set(0, ChatColor.GRAY + "Click to exchange your animal hides!");
//		meta.setLore(lore);
//		item.setItemMeta(meta);
//		inv.setItem(22, item);
				
		// Shop1
		
		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.main.Slot1.Material")));
		meta.setDisplayName(ChatColor.of(Main.data.getItemConfig().getString("shops.ItemNameColor")) + "" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.main.Slot1.DisplayName"));
		lore.set(0, ChatColor.GRAY + "Click to shop " + Main.data.getItemConfig().getString("shops.main.Slot1.DisplayName") + ".");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(9, item);
		
		// Shop2
		
		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.main.Slot2.Material")));
		meta.setDisplayName(ChatColor.of(Main.data.getItemConfig().getString("shops.ItemNameColor")) + "" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.main.Slot2.DisplayName"));
		lore.set(0, ChatColor.GRAY + "Click to shop " + Main.data.getItemConfig().getString("shops.main.Slot2.DisplayName") + ".");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(10, item);
		
		// Shop3
		
		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.main.Slot3.Material")));
		meta.setDisplayName(ChatColor.of(Main.data.getItemConfig().getString("shops.ItemNameColor")) + "" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.main.Slot3.DisplayName"));
		lore.set(0, ChatColor.GRAY + "Click to shop " + Main.data.getItemConfig().getString("shops.main.Slot3.DisplayName") + ".");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		// Shop4
		
		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.main.Slot4.Material")));
		meta.setDisplayName(ChatColor.of(Main.data.getItemConfig().getString("shops.ItemNameColor")) + "" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.main.Slot4.DisplayName"));
		lore.set(0, ChatColor.GRAY + "Click to shop " + Main.data.getItemConfig().getString("shops.main.Slot4.DisplayName") + ".");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		// Shop5
		
		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.main.Slot5.Material")));
		meta.setDisplayName(ChatColor.of(Main.data.getItemConfig().getString("shops.ItemNameColor")) + "" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.main.Slot5.DisplayName"));
		lore.set(0, ChatColor.GRAY + "Click to shop " + Main.data.getItemConfig().getString("shops.main.Slot5.DisplayName") + ".");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		// Shop6
		
		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.main.Slot6.Material")));
		meta.setDisplayName(ChatColor.of(Main.data.getItemConfig().getString("shops.ItemNameColor")) + "" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.main.Slot6.DisplayName"));
		lore.set(0, ChatColor.GRAY + "Click to shop " + Main.data.getItemConfig().getString("shops.main.Slot6.DisplayName") + ".");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		// Shop7
		
		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.main.Slot7.Material")));
		meta.setDisplayName(ChatColor.of(Main.data.getItemConfig().getString("shops.ItemNameColor")) + "" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.main.Slot7.DisplayName"));
		lore.set(0, ChatColor.GRAY + "Click to shop " + Main.data.getItemConfig().getString("shops.main.Slot7.DisplayName") + ".");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(15, item);
		
		// Shop8
		
		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.main.Slot8.Material")));
		meta.setDisplayName(ChatColor.of(Main.data.getItemConfig().getString("shops.ItemNameColor")) + "" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.main.Slot8.DisplayName"));
		lore.set(0, ChatColor.GRAY + "Click to shop " + Main.data.getItemConfig().getString("shops.main.Slot8.DisplayName") + ".");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(16, item);
		
		// Shop9
		
		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.main.Slot9.Material")));
		meta.setDisplayName(ChatColor.of(Main.data.getItemConfig().getString("shops.ItemNameColor")) + "" + ChatColor.BOLD + Main.data.getItemConfig().getString("shops.main.Slot9.DisplayName"));
		lore.set(0, ChatColor.GRAY + "Click to shop " + Main.data.getItemConfig().getString("shops.main.Slot9.DisplayName") + ".");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(17, item);
		
		// Borders
		item.setType(Material.valueOf(Main.data.getItemConfig().getString("shops.BorderMaterial")));
		meta.setDisplayName(" ");
		meta.setLore(null);
		item.setItemMeta(meta);
		
		inv.setItem(35, item);
		inv.setItem(34, item);
		inv.setItem(33, item);
		inv.setItem(32, item);
		inv.setItem(30, item);
		inv.setItem(29, item);
		inv.setItem(28, item);
		inv.setItem(27, item);
		
	}

}
