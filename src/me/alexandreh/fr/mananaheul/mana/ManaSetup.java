package me.alexandreh.fr.mananaheul.mana;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import me.alexandreh.fr.mananaheul.mysql.Secret;

public class ManaSetup implements Listener{
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoinEvent(PlayerJoinEvent e) throws SQLException{
		Player p = e.getPlayer();
		if(Main.mana.containsKey(p) != true){
			Main.mana.put(p, ManabyLvl.getMaxMana(p));
		}
	}
	
	public static void RetrievePlayerMana() throws SQLException{
		
		PreparedStatement ps = Secret.getDB().prepareStatement("SELECT player,mana FROM ncmana");
		ps.executeQuery();
		ResultSet r = ps.getResultSet();
		
		while (r.next()){
			int mana = r.getInt("mana");
			@SuppressWarnings("deprecation")
			OfflinePlayer p = Bukkit.getOfflinePlayer(r.getString("player")); 
		if(Main.mana.containsKey(p) != true){
			Main.mana.put(p, mana);
		}
		}
	}
	
	public static void SavePlayerMana() throws SQLException{
		
		for(OfflinePlayer p : Main.mana.keySet()){
		 PreparedStatement manaUpdate = Secret.getDB().prepareStatement("UPDATE ncmana SET mana = ? WHERE player = ?");
		 manaUpdate.setInt(1, Main.mana.get(p));
         manaUpdate.setString(2, p.getName());
         manaUpdate.executeUpdate();
		}
         
	}
	
}
