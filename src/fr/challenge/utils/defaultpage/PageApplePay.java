package fr.challenge.utils.defaultpage;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import fr.challenge.utils.Challenger;
import fr.challenge.utils.ItemShop;

/**
 * Page par defaut de paiement pour le HumineSurvival
 * 
 * @author Miza
 */
public abstract class PageApplePay {

	public static final String NAME = "PREMIUM SHOP";
	public static final int SIZE = (9 * 4);
	public static int PRIZE = 0;
	public static Inventory inv;

	/**
	 * Ouvre l'inventaire
	 * 
	 * @param challenger la cible
	 */
	public static void openShop(Challenger challenger) {
		inv = Bukkit.createInventory(null, SIZE, NAME);
		inv.setItem(SIZE - (9 * 2) - 5, ItemShop.applePay(challenger, PRIZE));
		inv.setItem(SIZE - 9, ItemShop.itemQuit());
		inv.setItem(SIZE - 1, ItemShop.itemQuit());
		challenger.getPlayer().openInventory(inv);
	}
}
