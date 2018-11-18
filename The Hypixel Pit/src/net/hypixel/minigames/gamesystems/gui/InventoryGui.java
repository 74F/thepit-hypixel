package net.hypixel.minigames.gamesystems.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryGui {

	String inventory_name;
	int size;
	Inventory inventory;
	
	public InventoryGui(String inventory_name, int size){
		this.inventory_name = inventory_name;
		this.size = size;
		inventory = Bukkit.createInventory(null, size, inventory_name);
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void openGui(Player player) {
		player.openInventory(getInventory());
	}
	
	public void registerItemStacks(Player player) {
		
	}
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	
}
