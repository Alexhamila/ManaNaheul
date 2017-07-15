package me.alexandreh.fr.mananaheul.mana;

import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.alexandreh.fr.mananaheul.commands.Console;
import me.alexandreh.fr.mananaheul.levels.ManabyLvl;

public class XpBar implements Listener{
	
	public static void setBar(Player p) throws SQLException{
		int mana = Console.GetMana(p);
		p.setLevel(mana);
		
	}
	public static void removeBar(Player p){
		p.setExp(0);
	}
	
	public static void giveXP(Player p, int xp){
		int lvl = p.getExpToLevel();
		p.setLevel(lvl + xp);
	}
	
	public static void removeXP(Player p, int xp){
		int lvl = p.getExpToLevel();
		p.setLevel(lvl - xp);
	}

}
