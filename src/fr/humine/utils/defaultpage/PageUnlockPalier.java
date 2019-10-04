package fr.humine.utils.defaultpage;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import fr.humine.utils.Challenger;
import fr.humine.utils.ItemShop;
import fr.humine.utils.pass.Palier;

public abstract class PageUnlockPalier {

	public static final String NAME = "UNLOCK PALIER";
	public static final int SIZE = (9*4);
	public static Inventory inv;
	
	public static void openShop(Challenger challenger, Palier palier) {
		String name = NAME + " " + palier.getNumeroPalier();
		
		inv = Bukkit.createInventory(null, SIZE, name);
		inv.setItem(SIZE-9, ItemShop.itemQuit());
		inv.setItem(SIZE-1, ItemShop.itemQuit());
		inv.setItem(SIZE - (9*2) - 5, ItemShop.palierPay(challenger.getPlayer(), palier));
		
		challenger.getPlayer().openInventory(inv);
	}
}
