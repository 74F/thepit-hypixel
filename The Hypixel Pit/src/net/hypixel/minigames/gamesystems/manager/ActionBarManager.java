package net.hypixel.minigames.gamesystems.manager;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

public class ActionBarManager {

	public static void sendActionBar(Player player, String message) {
		PacketPlayOutChat actionBarPacket = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte)2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(actionBarPacket);
	}
}
