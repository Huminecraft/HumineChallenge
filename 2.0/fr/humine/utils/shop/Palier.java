package fr.humine.utils.shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.humine.utils.exceptions.SaveFileException;
import fr.humine.utils.exceptions.SettingMissingException;
import humine.utils.cosmetiques.Cosmetique;

public class Palier implements Achievement, Savable, Comparable<Palier>, Comparator<Palier>{

	private int numeroPalier;
	private ItemStack itemRepresentation;
	private Cosmetique cosmetique;
	
	private boolean unlock;
	private boolean premium;
	
	private int tokenPass;
	
	private int humis;
	private int pixel;
	
	public Palier(int numPalier, ItemStack itemRepresentation, int humis, int pixel, int tokenPass, Cosmetique c, boolean unlock, boolean premium) {
		this.numeroPalier = numPalier;
		this.itemRepresentation = itemRepresentation;
		this.cosmetique = c;
		this.unlock = unlock;
		this.premium = premium;
		
		this.humis = humis;
		this.pixel = pixel;
	}
	
	public Palier(int numPalier, ItemStack itemRepresentation, int humis, int pixel, int tokenPass, Cosmetique c, boolean unlock) {
		this(numPalier, itemRepresentation, humis, pixel, tokenPass, c, unlock, false);
	}
	
	public Palier(int numPalier, ItemStack itemRepresentation, int humis, int pixel, int tokenPass, Cosmetique c) {
		this(numPalier, itemRepresentation, humis, pixel, tokenPass, c, false, false);
	}
	
	public Palier(int numPalier, ItemStack itemRepresentation, int humis, int pixel, int tokenPass) {
		this(numPalier, itemRepresentation, humis, pixel, tokenPass, null, false, false);
	}

	@Override
	public void giveRecompense(Player player) {
		//TODO donner les recompenses
	}

	@Override
	public Cosmetique getCosmetique() {
		return this.cosmetique;
	}

	public ItemStack getItemRepresentation() {
		return itemRepresentation;
	}

	public void setItemRepresentation(ItemStack itemRepresentation) {
		this.itemRepresentation = itemRepresentation;
	}

	public boolean isUnlock() {
		return unlock;
	}

	public void setUnlock(boolean unlock) {
		this.unlock = unlock;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public int getTokenPass() {
		return tokenPass;
	}

	public void setTokenPass(int tokenPass) {
		this.tokenPass = tokenPass;
	}

	public int getNumeroPalier() {
		return numeroPalier;
	}

	public void setCosmetique(Cosmetique cosmetique) {
		this.cosmetique = cosmetique;
	}

	public void setHumis(int humis) {
		this.humis = humis;
	}

	public void setPixel(int pixel) {
		this.pixel = pixel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cosmetique == null) ? 0 : cosmetique.hashCode());
		result = prime * result + humis;
		result = prime * result + ((itemRepresentation == null) ? 0 : itemRepresentation.hashCode());
		result = prime * result + numeroPalier;
		result = prime * result + pixel;
		result = prime * result + (premium ? 1231 : 1237);
		result = prime * result + tokenPass;
		result = prime * result + (unlock ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Palier))
			return false;
		Palier other = (Palier) obj;
		if (cosmetique == null) {
			if (other.cosmetique != null)
				return false;
		} else if (!cosmetique.equals(other.cosmetique))
			return false;
		if (humis != other.humis)
			return false;
		if (itemRepresentation == null) {
			if (other.itemRepresentation != null)
				return false;
		} else if (!itemRepresentation.equals(other.itemRepresentation))
			return false;
		if (numeroPalier != other.numeroPalier)
			return false;
		if (pixel != other.pixel)
			return false;
		if (premium != other.premium)
			return false;
		if (tokenPass != other.tokenPass)
			return false;
		if (unlock != other.unlock)
			return false;
		return true;
	}


	@Override
	public void save(File file) throws SaveFileException {
		try {
			file.createNewFile();
			
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);

			config.set("NumeroPalier", this.numeroPalier);
			config.set("ItemRepresentation", this.itemRepresentation);
			config.set("Cosmetique", (this.cosmetique != null) ? this.cosmetique.getId() : null);
			config.set("Unlock", this.unlock);
			config.set("Premium", this.premium);
			config.set("TokenPass", this.tokenPass);
			config.set("Humis", this.humis);
			config.set("Pixel", this.pixel);
			config.save(file);
			file.setWritable(false);
			
		} catch (IOException e) {
			throw new SaveFileException("Impossible de sauvegarder le palier");
		}
	}

	@Override
	public void load(File file) throws FileNotFoundException, SettingMissingException {
		if(!file.exists())
			throw new FileNotFoundException();
		
		file.setWritable(true);
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		if(!config.contains("NumeroPalier") || !config.contains("ItemRepresentation")|| 
				!config.contains("Cosmetique") || !config.contains("Unlock") ||
				!config.contains("Premium") || !config.contains("TokenPass") ||
				!config.contains("Humis") || !config.contains("Pixel") ||
				!config.contains("Exp")) {
			throw new SettingMissingException();
		}
		
		this.numeroPalier = config.getInt("NumeroPalier");
		this.itemRepresentation = config.getItemStack("ItemRepresentation");
		this.unlock = config.getBoolean("Unlock");
		this.premium = config.getBoolean("Premium");
		this.tokenPass = config.getInt("TokenPass");
		this.humis = config.getInt("Humis");
		this.pixel = config.getInt("Pixel");
		
		//TODO load le cosmetique
	}
	
	public static ItemStack PalierToItemStack(Palier palier) {
		ItemStack item = palier.getItemRepresentation();
		ChatColor color;
		
		if(palier.isUnlock())
			color = ChatColor.GREEN;
		else
			color = ChatColor.YELLOW;
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(color + "Palier " + palier.getNumeroPalier());
		
		List<String> lores = new ArrayList<>();

		lores.add(color + "Nombre de token pour le debloquer: " + palier.getTokenPass());
		lores.add(" ");
		
		if(palier.getCosmetique() != null) {
			lores.add("Cosmetique: " + palier.getCosmetique().getName());
		}
		
		meta.setLore(lores);
		item.setItemMeta(meta);
		item.setAmount(palier.getNumeroPalier());
		
		return item;
	}

	@Override
	public int compareTo(Palier palier) {
		if(this.numeroPalier < palier.getNumeroPalier())
			return 1;
		else if(this.numeroPalier > palier.getNumeroPalier())
			return -1;
		else
			return 0;
	}

	@Override
	public int compare(Palier p1, Palier p2) {
		return p1.compareTo(p2);
	}
	
}
