package fr.humine.utils.defaultpage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.ItemShop;
import fr.humine.utils.challenges.Challenge;

/**
 * Page par defaut de Liste des {@link Challenge} hebdomadaires du
 * {@link Challenger}
 * 
 * @author Miza
 */
public abstract class PageHebo {

	public static final String NAME = "HEBDO MENU";
	public static final int SIZE = (9 * 6);
	public static int PRIZE = 0;
	public static Inventory inv;

	/**
	 * Ouvre l'inventaire
	 * 
	 * @param challenger la cible
	 */
	public static void openShop(Challenger challenger) {
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
