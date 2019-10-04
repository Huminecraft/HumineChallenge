package fr.humine.commands.challenges;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;

public class ShowHedboChallengeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player) {
			Player player = (Player) sender;
			Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(player);
			
			if(challenger == null) {
				ChallengeMain.sendMessage(sender, "Vous n'avez pas de compte Challenger, contactez l'Administration");
				return false;
			}
			
			if(!challenger.hasPremium()) {
				ChallengeMain.sendMessage(sender, "Vous devez etre un membre du HumineClub");
				return false;
			}
			
			if(challenger.getHebdoChallenge().isEmpty()) {
				ChallengeMain.sendMessage(sender, "Aucun challenge !");
				return true;
			}
			
			for(Challenge c : challenger.getHebdoChallenge()) {
				c.showChallenge(challenger);
				sender.sendMessage("=====");
			}
		}
		
		return true;
	}
}
