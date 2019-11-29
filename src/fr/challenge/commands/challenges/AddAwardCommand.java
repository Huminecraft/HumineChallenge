package fr.challenge.commands.challenges;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.challenge.main.ChallengeMain;
import fr.challenge.main.ChallengeUtils;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.challenges.Award;
import fr.challenge.utils.challenges.Challenge;

public class AddAwardCommand implements CommandExecutor{

	private static final String COMMAND = "/addAward <ChallengeTitre> <exp> <token>";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		boolean hebdo = Arrays.asList(args).contains("HEBDOCHALLENGE");
		if(hebdo)
			args[args.length-1] = "";
		
		if(args.length < 3) {
			ChallengeMain.sendMessage(sender, "Argument insuffisant");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		Challenge challenge = null;
		if(!hebdo) {
			for(Challenge c : ChallengeMain.getDailyChallenge()) {
				if(c.getTitle().equals(args[0])) {
					challenge = c;
				}
			}
		}
		else {
			for(Challenge c : ChallengeMain.getHebdoChallenge()) {
				if(c.getTitle().equals(args[0])) {
					challenge = c;
				}
			}
		}
		
		if(challenge == null) {
			ChallengeMain.sendMessage(sender, "Challenge introuvable");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[1])) {
			ChallengeMain.sendMessage(sender, "Exp invalide");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[2])) {
			ChallengeMain.sendMessage(sender, "Token invalide");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		Award award = new Award(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		challenge.setAward(award);
		
		if(!hebdo) {
			for(Challenger c : ChallengeMain.getInstance().getBankChallenger().getChallengers())
				c.updateDailyChallenge(ChallengeMain.getDailyChallenge());
		}
		else {
			for(Challenger c : ChallengeMain.getInstance().getBankChallenger().getChallengers())
				c.updateHebdoChallenge(ChallengeMain.getHebdoChallenge());
		}
		
		ChallengeMain.sendMessage(sender, "Adward  " + args[0] + " ajoute !");
		
		return true;
	}

}
