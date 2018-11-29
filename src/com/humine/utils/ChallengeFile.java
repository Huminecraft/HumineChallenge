package com.humine.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.humine.main.ChallengeMain;
import com.humine.utils.level.challenge.DailyChallenge;

public class ChallengeFile
{

	private File				file;
	private FileConfiguration	config;

	public ChallengeFile(File file)
	{
		this.file = file;
		config = YamlConfiguration.loadConfiguration(this.file);
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public FileConfiguration getConfig()
	{
		return config;
	}

	public void setConfig(FileConfiguration config)
	{
		this.config = config;
	}

	public void saveDailyChallenge(DailyChallenge challenge)
	{
		this.config.createSection("DailyChallenge");
		this.config.set("DailyChallenge", challenge);
		try
		{
			this.config.save(this.file);
		}
		catch (IOException e)
		{
			ChallengeMain.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "[DailyChallenge] ERREUR FILE SAVE");
			ChallengeMain.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + e.getMessage());
		}
	}

	public DailyChallenge getDailyChallenge()
	{
		DailyChallenge challenge = null;

		if (this.config.contains("DailyChallenge"))
		{
			challenge = (DailyChallenge) this.config.get("DailyChallenge");
		}

		return challenge;
	}
}
