package net.hypixel.minigames.gamesystems.manager;

import org.bukkit.entity.Player;

import me.vagdedes.mysql.database.SQL;
import net.citizensnpcs.Settings.Setting;
import net.hypixel.minigames.Settings;

public class ExperienceManager {

	public static void addExp(Player player, int i) {
		SQL.set("xp", getCurrentExp(player) + i, "uuid", "=", player.getUniqueId().toString(), "thepit");
		SQL.set("total_xp", getTotalExp(player) + i, "uuid", "=", player.getUniqueId().toString(), "thepit");
	}
	
	public static double getCurrentExp(Player player) {
		return Double.parseDouble(SQL.get("xp", "uuid", "=", player.getUniqueId().toString(), "thepit").toString());
	}
	
	public static double getTotalExp(Player player) {
		return Double.parseDouble(SQL.get("total_xp", "uuid", "=", player.getUniqueId().toString(), "thepit").toString());
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
	
	public static int getLevelByExp(int prestige, double exp) {
		int i = 0;
		for(;i<Settings.levelRegularXP.length;i++) {
			if(Settings.levelRegularXP[i]*Settings.prestigeEXPPersent[prestige] >= exp) {
				return i+1;
			}
		}
		return i;
	}
	
}
