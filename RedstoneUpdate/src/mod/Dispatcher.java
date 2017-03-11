package mod;

import org.bukkit.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;

public class Dispatcher extends JavaPlugin{
	public static Redstone_lamp rl;
	
	@Override
	public void onEnable(){
		Bukkit.getLogger().info("ON");
		System.out.println("HI!");
		rl = new Redstone_lamp(this);
		Serial.Start();
	}
	
	@Override
	public void onDisable(){
		Bukkit.getLogger().info("OFF");
	}
}
