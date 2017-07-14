package me.alexandreh.fr.mananaheul.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alexandreh.fr.mananaheul.chat.Prefix;
import net.md_5.bungee.api.ChatColor;

public class ManaCommand implements CommandExecutor{
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("mana")){
					
			if(args.length == 0){
				if(sender instanceof Player){
					Player p = (Player) sender;
					
					// Get Player Mana //
					p.sendMessage(Prefix.prefix + ChatColor.BLUE + "Mana: " + ChatColor.RESET );
					
				}else{
					sender.sendMessage("Only Players can use this command.");
				}
				
			}
			
			
			
			
		}
		
		
		
		return false;
		
		
	
	
	
	}
}
