package fr.jejeoh.jump;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



public class Base extends JavaPlugin {
	
	private static Base instance;
	
	public Map<Player, Location> loc = new HashMap<>();
	
	@Override
	public void onEnable() {
		
		
		
		instance = this;
		

	    getServer().getPluginManager().registerEvents(new EventJump(this), this);
		
}
	
	@Override
	public void onDisable() {
		
	}

	public static Base getInstance() {
		return instance;
	}
	
}
