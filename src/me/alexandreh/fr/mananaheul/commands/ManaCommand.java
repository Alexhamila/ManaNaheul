package me.alexandreh.fr.mananaheul.commands;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alexandreh.fr.mananaheul.Main;
import me.alexandreh.fr.mananaheul.chat.Prefix;
import me.alexandreh.fr.mananaheul.levels.LvlRetriever;
import me.alexandreh.fr.mananaheul.levels.ManabyLvl;
import me.alexandreh.fr.mananaheul.mana.ManaSetup;
import me.alexandreh.fr.mananaheul.mana.XpBar;
import me.alexandreh.fr.mananaheul.utils.IntegerCheck;
import net.md_5.bungee.api.ChatColor;

public class ManaCommand implements CommandExecutor{
	@SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("xp")){
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
							ManaSetup.mana.put(p.getName(), Console.GetMana(p));
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
					// /xp -5L ou 5L %					
					String xp = args[0];
					xp = xp.replace("L", ""); 
					String player_name = args[1];
					Player player = Bukkit.getPlayer(player_name);
					player.sendMessage(xp + "xp");
					if(xp.contains("-")){
						String xp_without = xp.substring(1);
						player.sendMessage(xp_without + "without");
						int xp_without_int = Integer.parseInt(xp_without);	
						player.sendMessage(xp_without_int + "without-int");
						try {
							if(ManaSetup.canRemoveMana(player.getName(), xp_without_int)){
								XpBar.removeXP(player, xp_without_int);
								Console.setMana(player);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						//Main.mana.put(player, Main.mana.get(player) - xp_without_int); 
					}else{
						int xp_int = Integer.parseInt(xp);
						player.sendMessage(xp_int + ":int");
						//Main.mana.put(player, Main.mana.get(player) + xp_int);
						try {
							if(ManaSetup.canAddMana(player.getName(), xp_int)){
								XpBar.giveXP(player, xp_int);
								Console.setMana(player);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				if(args.length == 3){
					if(sender.isOp()){
					if(args[0].equalsIgnoreCase("remove")){
						if(ManaSetup.mana.containsKey(Bukkit.getOfflinePlayer(args[1]))){
							if(IntegerCheck.IsInteger(args[2]) == true){
									try {
										if(ManaSetup.canRemoveMana(args[1], Integer.parseInt(args[2]))){
											ManaSetup.mana.put(args[1], ManaSetup.mana.get(Bukkit.getOfflinePlayer(args[1])) - Integer.parseInt(args[2]));
										if(Bukkit.getPlayer(args[1]) != null){
										XpBar.setBar(Bukkit.getPlayer(args[1]));
										}
										}else{
											sender.sendMessage("On ne peut pas retirer ce nombre de mana à ce joueur !");
										}
									} catch (NumberFormatException | SQLException e) {
										sender.sendMessage("Erreur connexion API !");
									}
								}else{
									sender.sendMessage("Mauvaise formulation de la commande.");
								}
						}else{
							sender.sendMessage("Le joueur n'existe pas.");
						}
					}
					if(args[0].equalsIgnoreCase("add")){
						if(ManaSetup.mana.containsKey(Bukkit.getOfflinePlayer(args[1]))){
							if(IntegerCheck.IsInteger(args[2]) == true){
								try {
									if(ManaSetup.canAddMana(args[1], Integer.parseInt(args[2]))){
										ManaSetup.mana.put(args[1], ManaSetup.mana.get(Bukkit.getOfflinePlayer(args[1])) + Integer.parseInt(args[2]));
										if(Bukkit.getPlayer(args[1]) != null){
										XpBar.setBar(Bukkit.getPlayer(args[1]));
										}
									}else{
										sender.sendMessage("Mauvaise formulation de la commande.");
									}
								} catch(NumberFormatException | SQLException e){
									sender.sendMessage("Erreur connexion API !");
							}
							
						}
					}
				}
			}
			}
		}
		return false;
	}
}

		
