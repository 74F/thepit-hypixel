package net.hypixel.minigames.gamesystems.manager;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class TitleManager {

	public static void sendTitle(Player target, String title, String subtitle) {
		PacketPlayOutTitle title1 = new PacketPlayOutTitle(EnumTitleAction.TITLE, new ChatComponentText(title));
		PacketPlayOutTitle subtitle1 = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, new ChatComponentText(subtitle));
		
		((CraftPlayer) target).getHandle().playerConnection.sendPacket(title1);
		((CraftPlayer) target).getHandle().playerConnection.sendPacket(subtitle1);
	}
	
}
