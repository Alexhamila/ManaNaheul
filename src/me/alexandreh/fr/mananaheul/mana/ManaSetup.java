package me.alexandreh.fr.mananaheul.mana;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_11_R1.CraftOfflinePlayer;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.alexandreh.fr.mananaheul.Main;
import me.alexandreh.fr.mananaheul.levels.LvlRetriever;
import me.alexandreh.fr.mananaheul.levels.ManabyLvl;
import me.alexandreh.fr.mananaheul.mysql.Secret;

public class ManaSetup implements Listener{
	
	static Hash mana_hash = new Hash();
	
	public static HashMap<String, Integer> mana = mana_hash.getMana();
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoinEvent(PlayerJoinEvent e) throws SQLException{
		Player p = e.getPlayer();
		
		boolean exists;
		
		for(String str : mana.keySet()){
			if(str.equals(p.getName())){
			exists = true;
			}
		}
		
		if(exists = false){
		mana.put(p.getName(), ManabyLvl.getMaxMana(p.getName()));
        //Test if player is in table
        PreparedStatement testingExist_query = Secret.getDB().prepareStatement("SELECT id FROM ncmana WHERE player = ?");
        testingExist_query.setString(1, e.getPlayer().getName());
        ResultSet exist_query = testingExist_query.executeQuery();
        
        PreparedStatement insertIntoLevels = Secret.getDB().prepareStatement("INSERT INTO ncmana(player, mana, max) VALUES(?,?,?)");
        
        if(!exist_query.next())
        {
            insertIntoLevels.setString(1, e.getPlayer().getName());
            insertIntoLevels.setInt(2, ManabyLvl.getMaxMana(p.getName()));
            insertIntoLevels.setInt(3, ManabyLvl.getMaxMana(p.getName()));
            insertIntoLevels.executeUpdate();
        }
		}
		exists = false;
		XpBar.setBar(p);
	}
	@EventHandler(priority=EventPriority.NORMAL)
	public void onPlayerDeath(PlayerDeathEvent e){
		e.setKeepLevel(true);
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void onPlayerLeaveEvent(PlayerQuitEvent e) throws SQLException{
		Player p = e.getPlayer();

		mana.put(p.getName(), p.getLevel());
		ManaSetup.SaveSpecificPlayerMana(p.getName());
	}

	
	public static void RetrievePlayerMana() throws SQLException{
		
		PreparedStatement ps = Secret.getDB().prepareStatement("SELECT player,mana FROM ncmana");
		ps.executeQuery();
		ResultSet r = ps.getResultSet();
		
		while (r.next()){
			int mana_mana = r.getInt("mana");
			@SuppressWarnings("deprecation")
			String p = r.getString("player"); 
		if(mana.containsKey(p) != true){
			mana.put(p, mana_mana);
		}
		}
	}
	
	public static void SavePlayerMana() throws SQLException{
		
		for(String p : mana.keySet()){
		 PreparedStatement manaUpdate = Secret.getDB().prepareStatement("UPDATE ncmana SET mana = ?, max = ? WHERE player = ?");
		 manaUpdate.setInt(1, mana.get(p));
		 manaUpdate.setInt(2, ManabyLvl.getMaxMana(p));
         manaUpdate.setString(3, p);
         manaUpdate.executeUpdate();
		}
         
	}
	
	public static void SaveSpecificPlayerMana(String p) throws SQLException{
		 PreparedStatement manaUpdate = Secret.getDB().prepareStatement("UPDATE ncmana SET mana = ?, max = ? WHERE player ='" + p + "';");
		 manaUpdate.setInt(1, mana.get(p));
		 manaUpdate.setInt(2, ManabyLvl.getMaxMana(p));
         manaUpdate.executeUpdate();
	}
	
	public static boolean canRemoveMana(String p, int mana_mana) throws SQLException{
		int mana_max = ManabyLvl.getMaxMana(p);
		int mana_current = mana.get(p);
		
		if(0 <= mana_current - mana_mana && mana_current - mana_mana <= mana_max ){
			return true;
		}else{
		return false;
		}
	}
	public static boolean canAddMana(String p, int mana_mana) throws SQLException{
		int mana_max = ManabyLvl.getMaxMana(p);
		int mana_current = mana.get(p);
		
		if(mana_max >= mana_current + mana_mana && 0 <= mana_current + mana_mana){
			return true;
		}else{
		return false;
		}
	}
	
}
