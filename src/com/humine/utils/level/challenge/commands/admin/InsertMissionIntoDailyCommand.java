package com.humine.utils.level.challenge.commands.admin;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.humine.main.ChallengeMain;
import com.humine.utils.level.challenge.DailyChallenge;
import com.humine.utils.level.challenge.DailyChallengeManager;
import com.humine.utils.level.challenge.missions.Mission;

public class InsertMissionIntoDailyCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			if (!ChallengeMain.getInstance().getMissionWaiting().isEmpty())
			{
				if (args.length >= 1)
				{
					if (DailyExist(args[0]))
					{
						DailyChallenge challenge = DailyChallengeManager.getDailyChallenge(args[0]);
						if (challenge != null)
						{
							challenge = addMission(challenge);
							try
							{
								DailyChallengeManager.saveDailyChallenge(challenge);
							}
							catch (IOException e)
							{
								e.printStackTrace();
							}
							resetMissions();
							ChallengeMain.sendMessage(sender, ChatColor.GREEN + "Insertion effectuée !");
							return true;
						}
						ChallengeMain.sendMessage(sender, "Erreur fichier ! contacter un administrateur ou admin !");
					}
					else
						ChallengeMain.sendMessage(sender, "DailyChallenge Inexistant !");
				}
				else
				{
					if (hasDailyChallengeToday())
					{
						DailyChallenge challenge = ChallengeMain.getInstance().getChallengeOfToday();
						challenge = addMission(challenge);
						resetMissions();
						ChallengeMain.getInstance().setChallengeOfToday(challenge);
						ChallengeMain.sendMessage(sender, ChatColor.GREEN + "Insertion effectuée !");
						return true;
					}
					else
						ChallengeMain.sendMessage(sender, "DailyChallenge Inexistant !");
				}
			}
			else
				ChallengeMain.sendMessage(sender, "Aucune mission à insérer !");
		}

		return false;
	}

	private boolean hasDailyChallengeToday()
	{
		if (ChallengeMain.getInstance().getChallengeOfToday() != null)
			return true;
		else
			return false;
	}

	private void resetMissions()
	{
		ChallengeMain.getInstance().getMissionWaiting().clear();
	}

	private DailyChallenge addMission(DailyChallenge dCha)
	{
		DailyChallenge challenge = dCha;

		for (Mission mission : ChallengeMain.getInstance().getMissionWaiting())
		{
			challenge.getMissions().add(mission);
		}

		return challenge;
	}

	private boolean DailyExist(String date)
	{
		if (ChallengeMain.getInstance().getChallengeOfToday() != null
				&& ChallengeMain.getInstance().getChallengeOfToday().getDate().equals(date))
			return true;
		else
		{
			File file = new File(ChallengeMain.getInstance().getDailyChallengeFolder(), date);

			if (file.exists())
				return true;
			else
				return false;
		}
	}
}
