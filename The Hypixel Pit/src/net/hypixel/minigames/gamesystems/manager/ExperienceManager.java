package net.hypixel.minigames.gamesystems.manager;

import org.bukkit.entity.Player;

import me.vagdedes.mysql.database.SQL;

public class ExperienceManager {

	public static void addExp(Player player, int i) {
		SQL.set("xp", getCurrentExp(player) + i, "uuid", "=", player.getUniqueId().toString(), "thepit");
		SQL.set("total_xp", getTotalExp(player) + i, "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
	
	public static double getCurrentExp(Player player) {
		return Double.parseDouble(SQL.get("xp", "uuid", "=", player.getUniqueId().toString(), "thepit").toString())/100;
	}
	
	public static double getTotalExp(Player player) {
		return Double.parseDouble(SQL.get("total_xp", "uuid", "=", player.getUniqueId().toString(), "thepit").toString())/100;
	}
	
	public static void setExp(Player player, int i) {
		SQL.set("xp", i, "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
	
	public static void setLevel(Player player, int i) {
		SQL.set("level", i, "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
	
	public static int getLevel(Player player) {
		return Integer.parseInt(SQL.get("level", "uuid", "=", player.getUniqueId().toString(), "thepit").toString());
	}
	
}
