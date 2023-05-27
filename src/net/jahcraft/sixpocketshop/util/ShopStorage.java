package net.jahcraft.sixpocketshop.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.jahcraft.sixpocketshop.gui.SubShop;
import net.jahcraft.sixpocketshop.main.Main;

public class ShopStorage {

	public HashMap<Player, Integer> pageStorage = new HashMap<>();
	public HashMap<Player, Integer> shopNoStorage = new HashMap<>();
	public ArrayList<ItemStack> markerStorage;
	
	public ArrayList<ItemStack> getMarkers() {
		ArrayList<ItemStack> markers = new ArrayList<>();
		for (int shopNo = 1; shopNo <= 9; shopNo++) {
			int maxPage = 1;
			while (Main.data.getItemConfig().getString("shops.shop" + shopNo + ".Page" + Integer.toString(maxPage) + ".Slot1.Material") != null) {
				markers.add(SubShop.createInv(null, shopNo, maxPage).getItem(45));
				maxPage += 1;
			}
		}
		
		return markers;
	}
	
}
