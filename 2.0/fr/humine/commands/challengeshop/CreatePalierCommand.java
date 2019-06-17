package fr.humine.commands.challengeshop;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import fr.humine.ChallengeMain;
import fr.humine.utils.shop.Palier;
import fr.humine.utils.shop.TypePalier;
import humine.utils.cosmetiques.Cosmetique;

public class CreatePalierCommand implements CommandExecutor{

	private static String command = "/createpalier <numero> <itemRepresentation> <humis> <pixel> <tokenToPass> [premium] [cosmetiqueID]";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 5) {
			sender.sendMessage(command);
			return false;
		}
		
		if(!isNumber(args[0]) || args[0].length() > 4) {
			sender.sendMessage("numero palier invalide");
			sender.sendMessage(command);
			return false;
		}
		
		ItemStack item = getItem(args[1]);
		if(item == null) {
			sender.sendMessage("Item Representation invalide");
			sender.sendMessage(command);
			return false;
		}
		
		if(!isNumber(args[2]) || !isNumber(args[3]) || !isNumber(args[4])) {
			sender.sendMessage("Humis, Pixel ou TokenPass invalide");
			sender.sendMessage(command);
			return false;
		}
		
		int ItemID = ChallengeMain.getInstance().getBankItemStack().getFreeID();
		ChallengeMain.getInstance().getBankItemStack().addItemStack(ItemID, item);
		Palier palier = new Palier(Integer.parseInt(args[0]), Integer.toString(ItemID), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
		
		if(args.length >= 6) {
			if(args[5].equalsIgnoreCase("true"))
				palier.setType(TypePalier.PREMIUM);
		}
		
		if(args.length >= 7) {
			Cosmetique c = ChallengeMain.getInstance().getBankCosmetique().getCosmetique(args[6]);
			if(c != null) {
				palier.setCosmetique(c.getId());
			}
			else
				sender.sendMessage("Cosmetique non enregistre car id introuvable");
		}
		
		ChallengeMain.getInstance().getBankChallengeShop().getDefaultChallengeShop().addPalier(palier);
		ChallengeMain.getInstance().getBankChallengeShop().update();
		
		sender.sendMessage("Palier cree !");
		Palier.descriptionPalier(sender, palier);
		return true;
	}

	private ItemStack getItem(String string) {
		for(Material material : Material.values()) {
			if(material.name().equalsIgnoreCase(string))
				return new ItemStack(material);
		}
		return null;
	}

	private boolean isNumber(String str) {
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) < '0' || str.charAt(i) > '9')
				return false;
		}
		return true;
	}
}
