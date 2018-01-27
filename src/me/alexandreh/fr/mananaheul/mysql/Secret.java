package me.alexandreh.fr.mananaheul.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import net.md_5.bungee.api.ChatColor;

public class Secret {
	  
	  // Modif //
	  private static String host = "**.**.**.*";
	  private static String port = "****";
	  private static String database = "****";
	  private static String username = "***";
	  private static String password = "************";
	  private static Connection db;

	  
	  public static Connection getDB()
	  {
	    return db;
	  }
	  
	  public static void connectDB()
	    throws SQLException
	  {
	    if (!isConnected())
	    {
	      db = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useUnicode=yes", username, password);
	      Bukkit.getLogger().info(ChatColor.GREEN + "XP connecting to DB !");
	    }
	  }
	  
	  public static void disconnectDB()
	    throws SQLException
	  {
	    if (isConnected())
	    {
	      db.close();
	      Bukkit.getLogger().info(ChatColor.RED + "XP is disconnecting of DB");
	    }
	  }
	  
	  public static boolean isConnected()
	  {
	    return db != null;
	  }
	  
}
