package fr.humine.utils;

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
}
