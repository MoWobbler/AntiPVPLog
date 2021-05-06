package com.Melvin3000.AntiPVPLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerQuit implements Listener {

	
	static Player p = null;
	
	
	//Instead of checking if we should spawn a skeleton, we first need a list of all players
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		p = event.getPlayer();
		BungeeMessenger.PlayerList();
		
	    new BukkitRunnable() {
	        @Override
	        public void run() {
	             newSkeleton(p);
	        }
	    }.runTaskLater(AntiPVPLog.instance, 5L);
		
	}
	
	//After we get a list of all players, check if we can spawn a skeleton
	public static void newSkeleton(Player player) {
		
	
		if (!AntiPVPLog.activeWorlds.contains(player.getWorld().getName())) {
			return;
		}
		
		if (LogoutCheck.canLogout.contains(player.getUniqueId())) {
			LogoutCheck.canLogout.remove(player.getUniqueId());
			return;
		}

		if (LogoutCheck.isInsideSpawn(player.getLocation())) {
			return;
		}

		if (player.getGameMode() != GameMode.SURVIVAL) {
			return;
		}
		
		if (JailPlugin.isPlayerJailed(player.getUniqueId())) {
			return;
		}
		
		//Check if player is still online
		List<String> playerList = new ArrayList<>(Arrays.asList(BungeeMessenger.playerList));
		
		if (playerList.contains(player.getName())) {
			return;
		}
				
		//Spawn Skeleton
		new PVPLoggedPlayer(player);
	}
	
}
