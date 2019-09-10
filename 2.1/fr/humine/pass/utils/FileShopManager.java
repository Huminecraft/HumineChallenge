package fr.humine.pass.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import fr.humine.main.ChallengeMain;
import humine.utils.cosmetiques.Cosmetique;

public abstract class FileShopManager {

	private static File folder = null;
	
	public static void createDefaultFolder(File location) {
		File folder = new File(location, "Paliers");
		FileShopManager.folder = folder;
		
		File freePalier = new File(folder, "FreePaliers");
		File premiumPalier = new File(folder, "PremiumPalier");
		
		folder.mkdirs();
		freePalier.mkdirs();
		premiumPalier.mkdirs();
	}
	
	public static List<Palier> loadFreePaliers() {
		if(folder == null)
			return null;
		
		List<Palier> paliers = new ArrayList<Palier>();
		FileConfiguration config;
		File freePalier = new File(folder, "FreePaliers");
		
		for(File f : freePalier.listFiles()) {
			config = YamlConfiguration.loadConfiguration(f);
			
			if(!config.contains("numero") || !config.contains("item") || !config.contains("humis")
					|| !config.contains("pixel") || !config.contains("token") || !config.contains("cosmetique")) {
				System.err.println("FREE-PALIER : NON-LOAD : Fichier " + f.getName());
				continue;
			}
			
			ItemStack item = getItem(config.getString("item"));
			Cosmetique c = ChallengeMain.getInstance().getBankCosmetique().getCosmetique(config.getString("cosmetique"));
			Palier p = new Palier(config.getInt("numero"), item, config.getInt("humis"), config.getInt("pixel"), config.getInt("token"), c, false, false);
			paliers.add(p);
		}
		
		return paliers;
	}
	
	public static List<Palier> loadPremiumPaliers() {
		if(folder == null)
			return null;
		
		List<Palier> paliers = new ArrayList<Palier>();
		FileConfiguration config;
		File freePalier = new File(folder, "FreePaliers");
		
		for(File f : freePalier.listFiles()) {
			config = YamlConfiguration.loadConfiguration(f);
			
			if(!config.contains("numero") || !config.contains("item") || !config.contains("humis")
					|| !config.contains("pixel") || !config.contains("token") || !config.contains("cosmetique")) {
				System.err.println("PREMIUM-PALIER : NON-LOAD : Fichier " + f.getName());
				continue;
			}
			
			ItemStack item = getItem(config.getString("item"));
			Cosmetique c = ChallengeMain.getInstance().getBankCosmetique().getCosmetique(config.getString("cosmetique"));
			Palier p = new Palier(config.getInt("numero"), item, config.getInt("humis"), config.getInt("pixel"), config.getInt("token"), c, false, true);
			paliers.add(p);
		}
		
		return paliers;
	}

	private static ItemStack getItem(String item) {
		for(Material material : Material.values()) {
			if(material.name().equalsIgnoreCase(item)) {
				return new ItemStack(material);
			}
		}
		return null;
	}
}
