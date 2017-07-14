package me.alexandreh.fr.mananaheul;

import org.bukkit.plugin.java.JavaPlugin;

import me.alexandreh.fr.mananaheul.commands.ManaCommand;


public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info(" ON!");
		regListeners();
		registerCommands();
	    }
	@Override
	public void onDisable(){
		getLogger().info("Plugin off");
	}
	
	public void regListeners(){
		// Bukkit.getPluginManager().registerEvents(new ManaLvl(), Main.pl);
	}
	
	public void registerCommands(){
		getCommand("mana").setExecutor(new ManaCommand());
	}
	
}
