package me.alexandreh.fr.mananaheul.commands;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alexandreh.fr.mananaheul.Main;
import me.alexandreh.fr.mananaheul.chat.Prefix;
import me.alexandreh.fr.mananaheul.utils.IntegerCheck;
import net.md_5.bungee.api.ChatColor;

public class ManaCommand implements CommandExecutor{
	@SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("mana")){
			if(args.length == 0){
				if(sender instanceof Player){
					Player p = (Player) sender;		
					// Get Player Mana //
						try {
							p.sendMessage(Prefix.prefix + ChatColor.BLUE + "Mana: " + ChatColor.RESET + Console.GetMana(p));
						} catch (SQLException e1) {
							p.sendMessage(Prefix.prefix + ChatColor.RED + " Erreur !");
						}
						try {
							Main.mana.put(p, Console.GetMana(p));
						} catch (SQLException e) {
							
						}
				}else{
					sender.sendMessage("Seulement les joueurs peuvent utiliser cette commande.");
				}
			}else
				if(args.length == 1){
					sender.sendMessage("Mauvaise formulation de la commande.");
				}
				if(args.length == 2){
					sender.sendMessage("Mauvaise formulation de la commande.");
				}
				if(args.length == 3){
					if(args[0].equalsIgnoreCase("remove")){
						if(Main.mana.containsKey(Bukkit.getOfflinePlayer(args[1]))){
							if(IntegerCheck.IsInteger(args[2]) == true){
								// Check if soustraction is possible
								Main.mana.put(Bukkit.getOfflinePlayer(args[1]), Main.mana.get(Bukkit.getOfflinePlayer(args[1])) - Integer.parseInt(args[2]));
							}else{
								sender.sendMessage("Mauvaise formulation de la commande.");
							}
						}else{
							sender.sendMessage("Le joueur n'existe pas.");
						}
					}
					if(args[0].equalsIgnoreCase("add")){
						if(Main.mana.containsKey(Bukkit.getOfflinePlayer(args[1]))){
							if(IntegerCheck.IsInteger(args[2]) == true){
								Main.mana.put(Bukkit.getOfflinePlayer(args[1]), Main.mana.get(Bukkit.getOfflinePlayer(args[1])) + Integer.parseInt(args[2]));
								}else{
								sender.sendMessage("Mauvaise formulation de la commande.");
								}
							
						}
					}
				}
		}
		return false;
	}
}
