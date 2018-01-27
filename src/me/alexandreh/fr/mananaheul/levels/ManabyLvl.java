package me.alexandreh.fr.mananaheul.levels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.alexandreh.fr.mananaheul.Main;
import me.alexandreh.fr.mananaheul.mana.ManaSetup;
import me.alexandreh.fr.mananaheul.mysql.Secret;

public interface ManabyLvl {

	public static int getMaxMana(String p) throws SQLException{
		
		int lvl = LvlRetriever.GetPlayerLvl(p);
		
		switch(lvl){
		
		case 0:
			return 0;
		case 1:
			return 10;
		case 2:
			return 15;
		case 3:
			return 20;
		case 4:
			return 25;
		case 5: 
			return 30;
		case 6:
			return 35;
		case 7: 
			return 40;
		case 8: 
			return 45;
		case 9:
			return 50;
		case 10: 
			return 55;
		case 11:
			return 60;
		case 12:
			return 65;
		case 13:
			return 70;
		case 14:
			return 75;
		case 15:
			return 80;
		case 16:
			return 85;
		case 17:
			return 90;
		case 18:
			return 95;
		case 19:
			return 100;
		case 20:
			return 105;
		default:
			return 0;
		}
		
	}
	
	public static int getLvlbyMana(String p) throws SQLException{
		
		int mana = ManaSetup.mana.get(p);
		
		switch(mana){
		
		case 0:
			return 0;
		case 10:
			return 1;
		case 15:
			return 2;
		case 20:
			return 3;
		case 25:
			return 4;
		case 30: 
			return 5;
		case 35:
			return 6;
		case 40: 
			return 7;
		case 45: 
			return 8;
		case 50:
			return 9;
		case 55: 
			return 10;
		case 60:
			return 11;
		case 65:
			return 12;
		case 70:
			return 13;
		case 75:
			return 14;
		case 80:
			return 15;
		case 85:
			return 16;
		case 90:
			return 17;
		case 95:
			return 18;
		case 100:
			return 19;
		case 105:
			return 20;
		default:
			return 0;
		}
		
	}
	
	public static int getLvlbyManaConverter(String p) throws SQLException{
		
		int mana = ManabyLvl.getPlayerMax(p);
		
		switch(mana){
		
		case 0:
			return 0;
		case 10:
			return 1;
		case 15:
			return 2;
		case 20:
			return 3;
		case 25:
			return 4;
		case 30: 
			return 5;
		case 35:
			return 6;
		case 40: 
			return 7;
		case 45: 
			return 8;
		case 50:
			return 9;
		case 55: 
			return 10;
		case 60:
			return 11;
		case 65:
			return 12;
		case 70:
			return 13;
		case 75:
			return 14;
		case 80:
			return 15;
		case 85:
			return 16;
		case 90:
			return 17;
		case 95:
			return 18;
		case 100:
			return 19;
		case 105:
			return 20;
		default:
			return 0;
		}
	}
	
	public static void setPlayerMax(String p) throws SQLException{
			 PreparedStatement manaUpdate = Secret.getDB().prepareStatement("UPDATE ncmana SET max= ? WHERE player = ?");
			 manaUpdate.setInt(1, ManabyLvl.getMaxMana(p));
	         manaUpdate.setString(2, p);
	         manaUpdate.executeUpdate();
		}
	public static int getPlayerMax(String p) throws SQLException{
		PreparedStatement ps = Secret.getDB().prepareStatement("SELECT max FROM ncmana WHERE player = '"  + p + "';");
		ps.executeQuery();
		ResultSet r = ps.getResultSet();
		
		if(r.next()){
			int max = r.getInt("max");
			return max;
		}else{
		return 0;
		}
	}
			
	}

