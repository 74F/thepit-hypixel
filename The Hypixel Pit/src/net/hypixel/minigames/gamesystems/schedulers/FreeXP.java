package net.hypixel.minigames.gamesystems.schedulers;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.hypixel.minigames.gamesystems.manager.ExperienceManager;

public class FreeXP extends BukkitRunnable{

	@Override
	public void run() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(player != null) { 
				Random rand = new Random();
				int i = rand.nextInt(4) + 15;
				player.sendMessage("」a」lFREE XP! 」7for participation 」b+" + i + "XP");
				ExperienceManager.addExp(player, i);
			}
		}
	}

}
