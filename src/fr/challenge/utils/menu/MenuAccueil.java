package fr.challenge.utils.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.challenge.utils.Challenger;
import fr.challenge.utils.ItemShop;

public abstract class MenuAccueil {

	public final static String NAME = "Challenge Accueil";

	public static void openMenu(Challenger challenger) {
		openMenu(challenger.getPlayer());
	}

	public static void openMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, (9 * 3), NAME);

		inv.setItem(9 + 1, ItemShop.dailyItem());
		inv.setItem(9 + 4, ItemShop.survivalPassItem());
		inv.setItem(9 + 6, ItemShop.changeHebdoItem());
		inv.setItem(9 + 7, ItemShop.hebdoItem());

		player.openInventory(inv);
	}
}
