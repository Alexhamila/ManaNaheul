package me.alexandreh.fr.mananaheul.mana;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.alexandreh.fr.mananaheul.Main;

public class Restore {

	public static void RunTimer(){
		
	int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
		 
        @Override
        public void run() {
          	
        for (String p : ManaSetup.mana.keySet()){
        	int mana = ManaSetup.mana.get(p);
        	try {
				if(ManaSetup.canAddMana(p, 1)){
					ManaSetup.mana.put(p, ManaSetup.mana.get(p) + 1);
					if(Bukkit.getPlayer(p) != null){
					try {
						XpBar.setBar(Bukkit.getPlayer(p));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        	
        }

    }, 0L,  800L);
	}
}
