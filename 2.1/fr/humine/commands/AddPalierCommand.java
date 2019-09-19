package fr.humine.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.humine.main.ChallengeMain;
import fr.humine.pass.utils.Palier;
import fr.humine.utils.Challenger;
import humine.utils.cosmetiques.Cosmetique;

public class AddPalierCommand implements CommandExecutor
{
	private static final String COMMAND = "/createpalier <numPalier> <MaterialPresentation> <humis> <pixel> <token> [premium] [numeroCosmetique]";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player)) {
			ChallengeMain.sendMessage(sender, "Vous devez etre un joueur");
			return false;
		}
		
		if(args.length < 5) {
			ChallengeMain.sendMessage(sender, "Argument insuffisant");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		if(!isNumber(args[0])) {
			ChallengeMain.sendMessage(sender, "Numero de palier invalide");
			return false;
		}
		
		ItemStack itemPresentation = getItem(args[1]);
		if(itemPresentation == null) {
			ChallengeMain.sendMessage(sender, "MaterialPresentation invalide");
			return false;
		}
		
		if(!isNumber(args[2])) {
			ChallengeMain.sendMessage(sender, "Humis invalide");
			return false;
		}
		
		if(!isNumber(args[3])) {
			ChallengeMain.sendMessage(sender, "Pixel invalide");
			return false;
		}
		
		if(!isNumber(args[4])) {
			ChallengeMain.sendMessage(sender, "Token invalide");
			return false;
		}
		
		boolean premium = false;
		if(args.length >= 6 && args[5].equalsIgnoreCase("true")) {
			premium = true;
		}
		
		Cosmetique cosmetique = null;
		if(args.length >= 7) {
			
		}
		
		Palier palier = new Palier(Integer.parseInt(args[0]), itemPresentation, Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), cosmetique, false, premium);
		
		if(ChallengeMain.getPassMain().addPalier(palier)) {
			for(Challenger c : ChallengeMain.getInstance().getBankChallenger().getChallengers())
				c.getChallengePass().update(ChallengeMain.getPassMain());
			ChallengeMain.sendMessage(sender, "Palier n" + args[0] + " " + palier.getType().toString() + " ajoute !");
		}
		else {
			ChallengeMain.sendMessage(sender, "Palier n" + args[0] + " " + palier.getType().toString() + " NON ajoute !");
		}
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
