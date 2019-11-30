package fr.challenge.utils.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import fr.challenge.utils.Challenger;
import fr.challenge.utils.ItemShop;
import fr.challenge.utils.pass.Palier;

/**
 * Page par defaut de debloquage d'un {@link Palier}
 * 
 * @author Miza
 */
public abstract class MenuUnlockPalier {

	public static final String NAME = "UNLOCK PALIER";
	public static final int SIZE = (9 * 4);
	public static Inventory inv;

	/**
	 * Ouvre l'inventaire
	 * 
	 * @param challenger la cible
	 */
	public static void openMenu(Challenger challenger, Palier palier, boolean pay, boolean demo) {
		String name = NAME + " " + palier.getNumeroPalier();

		inv = Bukkit.createInventory(null, SIZE, name);
		inv.setItem(SIZE - 9, ItemShop.itemQuit());
		inv.setItem(SIZE - 1, ItemShop.itemQuit());
		inv.setItem(SIZE - (9 * 2) - 6, ItemShop.itemDemo());
		
		if(pay)
			inv.setItem(SIZE - (9 * 2) - 5, ItemShop.palierPay(challenger.getPlayer(), palier));

		if(demo)
			challenger.getPlayer().openInventory(inv);
	}
}
