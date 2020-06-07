package br.com.landwars.devtest.plugner.landtoggle;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerTeleportListener implements Listener{
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onMessage(PlayerCommandPreprocessEvent e) {
		if(e.getMessage().startsWith("/tpa ") || e.getMessage().startsWith("/system:tpa ")) {
			String[] args = e.getMessage().split(" ");
			String player = args[1];
			if(args[1] == null || args[1].equalsIgnoreCase("")) {
//				return;
			}
			if(Bukkit.getPlayer(args[1]) != null) {
				Player alvo = Bukkit.getPlayer(args[1]);
				
				if(Main.tpa.contains(alvo)) {
					e.getPlayer().sendMessage("§cEste jogador está com o recebimento de teleportes desativado.");
					e.setCancelled(true);
				}else {
					return;
				}
			}
			
			
		}
	}

}
