package net.hypixel.minigames.gamesystems.gui.sub;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.hypixel.minigames.ThePit;
import net.hypixel.minigames.gamesystems.gui.BaseGUI.GUIClickEvent;
import net.hypixel.minigames.gamesystems.gui.BaseGUI.GUIClickEventHandler;
import net.hypixel.minigames.gamesystems.manager.SQLManager;

public class AllStatsViewerGUI {

	private static final int menuSlotsPerRow = 9;
	private static final int menuSize = 27;

	public AllStatsViewerGUI(Player player) {
		int rowCount = menuSlotsPerRow;
		while (rowCount < 36 && rowCount < menuSize) {
			rowCount += menuSlotsPerRow;
		}

		ThePit.getGL().create(player, "My pit stats", rowCount, new GUIClickEventHandler() {
			@Override
			public void onOptionClick(GUIClickEvent event) {
				event.setClose(false);
				event.setDestroy(false);
			}
		});

		ThePit.getGL().setOption(player, 10, new ItemStack(Material.IRON_SWORD), ChatColor.RED + "Offensive Stats",
				ChatColor.GRAY + "Kills: " + ChatColor.GREEN + SQLManager.getStats(player, "kills"),
				ChatColor.GRAY + "Assists: " + ChatColor.GREEN + SQLManager.getStats(player, "assists"),
				ChatColor.GRAY + "Sword Hits: " + ChatColor.GREEN + SQLManager.getStats(player, "sword_hits"),
				ChatColor.GRAY + "Arrows Shot: " + ChatColor.GREEN + SQLManager.getStats(player, "arrow_shot"),
				ChatColor.RESET + "",
				ChatColor.GRAY + "Damage Dealt: " + ChatColor.GREEN + (Integer.parseInt(SQLManager.getStats(player, "melee_damage_dealt").toString()) + Integer.parseInt(SQLManager.getStats(player, "bow_damage_dealt").toString())),
				ChatColor.GRAY + "Melee Damage Dealt: " + ChatColor.GREEN + SQLManager.getStats(player, "melee_damage_dealt"),
				ChatColor.GRAY + "Bow Damage Dealt: " + ChatColor.GREEN + SQLManager.getStats(player, "bow_damage_dealt"),
				ChatColor.RESET + "",
				ChatColor.GRAY + "Highest Streak: " + ChatColor.GREEN + SQLManager.getStats(player, "highest_streak"));
		
		ThePit.getGL().setOption(player, 12, new ItemStack(Material.IRON_CHESTPLATE), ChatColor.BLUE + "Defensive Stats",
				ChatColor.GRAY + "Deaths: " + ChatColor.GREEN + SQLManager.getStats(player, "deaths"),
				ChatColor.RESET + "",
				ChatColor.GRAY + "Damage Taken: " + ChatColor.GREEN + (Integer.parseInt(SQLManager.getStats(player, "melee_damage_taken").toString()) + Integer.parseInt(SQLManager.getStats(player, "bow_damage_taken").toString())),
				ChatColor.GRAY + "Melee Damage Taken: " + ChatColor.GREEN + SQLManager.getStats(player, "melee_damage_taken"),
				ChatColor.GRAY + "Bow Damage Taken: " + ChatColor.GREEN + SQLManager.getStats(player, "bow_damage_taken"));
		
		ThePit.getGL().setOption(player, 14, new ItemStack(Material.WHEAT), ChatColor.RED + "Performance Stats",
				ChatColor.GRAY + "XP: " + ChatColor.AQUA + SQLManager.getStats(player, "total_xp"),
				ChatColor.RESET + "",
				ChatColor.GRAY + "K/D: " + ChatColor.GREEN + (Double.parseDouble(SQLManager.getStats(player, "kills").toString()) / Double.parseDouble(SQLManager.getStats(player, "deaths").toString())),
				ChatColor.GRAY + "K+A/D: " + ChatColor.GREEN + ((Double.parseDouble(SQLManager.getStats(player, "kills").toString()) + Double.parseDouble(SQLManager.getStats(player, "assists").toString())) / Double.parseDouble(SQLManager.getStats(player, "deaths").toString())),
				ChatColor.RESET + "",
				ChatColor.GRAY + "Damage dealt/taken: " + ChatColor.GREEN + ((Double.parseDouble(SQLManager.getStats(player, "melee_damage_dealt").toString()) + Double.parseDouble(SQLManager.getStats(player, "bow_damage_dealt").toString()))/(Double.parseDouble(SQLManager.getStats(player, "melee_damage_taken").toString()) + Double.parseDouble(SQLManager.getStats(player, "bow_damage_taken").toString()))),
				ChatColor.GRAY + "Arrows hit/show: " + ChatColor.GREEN,
				ChatColor.RESET + "",
				ChatColor.GRAY + "Hours played: " + ChatColor.GREEN + Integer.parseInt(SQLManager.getStats(player, "hours_played").toString()),
				ChatColor.GRAY + "Gold/hour: " + ChatColor.GREEN + ((Double.parseDouble(SQLManager.getStats(player, "total_gold").toString())/100)/Integer.parseInt(SQLManager.getStats(player, "hours_played").toString())),
				ChatColor.GRAY + "K+A/hour: " + ChatColor.GREEN + ((Double.parseDouble(SQLManager.getStats(player, "kills").toString())+Double.parseDouble(SQLManager.getStats(player, "assists").toString()))/Integer.parseInt(SQLManager.getStats(player, "hours_played").toString())));
		
		ThePit.getGL().setOption(player, 16, new ItemStack(Material.OBSIDIAN), ChatColor.LIGHT_PURPLE + "Miscellaneous Stats",
				ChatColor.GRAY + "Left Clicks: " + ChatColor.GREEN + SQLManager.getStats(player, "left_clicks").toString(),
				ChatColor.GRAY + "Gold Earned: " + ChatColor.GREEN + SQLManager.getStats(player, "gold_earned"),
				ChatColor.GRAY + "Diamond Items Purchased: " + ChatColor.GREEN + SQLManager.getStats(player, "diamond_purchased"),
				ChatColor.GRAY + "Chat Messages: " + ChatColor.GREEN + SQLManager.getStats(player, "chat"),
				ChatColor.RESET + "",
				ChatColor.GRAY + "Blocks Placed: " + ChatColor.GREEN + SQLManager.getStats(player, "block_place"),
				ChatColor.GRAY + "Blocks Broken: " + ChatColor.GREEN + SQLManager.getStats(player, "block_break"),
				ChatColor.RESET + "",
				ChatColor.GRAY + "Jumps into Pit: " + ChatColor.GREEN + SQLManager.getStats(player, "jump_pit"),
				ChatColor.GRAY + "Launcher Launches: " + ChatColor.GREEN + SQLManager.getStats(player, "launcher"),
				ChatColor.RESET + "",
				ChatColor.GRAY + "Golden Apples Eaten: " + ChatColor.GREEN + SQLManager.getStats(player, "gapple_eat"),
				ChatColor.GRAY + "Golden Heads Eaten: " + ChatColor.GREEN + SQLManager.getStats(player, "ghead_eat"),
				ChatColor.RESET + "",
				ChatColor.GRAY + "Lava Buckets Emptied: " + ChatColor.GREEN + SQLManager.getStats(player, "lava_bucket_use"),
				ChatColor.GRAY + "Fishing Rods Launched: " + ChatColor.GREEN + SQLManager.getStats(player, "fishing_rod"),
				ChatColor.RESET + "",
				ChatColor.GRAY + "Contracts Completed: " + ChatColor.GREEN + SQLManager.getStats(player, "contract_count"));
		
		ThePit.getGL().show(player);
	}

}
