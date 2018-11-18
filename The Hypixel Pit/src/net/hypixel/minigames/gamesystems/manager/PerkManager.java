package net.hypixel.minigames.gamesystems.manager;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.vagdedes.mysql.database.SQL;
import net.hypixel.minigames.upgrades.Perk;
import net.hypixel.minigames.upgrades.perks.*;

public class PerkManager {

	static ArrayList<Perk> perks = new ArrayList<Perk>();
	
	public static void registerPerks() {
		perks.add(new PerkNone());
		perks.add(new PerkGoldenHead());
		perks.add(new PerkVampire());
		perks.add(new PerkRambo());
		perks.add(new PerkOlympus());
		perks.add(new PerkDirty());
		perks.add(new PerkBarbarian());
	}
	
	public static Perk getPerkFromSlot(Player player, int slot) {
		return getPerkFromId(Integer.parseInt(SQL.get("slot" + Integer.toString(slot) + "_perk", "uuid", "=", player.getUniqueId().toString(), "thepit_upgrades").toString()));
	}
	
	public static Perk getPerkFromId(int i) {
		for(Perk perk : perks) {
			if(perk.getPerkId() == i) {
				return perk;
			}
		}
		return null;
	}
	
	public static int getPerkId(Perk perk) {
		return perk.getPerkId();
	}
	
	public static boolean hasPerk(Player player, Perk perk) {
		if(getPerkFromSlot(player, 1).getPerkId() == perk.getPerkId() || getPerkFromSlot(player, 2).getPerkId() == perk.getPerkId() || getPerkFromSlot(player, 3).getPerkId() == perk.getPerkId()){
			return true;
		}
		return false;
	}
}
