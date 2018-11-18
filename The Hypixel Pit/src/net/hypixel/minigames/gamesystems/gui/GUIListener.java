package net.hypixel.minigames.gamesystems.gui;

import java.util.HashSet;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Maps;

import net.hypixel.minigames.gamesystems.gui.BaseGUI.GUIClickEventHandler;

public class GUIListener implements Listener {
	public final Map<Player, BaseGUI> guis = Maps.newHashMap();

	public void create(Player player, String name, int size, GUIClickEventHandler handler) {
		if (player != null) {
			destroy(player);
			guis.put(player, new BaseGUI(name, size, handler));
		}
	}

	public BaseGUI getMenu(Player player) {
		return guis.get(player);
	}

	public void show(Player player) {
		if (guis.containsKey(player)) {
			guis.get(player).open(player);
		}
	}

	public void update(final Player player) {
		if (guis.containsKey(player)) {
			guis.get(player).update(player);
		}
	}

	public void setOption(Player player, int position, ItemStack icon, String name, String... info) {
		if (guis.containsKey(player)) {
			guis.get(player).setOption(position, icon, name, info);
		}
	}

	public String[] getOptions(Player player) {
		if (guis.containsKey(player)) {
			return guis.get(player).getOptions();
		}
		return null;
	}

	public void destroy(Player player) {
		if (guis.containsKey(player)) {
			guis.remove(player).destroy();
			player.getOpenInventory().close();
		}
	}

	public void destroyAll() {
		for (Player player : new HashSet<Player>(guis.keySet())) {
			destroy(player);
		}
	}

	public boolean has(Player player) {
		return guis.containsKey(player);
	}

}
