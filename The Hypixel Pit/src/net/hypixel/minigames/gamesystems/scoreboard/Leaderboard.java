package net.hypixel.minigames.gamesystems.scoreboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import me.vagdedes.mysql.database.SQL;
import net.hypixel.minigames.Settings;
import net.hypixel.minigames.ThePit;

public class Leaderboard {

	private static final String title = "」b」lTOP ACTIVE PLAYERS";
	private static final String header = "」7Pit Level";
	private static final String footer1 = "」7」oLeaderboard resets weekly";
	private static final String footer2 = "」7」oLogin to refresh your level";
	private Location location;
	private JavaPlugin plugin;
	
	private ArrayList<ArmorStand> armorstands = new ArrayList<ArmorStand>();
	
	public Leaderboard(JavaPlugin plugin, Location location) {
		this.plugin = plugin;
		this.location = location;
		createLeaderboard();
		ThePit.getInstance().getServer().getScheduler().runTaskTimer(ThePit.getInstance(), this::updateLeaderboard, 0, 20);
	}
	
	private void createLeaderboard() {
		Location loc = location.clone();
		createArmorStand(title, loc);
		loc = loc.subtract(0, 0.3, 0);
		createArmorStand(header, loc);
		loc = loc.subtract(0, 0.2, 0);
		for(int i = 0;i<10;i++)
		{
			loc = loc.subtract(0, 0.2, 0);
			armorstands.add(createArmorStand("」7...", loc));
		}
		loc = loc.subtract(0, 0.4, 0);
		createArmorStand(footer1, loc);
		loc = loc.subtract(0, 0.3, 0);
		createArmorStand(footer2, loc);
		loc = loc.subtract(0, 0.3, 0);
	}
	
	private void updateLeaderboard() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:localhost:3306/fakepixel_network", "root", "secret00");
			
			PreparedStatement pstate = connection.prepareStatement("SELECT * FROM `total_xp` ORDER BY `total_xp` `total_xp` DESC LIMIT 10;");
			ResultSet rs = pstate.executeQuery();
			int i = 0;
			while(rs.next()) {
				armorstands.get(i).setCustomName("」e" + (i+1) + ". 」r" + ((String) SQL.get("prefix", "id", "=", (String) SQL.get("rank", "uuid", "=", Bukkit.getOfflinePlayer(UUID.fromString(rs.getString("uuid").toString())).getUniqueId().toString(), "userdata"), "rankdata")).substring(0, 2) + "" + Bukkit.getOfflinePlayer(UUID.fromString(rs.getString("uuid").toString())).getName() + " 」7- " + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", Bukkit.getOfflinePlayer(UUID.fromString(rs.getString("uuid").toString())).getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", Bukkit.getOfflinePlayer(UUID.fromString(rs.getString("uuid").toString())).getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", Bukkit.getOfflinePlayer(UUID.fromString(rs.getString("uuid").toString())).getUniqueId().toString(), "thepit").toString() + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", Bukkit.getOfflinePlayer(UUID.fromString(rs.getString("uuid").toString())).getUniqueId().toString(), "thepit").toString())] + "] 」7(" + "」b" + (Double.parseDouble(SQL.get("total_xp", "uuid", "=", Bukkit.getOfflinePlayer(UUID.fromString(rs.getString("uuid").toString())).getUniqueId().toString(), "").toString())/1000000) + "M XP」7)");
				i++;
			}
			
			for(;i<10;i++) {
				armorstands.get(i).setCustomName("」7...");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private ArmorStand createArmorStand(String name, Location location) {
		
		ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
		armorStand.setGravity(false);
		armorStand.setVisible(false);
		armorStand.setCustomName(name);
		armorStand.setCustomNameVisible(true);
		return armorStand;
		
	}
}
