package net.hypixel.minigames.gamesystems.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.vagdedes.mysql.database.SQL;
import net.hypixel.minigames.Settings;
import net.hypixel.minigames.gamesystems.manager.ExperienceManager;
import net.hypixel.minigames.gamesystems.manager.PrestigeManager;
import net.hypixel.minigames.gamesystems.manager.TitleManager;

public class LevelingScheduler extends BukkitRunnable {

	public void run() {
		for(Player online : Bukkit.getOnlinePlayers()) {
			int level = ExperienceManager.getLevel(online);
			int needed_xp;
			int levelByExp = (ExperienceManager.getLevelByExp(PrestigeManager.getPrestige(online), ExperienceManager.getCurrentExp(online)));
			if(level < 120) {
				needed_xp = (int) (((Settings.levelRegularXP[level] * (Settings.prestigeEXPPersent[Integer.parseInt(SQL.get("prestige", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())])) - (Integer.parseInt(SQL.get("xp", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())+1)));
	
				
				if(needed_xp <= 0) {
					TitleManager.sendTitle(online, "§b§lLEVEL UP!", "§" + Settings.prestigeColorArray[PrestigeManager.getPrestige(online)] + "[" + Settings.levelColor[level-1] + level + "§" + Settings.prestigeColorArray[PrestigeManager.getPrestige(online)] + "]" + "§7 ➟ " + "§" + Settings.prestigeColorArray[PrestigeManager.getPrestige(online)] + "[" + Settings.levelColor[levelByExp-1] + levelByExp + "§" + Settings.prestigeColorArray[PrestigeManager.getPrestige(online)] + "]");
					ExperienceManager.setLevel(online, levelByExp);
				}
			}
		}
	}
}
