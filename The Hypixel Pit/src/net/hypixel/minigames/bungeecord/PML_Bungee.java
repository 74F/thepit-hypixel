package net.hypixel.minigames.bungeecord;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.hypixel.minigames.ThePit;

public class PML_Bungee implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if(!channel.equals("BungeeCord")) return;
		ByteArrayDataInput input = ByteStreams.newDataInput(message);
		String subchannel = input.readUTF();
		if(subchannel.equals("GetServer")) {
			String servername = input.readUTF();
			serverName = servername;
		}
	}
	
	public static String serverName;
	
	public static void getServer(Player player) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServer");

        player.sendPluginMessage(ThePit.getInstance(), "BungeeCord", out.toByteArray());
	}

}
