package me.alexandreh.fr.mananaheul.commands;

import java.sql.SQLException;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alexandreh.fr.mananaheul.Main;
import me.alexandreh.fr.mananaheul.levels.ManabyLvl;
import me.alexandreh.fr.mananaheul.mana.ManaSetup;

public class Console {
	

	public static void setMana(Player p){
		ManaSetup.mana.put(p.getName(), p.getLevel());
	}

	public static int GetMana(Player p) throws SQLException{
		boolean exists;
		
		for(String str : ManaSetup.mana.keySet()){
			if(str.equals(p.getName())){
				exists = true;
				return ManaSetup.mana.get(p.getName());
			}
		}
		if(exists = false){
			ManaSetup.mana.put(p.getName(), ManabyLvl.getMaxMana(p.getName()));
			return ManabyLvl.getMaxMana(p.getName());
		}
		exists = false;
		return 0;
		}
}
	
