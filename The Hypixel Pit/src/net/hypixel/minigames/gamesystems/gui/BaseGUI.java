package net.hypixel.minigames.gamesystems.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import net.hypixel.minigames.ThePit;

public class BaseGUI {
	
	private String name;
	private int size;
	private GUIClickEventHandler handler;
	private String[] optionNames;
	private ItemStack[] optionIcons;

	public BaseGUI(String name, int size, GUIClickEventHandler handler) {
        this.name = name;
        this.size = size;
        this.handler = handler;
        this.optionNames = new String[size];
        this.optionIcons = new ItemStack[size];
    }

	public BaseGUI setOption(int position, ItemStack icon, String name, String[] info) {
		this.optionNames[position] = name;
		this.optionIcons[position] = ItemUtils.name(icon, name, info);
		return this;
	}

	public void open(Player player) {
		Inventory inventory = Bukkit.createInventory(player, this.size, this.name);
		for (int iii = 0; iii < this.optionIcons.length; iii++) {
			if (this.optionIcons[iii] != null) {
				inventory.setItem(iii, this.optionIcons[iii]);
			}
		}
		player.openInventory(inventory);
	}

	public void update(Player player) {
		InventoryView inventory = player.getOpenInventory();
		if (inventory != null) {
			for (int iii = 0; iii < this.optionIcons.length; iii++) {
				if (this.optionIcons[iii] != null) {
					inventory.setItem(iii, this.optionIcons[iii]);
				}
			}
			player.updateInventory();
		}
	}

	public void destroy() {
		this.handler = null;
		this.optionNames = null;
		this.optionIcons = null;
	}

	public void onInventoryClick(InventoryClickEvent event) {
		if (!event.getInventory().getTitle().equals(name)) {
			return;
		}

		event.setCancelled(true);

		int slot = event.getRawSlot();

		try {
			if (!(slot >= 0 && slot < size && optionNames[slot] != null)) {
				return;
			}
		} catch (NullPointerException e) {
			return;
		}

		GUIClickEvent clickEvent = new GUIClickEvent((Player) event.getWhoClicked(), slot, optionNames[slot]);
		handler.onOptionClick(clickEvent);

		if (clickEvent.willClose()) {
			final Player player = (Player) event.getWhoClicked();

			Bukkit.getScheduler().runTaskLater(ThePit.getInstance(), new Runnable() {
				@Override
				public void run() {
					player.closeInventory();
				}
			}, 1L);
		}

		if (clickEvent.willDestroy()) {
			destroy();
		}
	}

	public String getName() {
		return this.name;
	}

	public String[] getOptions() {
		return optionNames;
	}

	public static class GUIClickEvent {
		private Player player;
		private int position;
		private String name;
		private boolean close;
		private boolean destroy;

		public GUIClickEvent(Player player, int position, String name) {
			this.player = player;
			this.position = position;
			this.name = name;
			this.close = false;
			this.destroy = false;
		}

		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean willClose() {
			return close;
		}

		public void setClose(boolean close) {
			this.close = close;
		}

		public boolean willDestroy() {
			return destroy;
		}

		public void setDestroy(boolean destroy) {
			this.destroy = destroy;
		}

	}

	public static abstract interface GUIClickEventHandler {
		public abstract void onOptionClick(GUIClickEvent event);
	}

}
