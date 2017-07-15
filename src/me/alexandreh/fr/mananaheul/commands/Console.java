package me.alexandreh.fr.mananaheul.commands;

import java.sql.SQLException;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alexandreh.fr.mananaheul.Main;
import me.alexandreh.fr.mananaheul.levels.ManabyLvl;

public class Console {
	
	public static void RemoveMana(CommandSender s, Player p, int remove_mana) throws SQLException{
		int p_mana = GetMana(p);
		if(p_mana >= remove_mana){
		}else{
			s.sendMessage(p.getName() + " n'a pas assez de mana ("+p_mana + "/" + remove_mana + ")");
		}
	}
	public static void AddMana(Player p, int mana){
	
		Main.mana.put(p, Main.mana.get(p) + mana);
	
	}

	public static int GetMana(Player p) throws SQLException{
		if(Main.mana.containsKey(p) != true){
			Main.mana.put(p, ManabyLvl.getMaxMana(p));
			return ManabyLvl.getMaxMana(p);
		}else if(Main.mana.containsKey(p)){	
			return Main.mana.get(p);
		}else{
		return 0;
		}
	}
	
}
