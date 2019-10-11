package fr.humine.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.humine.utils.pass.Palier;
import humine.main.MainShop;

/**
 * Classe contenant des {@link ItemStack} predefinis et utilisation 
 * de maniere simple
 * @author Miza
 */
public abstract class ItemShop extends humine.utils.ItemShop{

	public static ItemStack premiumApple() {
		ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Liste premium");
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack freeApple() {
		ItemStack item = new ItemStack(Material.APPLE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Liste gratuite");
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack applePay(Challenger challenger, int price) {
		ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta meta = item.getItemMeta();
		
		if(challenger.hasPremium())
			meta.setDisplayName(ChatColor.GREEN + "Vous avez deja le premium :)");
		else {
			meta.setDisplayName(ChatColor.GOLD + "Achetez le premium");
			List<String> lores = new ArrayList<>();
			lores.add("Prix: " + price + " Humis");
			lores.add("");
			lores.add("Pay");
			meta.setLore(lores);
		}
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack palierPay(Player player, Palier palier) {
		ItemStack item = new ItemStack(palier.getItemRepresentation());
		ItemMeta meta = item.getItemMeta();
		
		int humis = MainShop.getInstance().getBankHumis().getMoney(player);
		
		if(humis >= palier.getPriceHumis()) {
			meta.setDisplayName(ChatColor.GREEN + "Acheter ce palier");
		}
		else {
			meta.setDisplayName(ChatColor.RED + "Vous ne pouvez pas acheter ce palier");
		}
		
		List<String> lores = new ArrayList<>();
		lores.add("Prix: " + palier.getPriceHumis() + " Humis");
		lores.add("Vos Humis: " + humis);
		meta.setLore(lores);
		
		item.setItemMeta(meta);
		
		return item;
	}
}
