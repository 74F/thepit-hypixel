package net.hypixel.minigames.gamesystems.event.minorevent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.hypixel.minigames.gamesystems.event.MinorEvent;
import net.hypixel.minigames.gamesystems.manager.BountyManager;

public class EVERYONE_GETS_BOUNTY extends Minor {

	public EVERYONE_GETS_BOUNTY() {
		super(MinorEvent.EVERYONE_GETS_BOUNTY);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onExecuted() {
		for(Player online : Bukkit.getOnlinePlayers()) {
			BountyManager.addBounty(online, 100);
		}
	}

	
}
