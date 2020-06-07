package br.com.landwars.devtest.plugner.landtoggle;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.devpaulo.legendchat.api.Legendchat;
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import br.com.devpaulo.legendchat.api.events.PrivateMessageEvent;
import br.com.devpaulo.legendchat.channels.ChannelManager;
import br.com.devpaulo.legendchat.channels.types.Channel;

public class Main extends JavaPlugin implements Listener{

	public static ArrayList<Player> global = new ArrayList<Player>();
	public static ArrayList<Player> local = new ArrayList<Player>();
	public static ArrayList<Player> money = new ArrayList<Player>();
	public static ArrayList<Player> tpa = new ArrayList<Player>();
	public static ArrayList<Player> tell = new ArrayList<Player>();
	
	@Override
	public void onEnable() {
	    Bukkit.getConsoleSender().sendMessage("§fHabilitado com succeso!");
	    saveDefaultConfig();
	    
	    getCommand("toggle").setExecutor(this);
	    Bukkit.getPluginManager().registerEvents(this, this);
	    
	    Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
	    Bukkit.getPluginManager().registerEvents(new PlayerMoneyListener(), this);
	    Bukkit.getPluginManager().registerEvents(new PlayerTeleportListener(), this);
	    Bukkit.getPluginManager().registerEvents(new PlayerTellListener(), this);
	    
	}
	
	@Override
	public void onDisable() {
	    Bukkit.getConsoleSender().sendMessage("§fDesabilitado com succeso!");
	}
	
	
	public void guiMenu() {
		Inventory inv = Bukkit.createInventory(null, 4*9, "§fConfigurações");
		
		
	}
	
