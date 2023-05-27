package net.jahcraft.sixpocketshop.main;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.jahcraft.sixpocketshop.commands.ShopCommand;
import net.jahcraft.sixpocketshop.files.DataManager;
import net.jahcraft.sixpocketshop.gui.ShopMain;
import net.jahcraft.sixpocketshop.listener.ShopMainListener;
import net.jahcraft.sixpocketshop.listener.SubShopListener;
import net.jahcraft.sixpocketshop.util.ShopStorage;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	public Economy eco;
	public static DataManager data;
	public static Main plugin;
	public static ShopStorage shopStorage;


	@Override
	public void onEnable() {
		
		Main.data = new DataManager(this);
		
		data.reloadConfig();
		
		if (!setupEconomy()) {
			
			Bukkit.getLogger().info("Economy not detected! Disabling Western Hunting!");
			getServer().getPluginManager().disablePlugin(this);
			return;
			
		}
		
		//SIXPOCKETSHOP
				try {
					
					//COMMANDS
					getCommand("shop").setExecutor((CommandExecutor) new ShopCommand());
					
					//LISTENERS
					getServer().getPluginManager().registerEvents(new ShopMainListener(), this);
					getServer().getPluginManager().registerEvents(new SubShopListener(this), this);
					
					//UTILITY
					shopStorage = new ShopStorage();
					shopStorage.markerStorage = shopStorage.getMarkers();
					
					//GUI
					ShopMain.createInv();
					
				} catch (Exception e) {

					Bukkit.getLogger().warning("Failed to load SixPocketShop!");
					getServer().getPluginManager().disablePlugin(this);

					e.printStackTrace();
					
				}
		
	}
	
	@Override 
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getOpenInventory() != null && p.getOpenInventory().getTopInventory().getSize() >= 45) {
				if (shopStorage.markerStorage.contains(p.getOpenInventory().getItem(45))) {
					p.closeInventory();
				}
			}
		}
		Bukkit.getLogger().info("SixPocketShop Unloaded and Disabled!");
		
	}
	
	private boolean setupEconomy() {
		
		RegisteredServiceProvider<Economy> economy = getServer().
				getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		
		if (economy != null)
			eco = economy.getProvider();
		return (eco != null);
		
	}

}
