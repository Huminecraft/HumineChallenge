package fr.humine.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;

public class ShowTokenCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player)) {
			ChallengeMain.sendMessage(sender, "Vous devez etre un joueur");
			return false;
		}
		
		Player player = (Player) sender;
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(player);
		
		if(challenger == null) {
			ChallengeMain.sendMessage(player, "Vous n'avez pas de compte Challenger, veuillez contacter un Administrateur");
			return false;
		}
		
		ChallengeMain.sendMessage(player, "Votre solde de token : " + challenger.getToken().getAmount());
		
		return true;
	}
}
