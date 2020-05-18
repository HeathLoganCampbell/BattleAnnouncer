package com.battlechunk.announcer.commons;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

public class EasyConfig {
	private @Getter FileConfiguration modifier;
	private final @Getter File configFile;

	public EasyConfig(String name, JavaPlugin plugin) 
	{
		this.configFile = new File(plugin.getDataFolder().toString() + File.separatorChar + name + ".yml");
		boolean isNew = false;
		
		if (!this.configFile.exists()) 
		{
			isNew = true;
			try 
			{
				this.configFile.getParentFile().mkdirs();
				this.configFile.createNewFile();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		this.modifier = YamlConfiguration.loadConfiguration(this.configFile);
		if(isNew)
		{
			onCreate();
			this.save();
		}
	}

	protected void onCreate()
	{

	}


	public void save() 
	{
		try 
		{
			this.modifier.save(this.configFile);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void reload()
	{
		this.modifier = YamlConfiguration.loadConfiguration(this.configFile);
	}
}