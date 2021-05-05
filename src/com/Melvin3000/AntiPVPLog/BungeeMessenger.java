package com.Melvin3000.AntiPVPLog;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeMessenger implements PluginMessageListener{

	public static String[] playerList = null;
	
	//Receive player list
	 @Override
	    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
	        if (channel.equals("BungeeCord")) {
	        	try {
		            ByteArrayDataInput in = ByteStreams.newDataInput(message);
		            String subchannel = in.readUTF();
		            if (subchannel.equals("PlayerList")) {
		            	//Below part might need to be here
		                String server = in.readUTF();
		                playerList = in.readUTF().split(", ");
		                
		                
		                PlayerQuit.newSkeleton();
		            }
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}
	        }
	    }
	 

	 	//Ask bungeecord for a list of all players on the network
		public static void PlayerList() {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			  out.writeUTF("PlayerList");
			  out.writeUTF("ALL");
			  Bukkit.getServer().sendPluginMessage(AntiPVPLog.instance, "BungeeCord", out.toByteArray());
		}
		
		public static void ForwardName() {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			  out.writeUTF("PlayerList");
			  out.writeUTF("ALL");
			  Bukkit.getServer().sendPluginMessage(AntiPVPLog.instance, "BungeeCord", out.toByteArray());
		}
		
	
		
}
