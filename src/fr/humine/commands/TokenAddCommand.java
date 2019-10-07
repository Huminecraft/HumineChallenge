package fr.humine.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;

public class TokenAddCommand implements CommandExecutor{

	private static final String COMMAND = "/addtoken <name> <amount>";
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 2) {
			ChallengeMain.sendMessage(sender, "Argument insuffisant");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		if(target == null) {
			ChallengeMain.sendMessage(sender, "Joueur inexistant");
			return false;
		}
		
		if(!isNumber(args[1])) {
			ChallengeMain.sendMessage(sender, "Montant invalide");
			return false;
		}
		
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(target);
		challenger.getToken().addToken(Integer.parseInt(args[1]));
		
		ChallengeMain.sendMessage(sender, "Vous avez donne " + args[1] + " Token a " + args[0]);
		ChallengeMain.sendMessage(target, "Vous avez recu " + args[1] + " Token de la part de " + sender.getName());
		return true;
	}
	
	private boolean isNumber(String str) {
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) < '0' && str.charAt(i) > '9')
				return false;
		}
		return true;
	}
}
