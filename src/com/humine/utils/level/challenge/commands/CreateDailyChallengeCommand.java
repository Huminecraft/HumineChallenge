package com.humine.utils.level.challenge.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.humine.main.ChallengeMain;
import com.humine.utils.ChallengeFile;
import com.humine.utils.Date;
import com.humine.utils.level.challenge.DailyChallenge;

public class CreateDailyChallengeCommand implements CommandExecutor
{

	/*
	 * 	/dailychallenge <create|clone> [parametre...]
	 * 	/dailychallenge create <name> [date]
	 * 	/dailychallenge clone <name> [date]
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(args.length > 2) {
			
			if(args[0].equalsIgnoreCase("create")) {
				DailyChallenge challenge = new DailyChallenge();
				
				if(args.length >= 4)
					challenge.setDate(args[2]);
				
				if(challenge.getDate().equals(Date.getDateTodayInString()))
					ChallengeMain.getInstance().setChallengeOfToday(challenge);
				else
				{
					ChallengeFile file = new ChallengeFile(new File(ChallengeMain.getInstance().getDailyChallengeFolder(), challenge.getDate()));
					if(!file.getFile().exists()) {
						try
						{
							file.getFile().createNewFile();
						}
						catch (IOException e)
						{
							ChallengeMain.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "[DailyChallenge] ERREUR FILE CREATE");
							ChallengeMain.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + e.getMessage());
						}
					}
					
					if(file.getFile().exists())
						file.saveDailyChallenge(challenge);
				}
				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("clone")) {
				ChallengeFile file = new ChallengeFile(new File(ChallengeMain.getInstance().getDailyChallengeFolder(), args[1]));
				
				
				if(file.getFile().exists()) {
					DailyChallenge challenge = file.getDailyChallenge();
					
					if(args.length >= 4)
						challenge.setDate(args[2]);
					
					if(challenge.getDate().equals(Date.getDateTodayInString()))
						ChallengeMain.getInstance().setChallengeOfToday(challenge);
					else
					{
						file = new ChallengeFile(new File(ChallengeMain.getInstance().getDailyChallengeFolder(), challenge.getDate()));
						if(!file.getFile().exists()) {
							try
							{
								file.getFile().createNewFile();
							}
							catch (IOException e)
							{
								ChallengeMain.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "[DailyChallenge] ERREUR FILE CREATE");
								ChallengeMain.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + e.getMessage());
							}
						}
						
						if(file.getFile().exists())
							file.saveDailyChallenge(challenge);
						
					}
					
					return true;
				}
			}
		}
		
		return false;
	}

}
