package fr.humine.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.humine.main.ChallengeMain;
import humine.utils.Prestige;
import humine.utils.cosmetiques.MaterialHatCosmetique;

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
		
		ItemStack item = getItem(args[4]);
		if(item == null) {
			ChallengeMain.sendMessage(sender, "MaterialHat invalide");
			return false;
		}
		
		if(!isNumber(args[5])) {
			ChallengeMain.sendMessage(sender, "ID invalide");
			return false;
		}
		
		Prestige prestige = null;
		if(args.length >= 7) {
			prestige = getPrestige(args[6]);
		}
		
		if(prestige == null)
			prestige = Prestige.COMMUN;
		
		MaterialHatCosmetique p = new MaterialHatCosmetique(args[0].replace("_", " "), itemPresentation, Integer.parseInt(args[2]), Integer.parseInt(args[3]), item.getType(), prestige);
		p.setId(args[5]);
		
		ChallengeMain.getInstance().getBankCosmetique().addCosmetique(p);
		ChallengeMain.sendMessage(sender, "Cosmetique " + p.getId() + " ajoute !");
		return true;
	}
	
	private Prestige getPrestige(String str) {
		for(Prestige p : Prestige.values()) {
			if(p.name().equalsIgnoreCase(str))
				return p;
		}
		return null;
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
