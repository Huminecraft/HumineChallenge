package fr.humine.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.humine.main.ChallengeMain;
import fr.humine.main.ChallengeUtils;
import humine.main.MainShop;
import humine.utils.Prestige;
import humine.utils.cosmetiques.CustomHeadCosmetique;

public class AddCustomHeadCosmetique implements CommandExecutor{

	private static String COMMAND = "/paliercustomhatcosmetique <name> <custom head> <humis> <pixel> <id> [prestige]";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if(args.length < 5) {
			MainShop.sendMessage(player, "Argument insuffisant");
			MainShop.sendMessage(player, COMMAND);
			return false;
		}
		
		if(!hasAFreeSlot(player)) {
			MainShop.sendMessage(player, "Vous n'avez aucune place libre dans votre inventaire");
			return false;
		}
		
		if(!verification(args[1])) {
			MainShop.sendMessage(player, "Custom head invalide");
			MainShop.sendMessage(player, COMMAND);
			return false;
		}
		
		if(!isNumber(args[2]) || !isNumber(args[3])) {
			MainShop.sendMessage(player, "Prix invalide");
			MainShop.sendMessage(player, COMMAND);
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[4])) {
			ChallengeMain.sendMessage(sender, "ID invalide");
			return false;
		}
		
		Prestige prestige = Prestige.COMMUN;
		if(args.length >= 6) {
			prestige = getPrestige(args[5]);
		}
		
		
		ItemStack item = getItemStack(player, args[1]);
		if(item == null) {
			MainShop.sendMessage(player, "Erreur: l'item n'a pas pu etre recupere");
			return false;
		}
		
		int humisPrice = Integer.parseInt(args[3]);
		int pixelPrice = Integer.parseInt(args[4]);
		
		CustomHeadCosmetique cosmetique = new CustomHeadCosmetique(args[0].replace("_", " "), item, humisPrice, pixelPrice, prestige, "");
		cosmetique.setId(args[4]);
		
		ChallengeMain.getInstance().getBankCosmetique().addCosmetique(cosmetique);
		ChallengeMain.sendMessage(sender, "Cosmetique Custom Head " + cosmetique.getId() + " ajoute !");
		return true;
	}
	
	private boolean hasAFreeSlot(Player player) {
		for(int i = 0; i < player.getInventory().getContents().length; i++) {
			if(player.getInventory().getContents()[i] == null || player.getInventory().getContents()[i].getType() == Material.AIR)
				return true;
		}
		
		return false;
	}

	private ItemStack getItemStack(Player player, String head) {
		String cmd = "give " + player.getName() + " " + head + " 1";
		String name = head.split("\"")[4].substring(0, head.split("\"")[4].length() - 1);
		
		MainShop.getInstance().getServer().dispatchCommand(MainShop.getInstance().getServer().getConsoleSender(), cmd);
		
		ItemStack item = null;

		for(int i = 0; i < player.getInventory().getContents().length; i++) {
			if(player.getInventory().getContents()[i] != null && player.getInventory().getContents()[i].getType() != Material.AIR) {
				if(player.getInventory().getContents()[i].getItemMeta().getDisplayName().toLowerCase().equals(name.toLowerCase())) {
					item = player.getInventory().getContents()[i];
					player.getInventory().getContents()[i] = null;
					player.getInventory().setItem(i, null);
				}
			}
		}
		
		return item;
	}

	private boolean verification(String head) {
		if(!head.toLowerCase().contains("minecraft:player_head"))
			return false;
		
		if(!head.toLowerCase().contains("skullowner:"))
			return false;
		
		if(!head.toLowerCase().contains("name:"))
			return false;
		
		if(head.split("\"")[4].substring(0, head.split("\"")[4].length() - 1).equals(""))
			return false;
		
		return true;
	}

	private boolean isNumber(String number) {
		for(int i = 0; i < number.length(); i++) {
			if(number.charAt(i) < '0' || number.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}
	
	private Prestige getPrestige(String particle)
	{
		for(Prestige p : Prestige.values()) {
			if(p.name().equalsIgnoreCase(particle))
				return p;
		}
		
		return Prestige.COMMUN;
	}
}
