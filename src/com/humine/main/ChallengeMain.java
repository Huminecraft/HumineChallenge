package com.humine.main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ChallengeMain extends JavaPlugin {
	
	private static ChallengeMain instance;

	@Override
	public void onEnable() {
		instance = this;
	}

	public void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.AQUA + "[HumineChallenge] " + ChatColor.RESET + message);
	}

	public static ChallengeMain getInstance() {
		return instance;
	}
}
