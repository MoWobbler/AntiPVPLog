package com.Melvin3000.AntiPVPLog;

import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;




public class BungeecordMessageListener implements PluginMessageListener {


	
	
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
	System.out.println("Testing PMR");
	if (!channel.equals("BungeeCord")) {
	  return;
	}
	ByteArrayDataInput in = ByteStreams.newDataInput(message);
	String subchannel = in.readUTF();
	if (subchannel.equals("PlayerList")) {	  
		  String[] playerList = in.readUTF().split(", ");
		  System.out.println(playerList);
		  if (Arrays.asList(playerList).contains(player.getName())) {
			  PVPLoggedPlayer plp = new PVPLoggedPlayer(player);
			  plp.despawn();
		  }
	


	    }
	  }
	
	
	public static void PlayerList(Player player) {
		System.out.println("Testing PL");
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		  out.writeUTF("PlayerList");
		  out.writeUTF("ALL");
		  player.sendPluginMessage(AntiPVPLog.instance, "BungeeCord", out.toByteArray());
	}
	

	

	
	
	
}
