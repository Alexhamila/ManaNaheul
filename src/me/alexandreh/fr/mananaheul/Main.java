package me.alexandreh.fr.mananaheul;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.alexandreh.fr.mananaheul.commands.ManaCommand;
import me.alexandreh.fr.mananaheul.mana.ManaSetup;
import me.alexandreh.fr.mananaheul.mysql.Secret;


public class Main extends JavaPlugin {
	
	public static Plugin pl;
	
	@Override
	public void onEnable() {
		getLogger().info(" ON!");
		saveDefaultConfig();
		regListeners();
		LoadHashmap();
		registerCommands();
	    try {
			Secret.connectDB();
		} catch (SQLException e) {
			
		e.printStackTrace();
		Bukkit.getLogger().warning("Connection failed !");	
		}
		}
	@Override
	public void onDisable(){
		getLogger().info("Plugin off");
		SaveHashmap();
		try {
			Secret.disconnectDB();
		} catch (SQLException e) {
			Bukkit.getLogger().warning("Disconnection failed !");
		}
	}
	
	public void regListeners(){
		// Bukkit.getPluginManager().registerEvents(new ManaLvl(), Main.pl);
	}
	
	public void registerCommands(){
		getCommand("mana").setExecutor(new ManaCommand());
	}

	public static void LoadHashmap(){
		for(String p: pl.getConfig().getConfigurationSection("HashMap").getKeys(false)){
			ManaSetup.mana.put(Bukkit.getOfflinePlayer(p), pl.getConfig().getInt("HashMap." + p));
		}    
	}
	public static void SaveHashmap(){
		FileConfiguration c = pl.getConfig();
		for(OfflinePlayer p : ManaSetup.mana.keySet()){
			int mana_p = ManaSetup.mana.get(p);
			c.set("HashMap."+p.getName(), mana_p);
		}
	    pl.saveConfig();
	}
	
}
