package fr.challenge.commands.challenges;

import java.util.Arrays;

import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.challenge.main.ChallengeMain;
import fr.challenge.main.ChallengeUtils;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.challenges.ChallengeBiomeDiscover;

public class AddChallengeBiomeDiscoverCommand implements CommandExecutor{

	private static final String COMMAND = "/addchallengebiomediscover <title> <biome> [premium] [description]";
	
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
		
		Biome biome = ChallengeUtils.getBiome(args[1]);
		if(biome == null) {
			ChallengeMain.sendMessage(sender, "Biome invalide");
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
			for(int i = 3; i < args.length; i++)
				description += args[i] + " ";
		}
		
		ChallengeBiomeDiscover challenge = new ChallengeBiomeDiscover(args[0], description, biome, premium);
		
		if(!hebdo) {
			ChallengeMain.getDailyChallenge().add(challenge);
			for(Challenger c : ChallengeMain.getInstance().getBankChallenger().getChallengers())
				c.updateDailyChallenge(ChallengeMain.getDailyChallenge());
		}
		else {
			ChallengeMain.getHebdoChallenge().add(challenge);
			for(Challenger c : ChallengeMain.getInstance().getBankChallenger().getChallengers())
				c.updateHebdoChallenge(ChallengeMain.getHebdoChallenge());
		}
		
		ChallengeMain.sendMessage(sender, "ChallengeBreakBlock " + args[0] + " ajoute !");
		return true;
	}

}
