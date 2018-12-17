package com.humine.utils.level.challenge.commands.admin;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.humine.main.ChallengeMain;
import com.humine.utils.Date;
import com.humine.utils.level.challenge.DailyChallenge;
import com.humine.utils.level.challenge.DailyChallengeManager;

public class CreateDailyChallengeCommand implements CommandExecutor
{

	/*
	 * /dailychallenge <create|clone> [parametre...] /dailychallenge create
	 * <name> [date] /dailychallenge clone <date>
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{

		DailyChallenge challenge = new DailyChallenge();

		if (args.length >= 1)
			challenge.setDate(args[0]);

		File file = new File(ChallengeMain.getInstance().getDailyChallengeFolder(), challenge.getDate());
		if (!file.exists())
		{
			if (challenge.getDate().equals(Date.getDateTodayInString()))
				ChallengeMain.getInstance().setChallengeOfToday(challenge);
			else
			{
				try
				{
					DailyChallengeManager.saveDailyChallenge(challenge);
				}
				catch (IOException e)
				{
					System.err.println("Erreur Sauvegarde de DailyChallenge");
					System.err.println(e.getMessage());
				}
			}

			ChallengeMain.sendMessage(sender, "DailyChallenge créé !");
			return true;
		}
		else
			ChallengeMain.sendMessage(sender, "DailyChallenge déjà existant !");

		return true;
	}

}
