package fr.humine.pass.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import humine.utils.Prestige;
import humine.utils.cosmetiques.Cosmetique;
import humine.utils.cosmetiques.MaterialHatCosmetique;
import humine.utils.cosmetiques.ParticleCosmetique;

public class FileCosmetiqueManager {

	private static File folder = null;
	
	public static void createDefaultFolder(File location) {
		File folder = new File(location, "Cosmetiques");
		FileCosmetiqueManager.folder = folder;
		
		folder.mkdirs();
	}
	
	public static List<Cosmetique> loadCosmetiques() {
		if(folder == null)
			return null;
		
		List<Cosmetique> cosmetiques = new ArrayList<Cosmetique>();
		FileConfiguration config;
		
		for(File f : folder.listFiles()) {
			config = YamlConfiguration.loadConfiguration(f);
			
			if(!config.contains("name") || !config.contains("item") || !config.contains("humis")
					|| !config.contains("pixel") || !config.contains("prestige") || !config.contains("type")) {
				System.err.println("COSMETIQUES : NON-LOAD : Fichier " + f.getName());
				continue;
			}
			
			Cosmetique c = getCosmetique(config.getString("type"), config);
			cosmetiques.add(c);
		}
		
		return cosmetiques;
	}
	
	private static Cosmetique getCosmetique(String type, FileConfiguration config) {
		if(type.equalsIgnoreCase("particle"))
			return new ParticleCosmetique(config.getString("name"), getItem(config.getString("item")), config.getInt("humis"), config.getInt("pixel"), getParticle(config.getString("particle")), getPrestige(config.getString("prestige")));
		else if(type.equalsIgnoreCase("material_hat"))
			return new MaterialHatCosmetique(config.getString("name"), getItem(config.getString("item")), config.getInt("humis"), config.getInt("pixel"), getItem(config.getString("particle")).getType(), getPrestige(config.getString("prestige")));
		else if(type.equalsIgnoreCase("custom_head"))
			return null;
		else
			return null;
	}
	
	private static ItemStack getItem(String item) {
		for(Material material : Material.values()) {
			if(material.name().equalsIgnoreCase(item)) {
				return new ItemStack(material);
			}
		}
		return null;
	}
	
	private static Particle getParticle(String particle) {
		for(Particle p : Particle.values()) {
			if(p.name().equalsIgnoreCase(particle)) {
				return p;
			}
		}
		return null;
	}
	
	private static Prestige getPrestige(String prestige) {
		for(Prestige p : Prestige.values()) {
			if(p.name().equalsIgnoreCase(prestige)) {
				return p;
			}
		}
		return null;
	}
}
