package net.hypixel.minigames.gamesystems.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.vagdedes.mysql.database.SQL;
import net.hypixel.minigames.Settings;
import net.hypixel.minigames.gamesystems.manager.ExperienceManager;
import net.hypixel.minigames.gamesystems.manager.PrestigeManager;
import net.hypixel.minigames.gamesystems.manager.TitleManager;

public class Command_DoPrestige implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command c, String l, String[] args) {
		if(cs instanceof Player) {
			Player player = (Player) cs;
			if(ExperienceManager.getLevel(player) == 120 && PrestigeManager.getPrestige(player) != 30/* && (Double.parseDouble(SQL.get("gold_current_prestige", "uuid", "=", player.getUniqueId().toString(), "thepit").toString())/100) >= Settings.neededGoldToPrestige[Integer.parseInt(SQL.get("prestige", "uuid", "=", player.getUniqueId().toString(), "thepit").toString())+1]*/) {
				PrestigeManager.setPrestige(player, PrestigeManager.getPrestige(player) + 1);
				String playerText = ((String) SQL.get("prefix", "id", "=", (String) SQL.get("rank", "uuid", "=", player.getUniqueId().toString(), "userdata"), "rankdata")).substring(0, 2) + player.getName();
				TitleManager.sendTitle(player, "」e」lPRESTIGE!", "」7You unlocked prestige 」e" + Settings.prestigeStringList[PrestigeManager.getPrestige(player)]);
				Bukkit.broadcastMessage("」ePRESTIGE! " + playerText + " 」7unlocked prestige 」e" + Settings.prestigeStringList[PrestigeManager.getPrestige(player)] + "」7, gg!");
			}
			return true;
		}
		return false;
	}

}
