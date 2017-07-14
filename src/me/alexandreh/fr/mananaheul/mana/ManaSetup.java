package me.alexandreh.fr.mananaheul.mana;

import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.alexandreh.fr.mananaheul.Main;
import me.alexandreh.fr.mananaheul.levels.ManabyLvl;

public class ManaSetup implements Listener{
	
	public static HashMap<OfflinePlayer, Integer> mana = new HashMap<OfflinePlayer, Integer>();
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoinEvent(PlayerJoinEvent e) throws SQLException{
		Player p = e.getPlayer();
		if(mana.get(p) == null){
			mana.put(p, ManabyLvl.getMaxMana(p));
		}
	}
	
}
