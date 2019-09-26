package fr.humine.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public abstract class PageApplePay {

	public static final String NAME = "PREMIUM SHOP";
	public static final int SIZE = (9*4);
	public static final int PRIZE = 0;
	public static final Inventory inv;
	
	static {
		inv = Bukkit.createInventory(null, SIZE, NAME);
		inv.setItem(SIZE-9, ItemShop.itemQuit());
		inv.setItem(SIZE-1, ItemShop.itemQuit());
	}
	
	public static void openShop(Challenger challenger) {
		inv.setItem(SIZE - (9*2) - 5, ItemShop.applePay(challenger, PRIZE));
		challenger.getPlayer().openInventory(inv);
	}
}
