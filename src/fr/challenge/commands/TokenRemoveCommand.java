package fr.challenge.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.challenge.main.ChallengeMain;
import fr.challenge.main.ChallengeUtils;
import fr.challenge.utils.Challenger;

public class TokenRemoveCommand implements CommandExecutor{

	private static final String COMMAND = "/removetoken <name> <amount>";
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
		
		if(!ChallengeUtils.isNumber(args[1])) {
			ChallengeMain.sendMessage(sender, "Montant invalide");
			return false;
		}
		
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(target);
		challenger.getToken().removeToken(Integer.parseInt(args[1]));
		
		ChallengeMain.sendMessage(sender, "Vous avez enleve " + args[1] + " Token a " + args[0]);
		ChallengeMain.sendMessage(target, sender.getName() + " vous a retirez  " + args[1] + " Token");
		return true;
	}
}
