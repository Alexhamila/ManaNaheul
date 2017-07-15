package me.alexandreh.fr.mananaheul;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
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
	
	public Plugin plugin;
	public FileConfiguration config;
	public static HashMap<OfflinePlayer, Integer> mana = new HashMap<OfflinePlayer, Integer>();
	
	@Override
	public void onEnable() {
		getLogger().info(" ON!");
		plugin = this;
		config = this.getConfig();
		regListeners();
		registerCommands();
	    try {
			Secret.connectDB();
			ManaSetup.RetrievePlayerMana();
		} catch (SQLException e) {
			
		e.printStackTrace();
		Bukkit.getLogger().warning("Connection failed !");	
		}
	    }
	
	@Override
	public void onDisable(){
		getLogger().info("Plugin off");
		try {
			ManaSetup.SavePlayerMana();
			Secret.disconnectDB();
		} catch (SQLException e) {
			Bukkit.getLogger().warning("Disconnection failed !");
		}
	}
	
	public void regListeners(){
		Bukkit.getPluginManager().registerEvents(new ManaSetup(), plugin);
	}
	
	public void registerCommands(){
		getCommand("mana").setExecutor(new ManaCommand());
	}
}
	

