package me.alexandreh.fr.mananaheul.levels;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.alexandreh.fr.mananaheul.mysql.Secret;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LvlRetriever {

	public static int GetPlayerLvl(String p) throws SQLException{
		
		PreparedStatement ps = Secret.getDB().prepareStatement("SELECT lvl FROM nclevels WHERE player = '"  + p + "';");
		ps.executeQuery();
		ResultSet r = ps.getResultSet();
		
		if(r.next()){
			int lvl = r.getInt("lvl");
			return lvl;
		}else{
		return 0;
		}
	}
	
}
