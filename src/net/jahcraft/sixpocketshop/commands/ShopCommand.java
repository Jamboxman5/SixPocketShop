package net.jahcraft.sixpocketshop.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.jahcraft.sixpocketshop.gui.ShopMain;
import net.md_5.bungee.api.ChatColor;

public class ShopCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("shop")) {
			
			if (!(sender instanceof Player)) {
				sender.sendMessage("You cannot do this!");
				return true;
			}
			
			
			Player player = (Player) sender;

			if (player.hasPermission("spc.shop")) {
				
				Inventory inv = ShopMain.inv;
				
				
				player.openInventory(inv);
				return true;
				
			}
			
			player.sendMessage(ChatColor.RED + "You do not have permission to open the shop yet!");
			return true;
			
		}
		
		return false;
		
	}

}
