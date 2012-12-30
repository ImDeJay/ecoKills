package com.gmail.dejayyy.ecoKills;

import java.util.HashMap;
import java.util.Random;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ekMain extends JavaPlugin implements Listener {
	
	public HashMap<String, Integer> ab = new HashMap<String, Integer>();
	
	public static Economy econ = null;
	
	public void onEnable(){
		
		this.getServer().getPluginManager().registerEvents(this,  this);
		this.saveDefaultConfig();
		
	}
	
	public void onDisable(){
		
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdL, String[] args) {
		
		
		return true;
	}
	
	@EventHandler
	public void playerDeath(PlayerDeathEvent event){
		
		Player player = (Player) event.getEntity();

		Economy econ = VaultAdapter.getEconomy();
		
		if(player.getKiller() instanceof Player){
			
			
			Player killer = (Player) player.getKiller();
			
			int start = this.getConfig().getInt("start");
			int end = this.getConfig().getInt("end");
			
			String message = replaceColors(this.getConfig().getString("message").replaceAll("<player>", player.getName()));
			
			 int amount = 0;
			
			Random blah = new Random();
			
			for(int counter = start; counter<=end; counter++){
				
				amount = 1+blah.nextInt(end);
				
			} //end random number
			
			econ.depositPlayer(killer.getName(), amount);
			
			killer.sendMessage(ChatColor.DARK_AQUA + "[ecoKills] " + message.replaceAll("<reward>", Integer.toString(amount)));
			
		} //killer instanceof player
		
	}
	
	
	static String replaceColors(String message) {
		
		return ChatColor.translateAlternateColorCodes('&', message);
		
	  }
	
	
	
	
}
