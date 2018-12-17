package com.humine.utils.level.challenge.commands.admin;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.humine.main.ChallengeMain;
import com.humine.utils.level.challenge.DailyChallenge;
import com.humine.utils.level.challenge.DailyChallengeManager;

public class ShowDailyChallengeCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length >= 1) {
				DailyChallenge challenge = DailyChallengeManager.getDailyChallenge(args[0]);
				if(challenge != null)
					challenge.showMissions(player);
				
				return true;
			}
			else {
				if(ChallengeMain.getInstance().getChallengeOfToday() != null)
					ChallengeMain.getInstance().getChallengeOfToday().showMissions(player);
				
				return true;
			}
		}
		return false;
	}

}
