package br.com.landwars.devtest.plugner.landtoggle;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerMoneyListener implements Listener{
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onMessage(PlayerCommandPreprocessEvent e) {
		if(e.getMessage().startsWith("/money pay ") || e.getMessage().startsWith("/coin pay ") || e.getMessage().startsWith("/coins pay ") || e.getMessage().startsWith("/solaryeconomy pay ") || e.getMessage().startsWith("/solary-economy:money pay ")|| e.getMessage().startsWith("/solary-economy:coins pay ")|| e.getMessage().startsWith("/solary-economy:coin pay ")|| e.getMessage().startsWith("/solary-economy:solaryeconomy pay ")) {
			String[] args = e.getMessage().split(" ");
			String player = args[2];
			if(args[2] == null || args[2].equalsIgnoreCase("")) {
				return;
			}
			if(Bukkit.getPlayer(args[2]) != null) {
				Player alvo = Bukkit.getPlayer(args[2]);
				
				if(Main.money.contains(e.getPlayer())) {
					e.getPlayer().sendMessage("§cEste jogador está com o recebimento de coins desativado.");
					e.setCancelled(true);
				}else {
					return;
				}
			}
		}else if(e.getMessage().startsWith("/pay ")) {
			String[] args = e.getMessage().split(" ");
			String player = args[1];
			if(args[1] == null || args[1].equalsIgnoreCase("")) {
				return;
			}
			if(Bukkit.getPlayer(args[1]) != null) {
				Player alvo = Bukkit.getPlayer(args[1]);
				
				if(Main.money.contains(e.getPlayer())) {
					e.getPlayer().sendMessage("§cEste jogador está com o recebimento de coins desativado.");
					e.setCancelled(true);
				}else {
					return;
				}
			}
		}

	}
}
