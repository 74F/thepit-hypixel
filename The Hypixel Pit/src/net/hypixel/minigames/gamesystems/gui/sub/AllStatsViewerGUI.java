package net.hypixel.minigames.gamesystems.gui.sub;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.hypixel.minigames.ThePit;
import net.hypixel.minigames.gamesystems.gui.BaseGUI.GUIClickEvent;
import net.hypixel.minigames.gamesystems.gui.BaseGUI.GUIClickEventHandler;

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
				ChatColor.GRAY + "Kills: " + ChatColor.GREEN,
				ChatColor.GRAY + "Assists: " + ChatColor.GREEN,
				ChatColor.GRAY + "Sword Hits: " + ChatColor.GREEN,
				ChatColor.GRAY + "Arrows Shot: " + ChatColor.GREEN,
				ChatColor.RESET + "",
				ChatColor.GRAY + "Damage Dealt: " + ChatColor.GREEN,
				ChatColor.GRAY + "Melee Damage Dealt: " + ChatColor.GREEN,
				ChatColor.GRAY + "Bow Damage Dealt: " + ChatColor.GREEN,
				ChatColor.RESET + "",
				ChatColor.GRAY + "Highest Streak: " + ChatColor.GREEN);
		
		ThePit.getGL().setOption(player, 12, new ItemStack(Material.IRON_CHESTPLATE), ChatColor.BLUE + "Defensive Stats",
				ChatColor.GRAY + "Deaths: " + ChatColor.GREEN,
				ChatColor.RESET + "",
				ChatColor.GRAY + "Damage Taken: " + ChatColor.GREEN,
				ChatColor.GRAY + "Melee Damage Taken: " + ChatColor.GREEN,
				ChatColor.GRAY + "Bow Damage Taken: " + ChatColor.GREEN);
		
		ThePit.getGL().setOption(player, 14, new ItemStack(Material.WHEAT), ChatColor.RED + "Performance Stats",
				ChatColor.GRAY + "XP: " + ChatColor.AQUA,
				ChatColor.RESET + "",
				ChatColor.GRAY + "K/D: " + ChatColor.GREEN,
				ChatColor.GRAY + "K+A/D: " + ChatColor.GREEN,
				ChatColor.RESET + "",
				ChatColor.GRAY + "Damage dealt/taken: " + ChatColor.GREEN,
				ChatColor.GRAY + "Arrows hit/show: " + ChatColor.GREEN);
		
		ThePit.getGL().setOption(player, 16, new ItemStack(Material.OBSIDIAN), ChatColor.LIGHT_PURPLE + "Miscellaneous Stats",
				ChatColor.GRAY + "Left Clicks: " + ChatColor.GREEN,
				ChatColor.GRAY + "Gold Earned: " + ChatColor.GREEN,
				ChatColor.GRAY + "Diamond Items Purchased: " + ChatColor.GREEN,
				ChatColor.GRAY + "Chat Messages: " + ChatColor.GREEN,
				ChatColor.RESET + "",
				ChatColor.GRAY + "Blocks Placed: " + ChatColor.GREEN,
				ChatColor.GRAY + "Blocks Broken: " + ChatColor.GREEN,
				ChatColor.RESET + "",
				ChatColor.GRAY + "Jumps into Pit: " + ChatColor.GREEN,
				ChatColor.GRAY + "Launcher Launches: " + ChatColor.GREEN,
				ChatColor.RESET + "",
				ChatColor.GRAY + "Golden Apples Eaten: " + ChatColor.GREEN,
				ChatColor.GRAY + "Golden Heads Eaten: " + ChatColor.GREEN,
				ChatColor.RESET + "",
				ChatColor.GRAY + "Lava Buckets Emptied: " + ChatColor.GREEN,
				ChatColor.GRAY + "Fishing Rods Launched: " + ChatColor.GREEN,
				ChatColor.RESET + "",
				ChatColor.GRAY + "Contracts Completed: " + ChatColor.GREEN);
		
		ThePit.getGL().show(player);
	}

}
