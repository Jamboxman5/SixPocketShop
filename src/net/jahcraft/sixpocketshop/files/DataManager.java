package net.jahcraft.sixpocketshop.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.jahcraft.sixpocketshop.main.Main;


public class DataManager {

	private Main plugin;
	private FileConfiguration priceConfig = null;
	private FileConfiguration itemConfig = null;
	private File priceConfigFile = null;
	private File itemConfigFile = null;
	
	public DataManager(Main main) {
		
		this.plugin = main;
		// saves/initializes the config
		saveDefaultConfig();
		
	}
	
	public void reloadConfig() {
		if (this.priceConfigFile == null)
			this.priceConfigFile = new File(this.plugin.getDataFolder(), "ChargeMaster.yml");
		if (this.itemConfigFile == null)
			this.itemConfigFile = new File(this.plugin.getDataFolder(), "shops.yml");
		
		this.priceConfig = YamlConfiguration.loadConfiguration(this.priceConfigFile);
		this.itemConfig = YamlConfiguration.loadConfiguration(this.itemConfigFile);
		
		InputStream defaultChargeMaster = this.plugin.getResource("ChargeMaster.yml");
		InputStream defaultItems = this.plugin.getResource("shops.yml");
		if (defaultChargeMaster != null) {
			YamlConfiguration defaultPriceConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultChargeMaster));
			this.priceConfig.setDefaults(defaultPriceConfig);
		}
		if (defaultItems != null) {
			YamlConfiguration defaultItemConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultItems));
			this.priceConfig.setDefaults(defaultItemConfig);
		}
	}
	
	public FileConfiguration getPriceConfig() {
		if (this.priceConfig == null)
			reloadConfig();
		
		return this.priceConfig;
	}
	public FileConfiguration getItemConfig() {
		if (this.itemConfig == null)
			reloadConfig();
		
		return this.itemConfig;
	}
	
	public void saveConfig() {
		if (this.priceConfig == null || this.priceConfigFile == null || this.itemConfig == null || this.itemConfigFile == null)
			return;
		
		try {
			this.getPriceConfig().save(this.priceConfigFile);
		} catch (IOException e) {
			this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.priceConfigFile, e);
		}
		
		try {
			this.getItemConfig().save(this.itemConfigFile);
		} catch (IOException e) {
			this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.itemConfigFile, e);
		}
	}
	
	public void saveDefaultConfig() {
		if (this.priceConfigFile == null)
			this.priceConfigFile = new File(this.plugin.getDataFolder(), "ChargeMaster.yml");
		
		if (!this.priceConfigFile.exists()) {
			this.plugin.saveResource("ChargeMaster.yml", false);
		}
		if (this.itemConfigFile == null)
			this.itemConfigFile = new File(this.plugin.getDataFolder(), "shops.yml");
		
		if (!this.itemConfigFile.exists()) {
			this.plugin.saveResource("shops.yml", false);
		}
	}
	
}
