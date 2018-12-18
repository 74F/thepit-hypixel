package net.hypixel.minigames.gamesystems.schedulers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import me.vagdedes.mysql.database.SQL;
import net.hypixel.minigames.Settings;
import net.hypixel.minigames.bungeecord.PML_Bungee;
import net.hypixel.minigames.gamesystems.file.ConfigurationsList;
import net.hypixel.minigames.gamesystems.manager.ExperienceManager;
import net.hypixel.minigames.gamesystems.manager.GoldManager;
import net.hypixel.minigames.gamesystems.manager.TitleManager;

public class HypixelBoard extends BukkitRunnable {

	private static int i = 0;
	
	public void run() {
		for(Player online : Bukkit.getOnlinePlayers()) {
			List<String> scoreboardName = ConfigurationsList.getScoreboardConfig().getStringList("scoreboard.name");
			List<String> scoreboardDefault = ConfigurationsList.getScoreboardConfig().getStringList("scoreboard.default");
			List<String> scoreboardPrestige = ConfigurationsList.getScoreboardConfig().getStringList("scoreboard.prestige");
			List<String> scoreboardNoEvtTexts = ConfigurationsList.getScoreboardConfig().getStringList("scoreboard.no-event");
			List<String> scoreboardEventWaitingTexts = ConfigurationsList.getScoreboardConfig().getStringList("scoreboard.waiting-event");
			List<String> scoreboardEventDuringTexts = ConfigurationsList.getScoreboardConfig().getStringList("scoreboard.event." + Settings.getCurrentEvent().getEventName());
			
			Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
			List<String> list = new ArrayList();
			int exists = 0;
			
			PML_Bungee.getServer(online);
			
			
			if(Settings.hasWaitingEvent()) {
				for(int i = 0;i<scoreboardEventWaitingTexts.size();i++) {
					list.add(scoreboardEventWaitingTexts.get(scoreboardEventWaitingTexts.size()-i-1));
					exists++;
				}
				exists++;
			}
			
			if(Settings.duringEvent()) {
				for(int i = 0;i<scoreboardEventDuringTexts.size();i++) {
					list.add(scoreboardEventDuringTexts.get(scoreboardEventDuringTexts.size()-i-1));
					exists++;
				}
			}
			int level = ExperienceManager.getLevel(online);
			int needed_xp;
			String needed_xp_str = "";
			
			if(level < 120) {
				needed_xp = (int) (((Settings.levelRegularXP[level] * (Settings.prestigeEXPPersent[Integer.parseInt(SQL.get("prestige", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())])) - (Integer.parseInt(SQL.get("xp", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())+1)));
				needed_xp_str = "§b" + Integer.toString(needed_xp);
			}
			
			if(level == 120) {
				needed_xp_str = "§bMAXED!";
			}

			for(int i = 0;i<scoreboardNoEvtTexts.size();i++) {
				if(!Settings.serverStatus.isVisible())
					list.add(scoreboardNoEvtTexts.get(scoreboardNoEvtTexts.size()-i-1).replace("{USER_STATUS}", Settings.userStatus.get(online).getStatus()).replace("{LEVEL}", "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[level-1] + level + "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())] + "]").replace("{GOLD}", "§6" + GoldManager.getGold(online) + "§6g").replace("{NEEDED_XP}", needed_xp_str));
				else
					list.add(scoreboardNoEvtTexts.get(scoreboardNoEvtTexts.size()-i-1).replace("{USER_STATUS}", Settings.userStatus.get(online).getStatus()).replace("{LEVEL}", "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[level-1] + SQL.get("level", "uuid", "=", online.getUniqueId().toString(), "thepit").toString() + "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())] + "]").replace("{GOLD}", "§6" + GoldManager.getGold(online) + "§6g").replace("{NEEDED_XP}", needed_xp_str));
				exists++;
			}
			
			if(Integer.parseInt(SQL.get("prestige", "uuid", "=", online.getUniqueId().toString(), "thepit").toString()) != 0) {
				for(int i = 0;i<scoreboardPrestige.size();i++) {
					list.add(scoreboardPrestige.get(scoreboardPrestige.size()-i-1).replace("{PRESTIGE}", "§e" + Settings.prestigeStringList[Integer.parseInt(SQL.get("prestige", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())]));
					exists++;
				}
				
			}
			
			for(int i = 0;i<scoreboardDefault.size();i++) {
				try {
					list.add(scoreboardDefault.get(scoreboardDefault.size()-i-1).replace("{DATE}", "§7" + new SimpleDateFormat("mm.DD.yy").toPattern().replace(".", "/")).replace("{SERVER}", "§8" + PML_Bungee.serverName));
				} catch (Exception e) {
					e.printStackTrace();
				}
				exists++;
			}
			Objective obj = sb.registerNewObjective(online.getName(), "dummy");
			i++;
			for(int i = 0;i<list.size();i++) {
				obj.getScore(list.get(i)).setScore(i+1);
			}
			if(i>=scoreboardName.size())
				i = 0;
			if(i<scoreboardName.size())
				obj.setDisplayName(scoreboardName.get(i));
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			online.setScoreboard(obj.getScoreboard());
		}
	}
}
