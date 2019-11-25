package fr.humine.commands;

import fr.humine.main.ChallengeMain;
import fr.humine.main.ChallengeUtils;
import humine.utils.Prestige;
import humine.utils.cosmetiques.MaterialHatCosmetique;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class AddMaterialHatCosmetiqueCommand implements CommandExecutor{

	private static final String COMMAND = "/paliermaterialhatcosmetique <name> <MaterialPresentation> <humis> <pixel> <MaterialHat> <id> [prestige]";
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player)) {
			ChallengeMain.sendMessage(sender, "Vous devez etre un joueur");
			return false;
		}
		
		if(args.length < 6) {
			ChallengeMain.sendMessage(sender, "Argument insuffisant");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		
		Material materialPresentation = ChallengeUtils.getItem(args[4]);
		if(materialPresentation == null) {
			ChallengeMain.sendMessage(sender, "MaterialPresentation invalide");
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[2])) {
			ChallengeMain.sendMessage(sender, "Humis invalide");
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[3])) {
			ChallengeMain.sendMessage(sender, "Pixel invalide");
			return false;
		}
		
		Material item = ChallengeUtils.getItem(args[4]);
		if(item == null) {
			ChallengeMain.sendMessage(sender, "MaterialHat invalide");
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[5])) {
			ChallengeMain.sendMessage(sender, "ID invalide");
			return false;
		}
		
		Prestige prestige = null;
		if(args.length >= 7) {
			prestige = ChallengeUtils.getPrestige(args[6]);
		}
		
		if(prestige == null)
			prestige = Prestige.COMMUN;
		
		MaterialHatCosmetique p = new MaterialHatCosmetique(args[0].replace("_", " "), new ItemStack(materialPresentation), Integer.parseInt(args[2]), Integer.parseInt(args[3]), item, prestige);
		p.setId(args[5]);
		
		ChallengeMain.getInstance().getBankCosmetique().addCosmetique(p);
		ChallengeMain.sendMessage(sender, "Cosmetique " + p.getId() + " ajoute !");
		return true;
	}
}
