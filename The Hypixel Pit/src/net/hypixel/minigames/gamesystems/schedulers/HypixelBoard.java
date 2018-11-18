package net.hypixel.minigames.gamesystems.schedulers;

import java.text.SimpleDateFormat;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import me.vagdedes.mysql.database.SQL;
import net.hypixel.minigames.Settings;
import net.hypixel.minigames.bungeecord.PML_Bungee;
import net.hypixel.minigames.gamesystems.file.ConfigurationsList;
import net.hypixel.minigames.gamesystems.manager.GoldManager;
import net.hypixel.minigames.gamesystems.manager.TitleManager;

public class HypixelBoard {

	public static void registerScoreboard(Player target) {
		List<String> scoreboardDefault = ConfigurationsList.getScoreboardConfig().getStringList("scoreboard.default");
		List<String> scoreboardPrestige = ConfigurationsList.getScoreboardConfig().getStringList("scoreboard.prestige");
		List<String> scoreboardNoEvtTexts = ConfigurationsList.getScoreboardConfig().getStringList("scoreboard.no-event");
		List<String> scoreboardEventWaitingTexts = ConfigurationsList.getScoreboardConfig().getStringList("scoreboard.waiting-event");
		List<String> scoreboardEventDuringTexts = ConfigurationsList.getScoreboardConfig().getStringList("scoreboard.event." + Settings.getCurrentEvent().getEventName());
		
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = sb.registerNewObjective(target.getName(), "dummy");
		target.setScoreboard(sb);
		int exists = 0;
		
		StringBuilder sbuilder = new StringBuilder();
		
		PML_Bungee.getServer(target);
		
		
		if(Settings.hasWaitingEvent()) {
			for(int i = 0;i<scoreboardEventWaitingTexts.size();i++) {
				obj.getScore(scoreboardEventWaitingTexts.get(scoreboardEventWaitingTexts.size()-i-1)).setScore(exists+1);
				exists++;
			}
			exists++;
		}
		
		if(Settings.duringEvent()) {
			for(int i = 0;i<scoreboardEventDuringTexts.size();i++) {
				obj.getScore(scoreboardEventDuringTexts.get(scoreboardEventDuringTexts.size()-i-1)).setScore(exists+1);
				exists++;
			}
		}
		int level = Integer.parseInt(SQL.get("level", "uuid", "=", target.getUniqueId().toString(), "thepit").toString());
		int needed_xp;
		String needed_xp_str = "";
		if(level < 120) {
			needed_xp = (int) (((Settings.levelRegularXP[level] * (Settings.prestigeEXPPersent[Integer.parseInt(SQL.get("prestige", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())])) - (Integer.parseInt(SQL.get("xp", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())+1)));

			
			if(needed_xp <= 0) {
				TitleManager.sendTitle(target, "§b§lLEVEL UP!", "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", target.getUniqueId().toString(), "thepit").toString() + "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())] + "]" + "§7 ➟ " + "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())] + Integer.toString(Integer.parseInt(SQL.get("level", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())+1) + "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())] + "]");
				SQL.set("level", level+1, "uuid", "=", target.getUniqueId().toString(), "thepit");
			}
			
			needed_xp_str = "§b" + Integer.toString(needed_xp);
		}
		
		if(level == 120) {
			needed_xp_str = "§bMAXED!";
		}
		
		for(int i = 0;i<scoreboardNoEvtTexts.size();i++) {
			obj.setDisplayName("§e§lTHE HYPIXEL PIT");
			if(!Settings.serverStatus.isVisible())
				obj.getScore(scoreboardNoEvtTexts.get(scoreboardNoEvtTexts.size()-i-1).replace("{USER_STATUS}", Settings.userStatus.get(target).getStatus()).replace("{LEVEL}", "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", target.getUniqueId().toString(), "thepit").toString() + "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())] + "]").replace("{GOLD}", "§6" + GoldManager.getGold(target) + "§6g").replace("{NEEDED_XP}", needed_xp_str)).setScore(exists+1);
			else
				obj.getScore(scoreboardNoEvtTexts.get(scoreboardNoEvtTexts.size()-i-1).replace("{USER_STATUS}", Settings.userStatus.get(target).getStatus()).replace("{LEVEL}", "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", target.getUniqueId().toString(), "thepit").toString() + "§" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())] + "]").replace("{GOLD}", "§6" + GoldManager.getGold(target) + "§6g").replace("{NEEDED_XP}", needed_xp_str)).setScore(exists+1);
			exists++;
		}
		
		if(Integer.parseInt(SQL.get("prestige", "uuid", "=", target.getUniqueId().toString(), "thepit").toString()) != 0) {
			for(int i = 0;i<scoreboardPrestige.size();i++) {
				obj.getScore(scoreboardPrestige.get(scoreboardPrestige.size()-i-1).replace("{PRESTIGE}", "§e" + Settings.prestigeStringList[Integer.parseInt(SQL.get("prestige", "uuid", "=", target.getUniqueId().toString(), "thepit").toString())])).setScore(exists+1);
				exists++;
			}
			
		}
		
		for(int i = 0;i<scoreboardDefault.size();i++) {
			try {
				obj.getScore(scoreboardDefault.get(scoreboardDefault.size()-i-1).replace("{DATE}", "§7" + new SimpleDateFormat("mm.DD.yy").toPattern().replace(".", "/")).replace("{SERVER}", "§8" + PML_Bungee.serverName)).setScore(exists+1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			exists++;
		}
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
	}
}
