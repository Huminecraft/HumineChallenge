package fr.challenge.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.challenge.main.ChallengeMain;
import fr.challenge.main.ChallengeUtils;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.pass.Palier;
import humine.utils.cosmetiques.Cosmetique;

public class AddPalierCommand implements CommandExecutor
{
	private static final String COMMAND = "/createpalier <numPalier> <MaterialPresentation> <priceHumis> <awardHumis> <awardPixel> <awardExp> <token> [premium] [numeroCosmetique]";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player)) {
			ChallengeMain.sendMessage(sender, "Vous devez etre un joueur");
			return false;
		}
		
		if(args.length < 7) {
			ChallengeMain.sendMessage(sender, "Argument insuffisant");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[0])) {
			ChallengeMain.sendMessage(sender, "Numero de palier invalide");
			return false;
		}
		
		Material itemPresentation = ChallengeUtils.getItem(args[1]);
		if(itemPresentation == null) {
			ChallengeMain.sendMessage(sender, "MaterialPresentation invalide");
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[2])) {
			ChallengeMain.sendMessage(sender, "priceHumis invalide");
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[3])) {
			ChallengeMain.sendMessage(sender, "awardHumis invalide");
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[4])) {
			ChallengeMain.sendMessage(sender, "awardPixel invalide");
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[5])) {
			ChallengeMain.sendMessage(sender, "awardExp invalide");
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[6])) {
			ChallengeMain.sendMessage(sender, "Token invalide");
			return false;
		}
		
		boolean premium = false;
		if(args.length >= 8 && args[7].equalsIgnoreCase("true")) {
			premium = true;
		}
		
		Cosmetique cosmetique = null;
		if(args.length >= 9) {
			cosmetique = ChallengeMain.getInstance().getBankCosmetique().getCosmetique(args[8]);
		}
		
		int numPalier = Integer.parseInt(args[0]);
		int priceHumis = Integer.parseInt(args[2]);
		int awardHumis = Integer.parseInt(args[3]);
		int awardPixel = Integer.parseInt(args[4]);
		int awardExp = Integer.parseInt(args[5]);
		int tokenPass = Integer.parseInt(args[6]);
		Palier palier = new Palier(numPalier, new ItemStack(itemPresentation), priceHumis, awardHumis, awardPixel, awardExp, tokenPass, cosmetique, false, premium);
				
		ChallengeMain.getPassMain().addPalier(palier);
		for(Challenger c : ChallengeMain.getInstance().getBankChallenger().getChallengers())
			c.getChallengePass().update(ChallengeMain.getPassMain());
		ChallengeMain.sendMessage(sender, "Palier n" + args[0] + " " + palier.getType().toString() + " ajoute !");
		return true;
	}

}
