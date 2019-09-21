package fr.humine.commands.challenges;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.ChallengeKill;

public class AddChallengeKillCommand implements CommandExecutor{

	private static final String COMMAND = "/addchallengekill <title> <EntityToKill> <amount> [premium] [description]";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 3) {
			ChallengeMain.sendMessage(sender, "Argument insuffisant");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		EntityType entity = getEntity(args[1]);
		if(entity == null) {
			ChallengeMain.sendMessage(sender, "Entity invalide");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		if(!isNumber(args[2])) {
			ChallengeMain.sendMessage(sender, "Amount invalide");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		boolean premium = false;
		if(args.length >= 4) {
			if(args[3].equalsIgnoreCase("true"))
				premium = true;
		}
		
		String description = "";
		if(args.length >= 5) {
			for(int i = 4; i < args.length; i++)
				description += args[i] + " ";
		}
		
		ChallengeKill challenge = new ChallengeKill(args[0], description, entity, Integer.parseInt(args[2]), premium);
		ChallengeMain.getDailyChallenge().add(challenge);

		for(Challenger c : ChallengeMain.getInstance().getBankChallenger().getChallengers())
			c.updateDailyChallenge(ChallengeMain.getDailyChallenge());
		
		ChallengeMain.sendMessage(sender, "ChallengeKill " + args[0] + " ajoute !");
		return true;
	}
	
	private boolean isNumber(String str) {
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) < '0' && str.charAt(i) > '9')
				return false;
		}
		return true;
	}
	
	private EntityType getEntity(String str) {
		for(EntityType e : EntityType.values()) {
			if(e.name().equalsIgnoreCase(str))
				return e;
		}
		return null;
	}
}
