package net.hypixel.minigames.upgrades;

import java.util.List;

public class Perk {

	int perkId;
	String perkName;
	List<String> perkDesc;
	boolean renownShop;
	
	public Perk(int perkId, String perkName, List<String> perkDesc, boolean renownShop) {
		this.perkId = perkId;
		this.perkName = perkName;
		this.perkDesc = perkDesc;
		this.renownShop = renownShop;
	}

	public String getPerkName() {
		return perkName;
	}
	
	public List<String> getPerkDescriptions() {
		return perkDesc;
	}
	
	public boolean isOnRenownShop() {
		return renownShop;
	}
	
	public int getPerkId() {
		return perkId;
	}
}
