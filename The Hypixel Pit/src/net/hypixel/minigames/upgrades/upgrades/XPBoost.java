package net.hypixel.minigames.upgrades.upgrades;

import net.hypixel.minigames.upgrades.Upgrade;

public class XPBoost extends Upgrade {
	
	public XPBoost() {
		super("XP Boost",5,"」7Each tier:","」7Earn 」b+10% XP 」7from all","」7sources.");
	}
	
	public double tier1_xp() {
		return 0.1;
	}
	
	public double tier2_xp() {
		return tier1_xp()*2;
	}
	
	public double tier3_xp() {
		return tier1_xp()*3;
	}
	
	public double tier4_xp() {
		return tier1_xp()*4;
	}
	
	public double tier5_xp() {
		return tier1_xp()*5;
	}

}
