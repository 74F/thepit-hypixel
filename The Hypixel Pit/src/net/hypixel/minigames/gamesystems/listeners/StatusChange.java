package net.hypixel.minigames.gamesystems.listeners;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.hypixel.minigames.Settings;
import net.hypixel.minigames.gamesystems.status.UserStatus;

public class StatusChange implements Listener {

	@EventHandler
	public void userStatusChange(EntityDamageByEntityEvent e) {
		Player damager = null;
		if(e.getDamager() instanceof Player) {
			damager = (Player) e.getDamager();
		}
		if(e.getDamager() instanceof Projectile) {
			damager = (Player) ((Projectile) e.getDamager()).getShooter();
		}
		if(e.getDamager() instanceof TNTPrimed) {
			damager = (Player) ((TNTPrimed) e.getDamager()).getSource();
		}
		
		if(e.getEntity() instanceof Player) {
			if(damager != null) {
				Player damaged = (Player) e.getEntity();
				Settings.userStatus.remove(damager);
				Settings.userStatus.remove(damaged);
				Settings.userStatus.put(damager, UserStatus.FIGHTING);
				Settings.userStatus.put(damager, UserStatus.FIGHTING);
			}
		}
	}
}
