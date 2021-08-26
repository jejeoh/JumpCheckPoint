package fr.jejeoh.jump;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventJump implements Listener {

	private Base main;
	
	public EventJump(Base main) {
		
		this.main = main;
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		
		Player player = e.getPlayer();
		
		ItemStack bous = new ItemStack(Material.COMPASS, 1);
		ItemMeta bous1 = bous.getItemMeta();
		
		bous1.setDisplayName("§5Teleporte");
		bous.setItemMeta(bous1);
		
		player.getInventory().setItem(0, bous);
		
		
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	
	 @EventHandler
	 public void onDeg(EntityDamageEvent e) {
		 
		 e.setCancelled(true);
		 
	 }
	 
	 @EventHandler
	 public void onMove(PlayerMoveEvent e) {
		 Player player = e.getPlayer();
		 if(player.getLocation().getBlockY() <= 120) {
				if(main.loc.containsKey(player)) {
					
					Location tp = main.loc.get(player);
					player.teleport(tp);
					
				}else {
					Location loc = new Location(Bukkit.getWorld("world"), -101.5, 251, 256.5, -180f, -2f);
					player.teleport(loc);
				}
				
		 }
	 }

	@EventHandler
	public void onint(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action ac = e.getAction();
		ItemStack it = e.getItem();
		
		if(e.getClickedBlock() != null && ac == Action.RIGHT_CLICK_BLOCK) {
			BlockState bs = e.getClickedBlock().getState();
			if(bs instanceof Sign) {
				Sign sign = (Sign) bs;
				if(sign.getLine(0).equalsIgnoreCase("[CheckPoint]")) {
					main.loc.put(player, player.getLocation());
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10f, 1f);
				}

			}
		}
		
		if(it != null) {
			
			if(it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§5Teleporte") && ((ac == Action.RIGHT_CLICK_AIR ) || (ac == Action.RIGHT_CLICK_BLOCK))){
				e.setCancelled(true);
				if(main.loc.containsKey(player)) {
					
					Location tp = main.loc.get(player);
					player.teleport(tp);
					
				}else {
					player.sendMessage("§cVous avez encore zero checkpoint !");
					player.playSound(player.getLocation(), Sound.ANVIL_LAND, 10f, 1f);
				}
				
			}
			
		}
			
	}
	
	@EventHandler
	public void onCli(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			
			ItemStack it = e.getCurrentItem();
			Player player = (Player) e.getWhoClicked();
			
			if(it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§5Teleporte")){
				e.setCancelled(true);
				if(main.loc.containsKey(player)) {
					
					Location tp = main.loc.get(player);
					player.teleport(tp);
					
				}else {
					player.sendMessage("§cVous avez encore zero checkpoint !");
					player.playSound(player.getLocation(), Sound.ANVIL_LAND, 10f, 1f);

				}
			}
			
		}
		
		
	}
	
}
