package net.hypixel.minigames.gamesystems.manager;

import org.bukkit.entity.Player;

import me.vagdedes.mysql.database.SQL;

public class GoldManager {

	public static void addGold(Player player, double gold) {
		
		int currentGold = Integer.parseInt(SQL.get("golds", "uuid", "=", player.getUniqueId().toString(), "thepit").toString());
		int totalGold = Integer.parseInt(SQL.get("total_gold", "uuid", "=", player.getUniqueId().toString(), "thepit").toString());
		int prestige_gold = Integer.parseInt(SQL.get("gold_current_prestige", "uuid", "=", player.getUniqueId().toString(), "thepit").toString());
		
		SQL.set("golds", currentGold + (gold*100), "uuid", "=", player.getUniqueId().toString(), "thepit");
		SQL.set("total_gold", totalGold + (gold*100), "uuid", "=", player.getUniqueId().toString(), "thepit");
		SQL.set("gold_current_prestige", prestige_gold + (gold*100), "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
	
	public static void setGold(Player player, double gold) {
		int currentGold = Integer.parseInt(SQL.get("golds", "uuid", "=", player.getUniqueId().toString(), "thepit").toString());
		SQL.set("golds", gold*100, "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
	
	public static void setPrestigeGold(Player player, double gold) {
		int prestige_gold = Integer.parseInt(SQL.get("gold_current_prestige", "uuid", "=", player.getUniqueId().toString(), "thepit").toString());
		SQL.set("gold_current_prestige", prestige_gold + (gold*100), "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
	
	public static double getGold(Player player) {
		return Integer.parseInt(SQL.get("golds", "uuid", "=", player.getUniqueId().toString(), "thepit").toString())/100;
	}

}
