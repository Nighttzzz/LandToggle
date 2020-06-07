package br.com.landwars.devtest.plugner.landtoggle;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.PrivateMessageEvent;

public class PlayerTellListener implements Listener{
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPVMessage(PrivateMessageEvent e) {
		if(e.getReceiver() instanceof Player) {
			Player p = (Player)e.getReceiver();
			
			if(Main.tell.contains(p)) {
				e.getSender().sendMessage("§cEste jogador está com o recebimento de mensagens privadas desativado.");
				e.setCancelled(true);
				
				
				
			}
		}
	}
}
