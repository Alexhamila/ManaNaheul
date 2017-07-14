package me.alexandreh.fr.mananaheul.levels;

import java.sql.SQLException;

import org.bukkit.entity.Player;

public interface ManabyLvl {

	public static int getMaxMana(Player p) throws SQLException{
		
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
	
}
