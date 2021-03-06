package fr.challenge.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.menu.MenuAccueil;

public class ShowMenuAccueil implements CommandExecutor
{

	// commande : /challenge
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
		
		MenuAccueil.openMenu(challenger);
		return true;
	}
}
