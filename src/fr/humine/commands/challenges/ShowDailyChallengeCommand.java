package fr.humine.commands.challenges;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;

public class ShowDailyChallengeCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			Player player = (Player) sender;
			Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(player);
			
			if(challenger == null) {
				ChallengeMain.sendMessage(sender, "Vous n'avez pas de compte Challenger, contactez l'Administration");
				return false;
			}
			
			if(challenger.getDailyChallenge().isEmpty()) {
				ChallengeMain.sendMessage(sender, "Aucun challenge !");
				return true;
			}
			
			for(Challenge c : challenger.getDailyChallenge()) {
				c.showChallenge(challenger);
				sender.sendMessage("=====");
			}
		}
		
		return true;
	}
}
