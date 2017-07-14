package me.alexandreh.fr.mananaheul.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Console {
	public static void RemoveMana(CommandSender s, Player p, int remove_mana){
		int p_mana = GetMana(p);
		if(p_mana >= remove_mana){
		}else{
			s.sendMessage(p.getName() + " n'a pas assez de mana ("+p_mana + "/" + remove_mana + ")");
		}
	}
	public static void AddMana(Player p, int mana){
	}
	public static int GetMana(Player p){
		return 0;

	}
	
}
