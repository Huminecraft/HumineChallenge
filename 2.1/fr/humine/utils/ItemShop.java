package fr.humine.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
}
