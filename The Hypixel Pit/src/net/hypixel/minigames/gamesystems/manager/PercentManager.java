package net.hypixel.minigames.gamesystems.manager;

import io.netty.util.internal.ThreadLocalRandom;

public class PercentManager {

	public static boolean percent(double percent) {
		return percent > ThreadLocalRandom.current().nextDouble(0, 10);
	}
}
