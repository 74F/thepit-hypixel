package net.hypixel.minigames.gamesystems.manager;

import org.bukkit.entity.Player;

import me.vagdedes.mysql.database.SQL;

public class PrestigeManager {

	public static void setPrestige(Player player, int prestige) {
		GoldManager.setGold(player, 0);
		GoldManager.setPrestigeGold(player, 0);
		ExperienceManager.setExp(player, 0);
		ExperienceManager.setLevel(player, 1);
		SQL.set("prestige", prestige, "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
	
	public static int getPrestige(Player player) {
		return Integer.parseInt(SQL.get("prestige", "uuid", "=", player.getUniqueId().toString(), "thepit").toString());
	}
}
