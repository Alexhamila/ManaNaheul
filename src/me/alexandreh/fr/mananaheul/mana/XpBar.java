package me.alexandreh.fr.mananaheul.mana;

import java.sql.SQLException;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.alexandreh.fr.mananaheul.commands.Console;
import me.alexandreh.fr.mananaheul.levels.ManabyLvl;

public class XpBar implements Listener{
	
	public static void setBar(Player p) throws SQLException{
		int mana = Console.GetMana(p);
		p.setLevel(mana);
	}
	
	public static void giveXP(Player p, int xp){
		int lvl = p.getLevel();
		p.sendMessage("add: " + xp + "from :" + lvl);
		p.setLevel(lvl + xp);
	}
	
	public static void removeXP(Player p, int xp){
		int lvl = p.getLevel();
		p.sendMessage("remove: " + xp + "from :" + lvl);
		p.setLevel(lvl - xp);
	}
	
	
	

}
