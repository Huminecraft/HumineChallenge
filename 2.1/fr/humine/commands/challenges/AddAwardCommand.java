package fr.humine.commands.challenges;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Award;
import fr.humine.utils.challenges.Challenge;

public class AddAwardCommand implements CommandExecutor{

	private static final String COMMAND = "/addAward <ChallengeTitre> <exp> <token>";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 3) {
			ChallengeMain.sendMessage(sender, "Argument insuffisant");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		Challenge challenge = null;
		for(Challenge c : ChallengeMain.getDailyChallenge()) {
			if(c.getTitle().equals(args[0])) {
				challenge = c;
			}
		}
		
		if(challenge == null) {
			ChallengeMain.sendMessage(sender, "Challenge introuvable");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		if(!isNumber(args[1])) {
			ChallengeMain.sendMessage(sender, "Exp invalide");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		if(!isNumber(args[2])) {
			ChallengeMain.sendMessage(sender, "Token invalide");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		Award award = new Award(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		challenge.setAward(award);
		
		for(Challenger c : ChallengeMain.getInstance().getBankChallenger().getChallengers())
			c.updateDailyChallenge(ChallengeMain.getDailyChallenge());
		
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
