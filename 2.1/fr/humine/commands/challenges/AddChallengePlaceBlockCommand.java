package fr.humine.commands.challenges;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.challenges.ChallengePlaceBlock;

public class AddChallengePlaceBlockCommand implements CommandExecutor{

	private static final String COMMAND = "/addchallengeplaceblock <title> <ItemToPlace> <amount> [premium] [description]";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		if(args.length < 3) {
			ChallengeMain.sendMessage(sender, "Argument insuffisant");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		ItemStack item = getItem(args[1]);
		if(item == null) {
			ChallengeMain.sendMessage(sender, "ItemToPlace invalide");
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
		
		ChallengePlaceBlock challenge = new ChallengePlaceBlock(args[0], description, item, Integer.parseInt(args[2]), premium);
		ChallengeMain.getDailyChallenge().add(challenge);

		ChallengeMain.sendMessage(sender, "ChallengePlaceBlock " + args[0] + " ajoute !");
		return true;
	}
	
	private boolean isNumber(String str) {
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) < '0' && str.charAt(i) > '9')
				return false;
		}
		return true;
	}
	
	private ItemStack getItem(String str) {
		for(Material m : Material.values()) {
			if(m.name().equalsIgnoreCase(str))
				return new ItemStack(m);
		}
		return null;
	}
}
