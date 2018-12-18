package net.hypixel.minigames.gamesystems.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.hypixel.minigames.Settings;
import net.hypixel.minigames.gamesystems.manager.BountyManager;
import net.hypixel.minigames.gamesystems.status.UserStatus;

public class FightingMode extends BukkitRunnable {

	public void run() {
		for(Player online : Bukkit.getOnlinePlayers()) {
			if(Settings.fightTimer.get(online) != 0) {
				if(!isBountied(online)) {
					Settings.userStatus.remove(online);
					Settings.userStatus.put(online, UserStatus.FIGHTING);
				} else {
					Settings.userStatus.remove(online);
					Settings.userStatus.put(online, UserStatus.BOUNTY);
				}
				int value = Settings.fightTimer.get(online);
				Settings.fightTimer.remove(online);
				Settings.fightTimer.put(online, value-1);
				return;
			}
			
			if(!isBountied(online)) {
				Settings.setPlayerStatus(online, UserStatus.IDLE);
			}
		}
	}
	
	public boolean isBountied(Player player) {
		return BountyManager.hasBounty(player);
	}
}
