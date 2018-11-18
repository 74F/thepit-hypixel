package net.hypixel.minigames.upgrades;

import java.util.Arrays;
import java.util.List;

public class Upgrade {

	String name;
	List<String> description;
	int maxTier;

	public Upgrade(String name, int maxTier, String... description) {
		this.name = name;
		this.description = Arrays.asList(description);
		this.maxTier = maxTier;
	}
}
