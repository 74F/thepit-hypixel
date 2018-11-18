package net.hypixel.minigames;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.vagdedes.mysql.database.SQL;
import net.hypixel.minigames.bungeecord.PML_Bungee;
import net.hypixel.minigames.gamesystems.commands.Command_DoPrestige;
import net.hypixel.minigames.gamesystems.commands.Command_OpenGui;
import net.hypixel.minigames.gamesystems.file.ConfigurationsList;
import net.hypixel.minigames.gamesystems.gui.GUIListener;
import net.hypixel.minigames.gamesystems.listeners.ListenerEvent;
import net.hypixel.minigames.gamesystems.manager.PerkManager;
import net.hypixel.minigames.gamesystems.schedulers.FreeXP;
import net.hypixel.minigames.gamesystems.schedulers.HypixelBoard;
import net.hypixel.minigames.gamesystems.status.UserStatus;
import net.hypixel.minigames.upgrades.perks.PerkRambo;

public class ThePit extends JavaPlugin {

	private static String version = "v0.1";
	private GUIListener GL;
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Bukkit.getConsoleSender().sendMessage("」cFailed to load MySQL JDBC Driver. The Pit Plugin Disabling...");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		getConfig().options().copyDefaults();
		saveConfig();

		getCommand("opengui").setExecutor(new Command_OpenGui());
		Command openGuiCMD = getCommand("opengui");
		getCommand("doprestige").setExecutor(new Command_DoPrestige());
		Command DoPrestigeCMD = getCommand("doprestige");
		openGuiCMD.setPermission("pit.test");
		openGuiCMD.setPermissionMessage("This is Test Command for Developer, You cannot use this!");
		DoPrestigeCMD.setPermission("pit.test");
		DoPrestigeCMD.setPermissionMessage("This is Test Command for Developer, You cannot use this!");
		
		GL = new GUIListener();

		//new Leaderboard(this, new Location(Bukkit.getWorlds().get(0), 13.5, 119, 0.5));
		getServer().getPluginManager().registerEvents(new ListenerEvent(), this);
		getServer().getPluginManager().registerEvents(GL, this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PML_Bungee());
		Bukkit.getConsoleSender().sendMessage("」aThe Hypixel Pit 」7- 」eCopied by 74F / Special Thanks: 」c[ADMIN] Minikloon 」eon Hypixel");
		Bukkit.getConsoleSender().sendMessage("」aThe Hypixel Pit 」7- 」aEnabled!");
		Bukkit.getConsoleSender().sendMessage("」aThe Hypixel Pit 」7- 」bWelcome to the Hypixel Pit!");
		
		ConfigurationsList.Startup_ScoreboardConfig();
		ConfigurationsList.getScoreboardConfig().options().copyDefaults(true);
		ConfigurationsList.saveScoreboardConfig();
		
		for(Player online : Bukkit.getOnlinePlayers()) {
			Settings.userStatus.put(online, UserStatus.IDLE);
		}
		
		PerkManager.registerPerks();
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player online : Bukkit.getOnlinePlayers()) {
					
					
					HypixelBoard.registerScoreboard(online);
					String tabAndChatSyntax = ((String) SQL.get("prefix", "id", "=", (String) SQL.get("rank", "uuid", "=", online.getUniqueId().toString(), "userdata"), "rankdata")).substring(0, 2) + online.getName();
					
					online.setPlayerListName("」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", online.getUniqueId().toString(), "thepit").toString() + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", online.getUniqueId().toString(), "thepit").toString())] + "] " + tabAndChatSyntax);
					
					if(PerkManager.hasPerk(online, new PerkRambo())) {
						online.setMaxHealth(16);
					} else {
						online.resetMaxHealth();
						return;
					}
				}
			}
		}, 0, 20);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new FreeXP(), 0, 20*60*5);
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("」aThe Hypixel Pit 」7- 」cEnabled!");
	}

	public static ThePit getInstance() {
		return ThePit.getPlugin(ThePit.class);
	}
	
	public static GUIListener getGL() {
		return getInstance().GL;
	}
	
	public String getVersion() {
		return version;
	}
	
}
