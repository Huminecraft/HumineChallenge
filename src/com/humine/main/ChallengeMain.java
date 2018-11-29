package com.humine.main;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.humine.utils.level.challenge.DailyChallenge;

public class ChallengeMain extends JavaPlugin {
	
	private static ChallengeMain instance;
	
	private DailyChallenge challengeOfToday;
	
	private File dailyChallengeFolder;

	@Override
	public void onEnable() {
		instance = this;
		this.dailyChallengeFolder = new File(this.getDataFolder(), "DailyChallenge");
		
		
		if(!dailyChallengeFolder.exists())
			dailyChallengeFolder.mkdirs();
	}

	public void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.AQUA + "[HumineChallenge] " + ChatColor.RESET + message);
	}

	public static ChallengeMain getInstance() {
		return instance;
	}

	public DailyChallenge getChallengeOfToday()
	{
		return challengeOfToday;
	}

	public void setChallengeOfToday(DailyChallenge challengeOfToday)
	{
		this.challengeOfToday = challengeOfToday;
	}

	public File getDailyChallengeFolder()
	{
		return dailyChallengeFolder;
	}
}
