package net.hypixel.minigames.gamesystems.file;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.hypixel.minigames.ThePit;

public class ConfigurationsList {

	private static FileConfiguration scoreboard_yml;
	private static File scoreboard_yml_f;
	
	public static void Startup_ScoreboardConfig() {
		scoreboard_yml_f = new File(ThePit.getInstance().getDataFolder(), "scoreboard.yml");
		
		if(!scoreboard_yml_f.exists()) {
			try {
				scoreboard_yml_f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		scoreboard_yml = YamlConfiguration.loadConfiguration(scoreboard_yml_f);
	}
	
	public static FileConfiguration getScoreboardConfig() {
		return scoreboard_yml;
	}
	
	public static void saveScoreboardConfig() {
		try {
			scoreboard_yml.save(scoreboard_yml_f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
