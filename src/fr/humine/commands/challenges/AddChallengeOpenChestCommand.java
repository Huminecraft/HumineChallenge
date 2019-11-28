package fr.humine.commands.challenges;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.humine.main.ChallengeMain;
import fr.humine.main.ChallengeUtils;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.ChallengeOpenChest;

public class AddChallengeOpenChestCommand implements CommandExecutor{

	private static final String COMMAND = "/addchallengeopenchest <title> <amount> [premium] [description]";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		boolean hebdo = Arrays.asList(args).contains("HEBDOCHALLENGE");
		if(hebdo)
			args[args.length-1] = "";
		
		if(args.length < 2) {
			ChallengeMain.sendMessage(sender, "Argument insuffisant");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[1])) {
			ChallengeMain.sendMessage(sender, "Amount invalide");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		boolean premium = false;
		if(args.length >= 3) {
			if(args[2].equalsIgnoreCase("true"))
				premium = true;
		}
		
		String description = "";
		if(args.length >= 4) {
			description = args[3];
			for(int i = 4; i < args.length; i++)
				description += " " + args[i];
		}
		
		ChallengeOpenChest challenge = new ChallengeOpenChest(args[0], description, Integer.parseInt(args[1]), premium);

		if(!hebdo) {
			ChallengeMain.getDailyChallenge().add(challenge);
			for(Challenger c : ChallengeMain.getInstance().getBankChallenger().getChallengers())
				c.updateDailyChallenge(ChallengeMain.getDailyChallenge());
		}else {
			ChallengeMain.getHebdoChallenge().add(challenge);
			for(Challenger c : ChallengeMain.getInstance().getBankChallenger().getChallengers())
				c.updateHebdoChallenge(ChallengeMain.getHebdoChallenge());
		}
		
		ChallengeMain.sendMessage(sender, "ChallengeOpenShop " + args[0] + " ajoute !");
		return true;
	}
}
