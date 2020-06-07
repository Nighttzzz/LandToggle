package br.com.landwars.devtest.plugner.landtoggle;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import br.com.devpaulo.legendchat.channels.types.Channel;

public class PlayerChatListener implements Listener{
	
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onGlobalMessage(ChatMessageEvent e) {
		
		
		
		Channel canal = e.getChannel();
		
		
		
		
		if(canal.getName().equalsIgnoreCase("global")) {
			
			if(Main.global.contains(e.getSender())) {
				e.getSender().sendMessage("§cVocê não pode enviar mensagens no global, sendo que você está com o canal desativado.");
				e.setCancelled(true);
				return;
			}
			
			for(Player alvo : Main.global) {
				if(e.getRecipients().contains(alvo)) {
					e.getRecipients().remove(alvo);
					
				}
				
			}
			
		}
		
		if(canal.getName().equalsIgnoreCase("local")) {
			if(Main.local.contains(e.getSender())) {
				e.getSender().sendMessage("§cVocê não pode enviar mensagens no local, sendo que você está com o local desativado.");
				e.setCancelled(true);
				return;
			}
			for(Player alvo : Main.local) {
				if(e.getRecipients().contains(alvo)) {
					e.getRecipients().remove(alvo);
					
				}
				
			}
			
		}
		
		
	}

}