	//																																																							solary-economy:

		
	
	
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onUserJoin(PlayerJoinEvent e) {
		if(getConfig().contains(e.getPlayer().getName())) {
			
		}else {
			getConfig().set(e.getPlayer().getName() + ".ReceberMoney", true);
			getConfig().set(e.getPlayer().getName() + ".ReceberTell", true);
			getConfig().set(e.getPlayer().getName() + ".ReceberTpa", true);
			getConfig().set(e.getPlayer().getName() + ".ReceberGlobal", true);			
			getConfig().set(e.getPlayer().getName() + ".ReceberLocal", true);	
			Bukkit.getConsoleSender().sendMessage("§6§lLand§2§lToggle §fConfiguração criada para §a" + e.getPlayer().getName());
			saveConfig();
			
		}
		
		if(getConfig().getBoolean(e.getPlayer().getName() + ".ReceberGlobal") == false) {
			global.add(e.getPlayer());
		}
		
		if(getConfig().getBoolean(e.getPlayer().getName() + ".ReceberLocal") == false) {
			local.add(e.getPlayer());
		}
		
		if(getConfig().getBoolean(e.getPlayer().getName() + ".ReceberTell") == false) {
			tell.add(e.getPlayer());
		}
		if(getConfig().getBoolean(e.getPlayer().getName() + ".ReceberMoney") == false) {
			money.add(e.getPlayer());
		}
		if(getConfig().getBoolean(e.getPlayer().getName() + ".ReceberTpa") == false) {
			tpa.add(e.getPlayer());
		}
		
		
		
		
	}
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onUserQuit(PlayerQuitEvent e) {
		if(global.contains(e.getPlayer())) global.remove(e.getPlayer());
		if(local.contains(e.getPlayer())) local.remove(e.getPlayer());
		if(tell.contains(e.getPlayer())) tell.remove(e.getPlayer());
		if(money.contains(e.getPlayer())) money.remove(e.getPlayer());
		if(tpa.contains(e.getPlayer())) tpa.remove(e.getPlayer());
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("toggle")) {
			Player p = (Player)sender;
			
			Inventory inv = Bukkit.createInventory(null, 4*9, "§8Configurações");
			
			ItemStack item_money = new ItemStack(Material.GOLD_NUGGET, 1);
			ItemMeta moneymeta = item_money.getItemMeta();
			
			moneymeta.setDisplayName("§7Recebimento de coins");
			item_money.setItemMeta(moneymeta);

			ItemStack item_global = new ItemStack(Material.BOOK, 1);
			ItemMeta globalmeta = item_global.getItemMeta();
			
			globalmeta.setDisplayName("§7Recebimento de Mensagens no Chat Global");
			item_global.setItemMeta(globalmeta);
			
			ItemStack item_local = new ItemStack(Material.FEATHER, 1);
			ItemMeta localmeta = item_global.getItemMeta();
			
			localmeta.setDisplayName("§7Recebimento de Mensagens no Chat Local");
			item_local.setItemMeta(localmeta);			
			
			ItemStack item_tell = new ItemStack(Material.PAPER, 1);
			ItemMeta tellmeta = item_money.getItemMeta();
			
			tellmeta.setDisplayName("§7Recebimento de Mensagens /tell");
			item_tell.setItemMeta(tellmeta);
			
			ItemStack item_tpa = new ItemStack(Material.ENDER_PEARL, 1);
			ItemMeta tpameta = item_money.getItemMeta();
			
			tpameta.setDisplayName("§7Recebimento de Teleportes /tpa");
			item_tpa.setItemMeta(tpameta);
			
			
			inv.setItem(11, item_money);
			inv.setItem(12, item_global);
			inv.setItem(13, item_local);
			inv.setItem(14, item_tell);
			inv.setItem(15, item_tpa);
			
			
			ItemStack gp_money = new ItemStack(Material.STAINED_GLASS_PANE, 1);
			if(getConfig().getBoolean(p.getName() + ".ReceberMoney") == false) {
				gp_money.setDurability((short) 14);
				ItemMeta gpmoneymeta = gp_money.getItemMeta();
				gpmoneymeta.setDisplayName("§cCoins: Desabilitado");
				
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add("§aClique para habilitar!");
				lore.add("");
				
				gpmoneymeta.setLore(lore);
				gp_money.setItemMeta(gpmoneymeta);
			}else {
				gp_money.setDurability((short) 5);
				ItemMeta gpmoneymeta = gp_money.getItemMeta();
				gpmoneymeta.setDisplayName("§aCoins: Habilitado");
				
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add("§cClique para desabilitar!");
				lore.add("");
				
				gpmoneymeta.setLore(lore);
				gp_money.setItemMeta(gpmoneymeta);
			}
			ItemStack gp_global = new ItemStack(Material.STAINED_GLASS_PANE, 1);
			if(getConfig().getBoolean(p.getName() + ".ReceberGlobal") == false) {
				gp_global.setDurability((short) 14);
				ItemMeta gpglobalmeta = gp_global.getItemMeta();
				gpglobalmeta.setDisplayName("§cChat Global: Desabilitado");
				
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add("§aClique para habilitar!");
				lore.add("");
				
				gpglobalmeta.setLore(lore);
				gp_global.setItemMeta(gpglobalmeta);
			}else {
				gp_global.setDurability((short) 5);
				ItemMeta gpglobalmeta = gp_global.getItemMeta();
				gpglobalmeta.setDisplayName("§aChat Global: Habilitado");
				
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add("§cClique para desabilitar!");
				lore.add("");
				
				gpglobalmeta.setLore(lore);
				gp_global.setItemMeta(gpglobalmeta);
			}		
		
			ItemStack gp_local = new ItemStack(Material.STAINED_GLASS_PANE, 1);
			if(getConfig().getBoolean(p.getName() + ".ReceberLocal") == false) {
				gp_local.setDurability((short) 14);
				ItemMeta gplocalmeta = gp_local.getItemMeta();
				gplocalmeta.setDisplayName("§cChat Local: Desabilitado");
				
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add("§aClique para habilitar!");
				lore.add("");
				
				gplocalmeta.setLore(lore);
				gp_local.setItemMeta(gplocalmeta);
			}else {
				gp_local.setDurability((short) 5);
				ItemMeta gplocalmeta = gp_local.getItemMeta();
				gplocalmeta.setDisplayName("§aChat Local: Habilitado");
				
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add("§cClique para desabilitar!");
				lore.add("");
				
				gplocalmeta.setLore(lore);
				gp_local.setItemMeta(gplocalmeta);
			}
			
			
			ItemStack gp_tell = new ItemStack(Material.STAINED_GLASS_PANE, 1);
			if(getConfig().getBoolean(p.getName() + ".ReceberTell") == false) {
				gp_tell.setDurability((short) 14);
				ItemMeta gptellmeta = gp_tell.getItemMeta();
				gptellmeta.setDisplayName("§cTell: Desabilitado");
				
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add("§aClique para habilitar!");
				lore.add("");
				gptellmeta.setLore(lore);
				gp_tell.setItemMeta(gptellmeta);
			}else {
				gp_tell.setDurability((short) 5);
				ItemMeta gptellmeta = gp_tell.getItemMeta();
				gptellmeta.setDisplayName("§aTell: Habilitado");
				
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add("§cClique para desabilitar!");
				lore.add("");
				
				gptellmeta.setLore(lore);
				gp_tell.setItemMeta(gptellmeta);
			}
			
			ItemStack gp_tpa = new ItemStack(Material.STAINED_GLASS_PANE, 1);
			if(getConfig().getBoolean(p.getName() + ".ReceberTpa") == false) {
				gp_tpa.setDurability((short) 14);
				ItemMeta gptpameta = gp_tpa.getItemMeta();
				gptpameta.setDisplayName("§cTpa: Desabilitado");
				
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add("§aClique para habilitar!");
				lore.add("");
				gptpameta.setLore(lore);
				gp_tpa.setItemMeta(gptpameta);
			}else {
				gp_tpa.setDurability((short) 5);
				ItemMeta gptpameta = gp_tpa.getItemMeta();
				gptpameta.setDisplayName("§aTpa: Habilitado");
				
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add("§cClique para desabilitar!");
				lore.add("");
				gptpameta.setLore(lore);
				gp_tpa.setItemMeta(gptpameta);
			}
			
			inv.setItem(20, gp_money);
			inv.setItem(21, gp_global);
			inv.setItem(22, gp_local);
			inv.setItem(23, gp_tell);
			inv.setItem(24, gp_tpa);
			
			
			
			p.openInventory(inv);
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		return false;
		
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		InventoryAction action = e.getAction();
		
		if(e.getInventory().getName().equalsIgnoreCase("§8Configurações")) {
			if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
				ItemStack item = e.getCurrentItem();
				ItemMeta itemmeta = item.getItemMeta();
				
				if(itemmeta.getDisplayName().contains("Coins:")) {
					
					if(getConfig().getBoolean(p.getName() + ".ReceberMoney") == true) {
						getConfig().set(p.getName() + ".ReceberMoney", false);
						money.add(p);
						p.sendMessage("§cVocê desabilitou o recebimento de moedas!");
					}else {
						getConfig().set(p.getName() + ".ReceberMoney", true);
						money.remove(p);
						p.sendMessage("§aVocê habilitou o recebimento de moedas!");
					}
					
					p.closeInventory();
					saveConfig();
					reloadConfig();
					
				}else if(itemmeta.getDisplayName().contains("Tell:")) {
					if(getConfig().getBoolean(p.getName() + ".ReceberTell") == true) {
						getConfig().set(p.getName() + ".ReceberTell", false);
						tell.add(p);
						p.sendMessage("§cVocê desabilitou o recebimento de mensagens privadas!");
					}else {
						getConfig().set(p.getName() + ".ReceberTell", true);
						tell.remove(p);
						p.sendMessage("§aVocê habilitou o recebimento de moedas!");
					}
		
					p.closeInventory();
					saveConfig();
					reloadConfig();
				}else if(itemmeta.getDisplayName().contains("Tpa:")) {
					if(getConfig().getBoolean(p.getName() + ".ReceberTpa") == true) {
						tpa.add(p);
						p.sendMessage("§cVocê desabilitou o recebimento de teleportes!");
						getConfig().set(p.getName() + ".ReceberTpa", false);
					}else {
						tpa.remove(p);
						getConfig().set(p.getName() + ".ReceberTpa", true);
						p.sendMessage("§aVocê habilitou o recebimento de moedas!");
					}
					p.closeInventory();
					saveConfig();
					reloadConfig();
				}else if(itemmeta.getDisplayName().contains("Chat Global:")) {
					if(getConfig().getBoolean(p.getName() + ".ReceberGlobal") == true) {
						e.getWhoClicked().closeInventory();
						p.sendMessage("§cVocê desabilitou o recebimento de mensagens globais!");
						ChannelManager channelManager = Legendchat.getChannelManager();
				       
						getConfig().set(p.getName() + ".ReceberGlobal", false);
						global.add(p);
						p.closeInventory();
						saveConfig();
						reloadConfig();
					}else {
						getConfig().set(p.getName() + ".ReceberGlobal", true);
						e.getWhoClicked().closeInventory();
						p.sendMessage("§aVocê habilitou o recebimento de mensagens globais!");
						global.remove(p);
						p.closeInventory();
						saveConfig();
						reloadConfig();				        
					}
					
					}
				else if(itemmeta.getDisplayName().contains("Chat Local:")) {
						if(getConfig().getBoolean(p.getName() + ".ReceberLocal") == true) {
							p.sendMessage("§cVocê desabilitou o recebimento de mensagens locais!");
							ChannelManager channelManager = Legendchat.getChannelManager();
					       
							getConfig().set(p.getName() + ".ReceberLocal", false);
							local.add(p);
						}else {
							getConfig().set(p.getName() + ".ReceberLocal", true);
							p.sendMessage("§aVocê habilitou o recebimento de mensagens local!");
							local.remove(p);
					        
						}
					p.closeInventory();
					saveConfig();
					reloadConfig();
				}
			}
			e.setCancelled(true);
		}
		return;
		
	}

	

	
	
	

}
