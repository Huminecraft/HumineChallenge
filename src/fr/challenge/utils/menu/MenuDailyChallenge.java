package fr.challenge.utils.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import fr.challenge.utils.Challenger;
import fr.challenge.utils.ItemShop;
import fr.challenge.utils.challenges.Challenge;

public abstract class MenuDailyChallenge {

	public static final String NAME = "Challenge Daily Menu";
	
	public static void openMenu(Challenger challenger) {
		Inventory inv = Bukkit.createInventory(null, (9*4), NAME);
		
		int i = 0;
		for(Challenge challenge : challenger.getDailyChallenge()) {
			inv.setItem(i, challenge.toItemStack());
			i++;
		}
		
		inv.setItem(inv.getSize() - 9, ItemShop.itemQuit());
		inv.setItem(inv.getSize() - 1, ItemShop.itemQuit());
		
		challenger.getPlayer().openInventory(inv);
	}
}
