package net.hypixel.minigames.gamesystems.manager;

import org.bukkit.entity.Player;

import me.vagdedes.mysql.database.SQL;

public final class SQLManager {

	public static Object getStats(Player player, String s) {
		return SQL.get(s, "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
}
