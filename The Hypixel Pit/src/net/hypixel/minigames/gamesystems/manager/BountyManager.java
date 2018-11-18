package net.hypixel.minigames.gamesystems.manager;

import org.bukkit.entity.Player;

import me.vagdedes.mysql.database.SQL;

public class BountyManager {

	public static boolean hasBounty(Player player) {
		if(Integer.parseInt(SQL.get("bounty", "uuid", "=", player.getUniqueId().toString(), "thepit").toString()) != 0) {
			return true;
		}
		return false;
	}
	
	public static int getBounty(Player player) {
		return Integer.parseInt(SQL.get("bounty", "uuid", "=", player.getUniqueId().toString(), "thepit").toString());
	}
	
	public static void removeBounty(Player player) {
		SQL.set("bounty", 0, "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
	
	public static void addBounty(Player player, int value) {
		SQL.set("bounty", Integer.parseInt(SQL.get("bounty", "uuid", "=", player.getUniqueId().toString(), "thepit").toString()) + value, "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
	
	public static void setBounty(Player player, int value) {
		SQL.set("bounty", value, "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
}
