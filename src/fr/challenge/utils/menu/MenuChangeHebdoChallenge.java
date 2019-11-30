package fr.challenge.utils.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.ItemShop;
import fr.challenge.utils.challenges.Challenge;

/**
 * Page par defaut de Liste des {@link Challenge} hebdomadaires du
 * {@link Challenger}
 * 
 * @author Miza
 */
public abstract class MenuChangeHebdoChallenge {

	public static final String NAME = "HEBDO MENU";
	public static final int SIZE = (9 * 6);
	public static int PRIZE = 0;
	public static Inventory inv;

	/**
	 * Ouvre l'inventaire
	 * 
	 * @param challenger la cible
	 */
	public static void openMenu(Challenger challenger) {
		inv = Bukkit.createInventory(null, SIZE, NAME);
		inv.setItem(SIZE - 9, ItemShop.itemQuit());
		inv.setItem(SIZE - 1, ItemShop.itemQuit());

		for (int i = 1; i <= ChallengeMain.getInstance().getCurrentWeek(); i++) {
			ItemStack item = new ItemStack(Material.BOOK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.AQUA + "Semaine " + i);
			item.setItemMeta(meta);
			inv.setItem(i - 1, item);
		}

		challenger.getPlayer().openInventory(inv);
	}

}
