package net.hypixel.minigames.gamesystems.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.vagdedes.mysql.database.SQL;
import net.hypixel.minigames.Settings;

@SuppressWarnings("all")
public class Command_OpenGui implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command c, String l, String[] args) {
		if(cs instanceof Player) {
			Player player = (Player) cs;
			double prestigePercent = Settings.prestigeEXPPersent[Integer.parseInt(SQL.get("prestige", "uuid", "=", player.getUniqueId().toString(), "thepit").toString())+1];
			
			//Settings.prestige.registerItemStacks(player);
			//Settings.prestige.openGui(player);
		}
		return false;
	}

}
