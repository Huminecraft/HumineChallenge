package fr.humine.commands.challenges;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.challenges.Challenge;

public class ShowDailyChallengeCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(ChallengeMain.getDailyChallenge().isEmpty()) {
			ChallengeMain.sendMessage(sender, "Aucun challenge !");
			return true;
		}
		
		for(Challenge c : ChallengeMain.getDailyChallenge()) {
			c.showChallenge(sender);
			sender.sendMessage("=====");
		}
		
		return true;
	}
}
