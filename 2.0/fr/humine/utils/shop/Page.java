package fr.humine.utils.shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.humine.utils.exceptions.SaveFileException;
import fr.humine.utils.exceptions.SettingMissingException;

public class Page implements Savable{

	private static final byte lineLimit = 9;

	private Palier[] freePalier;
	private Palier[] premiumPalier;

	private ItemStack freeLogo;
	private ItemStack premiumLogo;

	public Page(Palier[] freePalier, Palier[] premiumPalier) {
		this.freePalier = freePalier;
		this.premiumPalier = premiumPalier;

		this.freeLogo = ItemShop.freeApple();
		this.premiumLogo = ItemShop.premiumApple();
	}

	public Page(Palier[] freePalier) {
		this(freePalier, new Palier[lineLimit]);
	}

	public Page() {
		this(new Palier[lineLimit], new Palier[lineLimit]);
	}

	public boolean freePalierIsEmpty() {
		for (int i = 0; i < this.freePalier.length; i++) {
			if (this.freePalier[i] != null)
				return false;
		}
		return true;
	}

	public boolean premiumPalierIsEmpty() {
		for (int i = 0; i < this.premiumPalier.length; i++) {
			if (this.premiumPalier[i] != null)
				return false;
		}
		return true;
	}

	public boolean isEmpty() {
		return freePalierIsEmpty() && premiumPalierIsEmpty();
	}

	public boolean freePalierIsFull() {
		for (int i = 0; i < this.freePalier.length; i++) {
			if (this.freePalier[i] == null)
				return false;
		}
		return true;
	}

	public boolean premiumPalierIsFull() {
		for (int i = 0; i < this.freePalier.length; i++) {
			if (this.freePalier[i] == null)
				return false;
		}
		return true;
	}

	public boolean isFull() {
		return freePalierIsFull() && premiumPalierIsFull();
	}

	public boolean addPalier(TypePalier type, Palier palier) {
		if (type == TypePalier.FREE) {
			if (!freePalierIsFull()) {
				for (int i = 0; i < this.freePalier.length; i++) {
					if (this.freePalier[i] == null) {
						this.freePalier[i] = palier;
						return true;
					}
				}
			}
		} else if (type == TypePalier.PREMIUM) {
			if (!premiumPalierIsFull()) {
				for (int i = 0; i < this.premiumPalier.length; i++) {
					if (this.premiumPalier[i] == null) {
						this.premiumPalier[i] = palier;
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean setPalier(TypePalier type, Palier palier, int slot) {
		if (type == TypePalier.FREE) {
			if (slot >= 0 && slot < this.freePalier.length) {
				this.freePalier[slot] = palier;
				return true;
			}
		} else if (type == TypePalier.PREMIUM) {
			if (slot >= 0 && slot < this.premiumPalier.length) {
				this.premiumPalier[slot] = palier;
				return true;
			}
		}

		return false;
	}

	public Palier getPalier(TypePalier type, int slot) {
		if (type == TypePalier.FREE) {
			if (slot >= 0 && slot < this.freePalier.length) {
				return this.freePalier[slot];
			}
		} else if (type == TypePalier.PREMIUM) {
			if (slot >= 0 && slot < this.premiumPalier.length) {
				return this.premiumPalier[slot];
			}
		}

		return null;
	}

	public boolean removePalier(TypePalier type, Palier palier) {
		if (type == TypePalier.FREE) {
			for (int i = 0; i < this.freePalier.length; i++) {
				if (this.freePalier[i] != null) {
					if (this.freePalier[i].equals(palier)) {
						this.freePalier[i] = null;
						return true;
					}
				}
			}
		} else if (type == TypePalier.PREMIUM) {
			for (int i = 0; i < this.premiumPalier.length; i++) {
				if (this.premiumPalier[i] != null) {
					if (this.premiumPalier[i].equals(palier)) {
						this.premiumPalier[i] = null;
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean removePalier(TypePalier type, int slot) {
		if (type == TypePalier.FREE) {
			if (slot >= 0 && slot < this.freePalier.length) {
				this.freePalier[slot] = null;
				return true;
			}
		} else if (type == TypePalier.PREMIUM) {
			if (slot >= 0 && slot < this.premiumPalier.length) {
				this.premiumPalier[slot] = null;
				return true;
			}
		}

		return false;
	}

	public boolean contains(TypePalier type, Palier palier) {
		if (type == TypePalier.FREE) {
			for (int i = 0; i < this.freePalier.length; i++) {
				if (this.freePalier[i].equals(palier)) {
					return true;
				}
			}
		} else if (type == TypePalier.PREMIUM) {
			for (int i = 0; i < this.premiumPalier.length; i++) {
				if (this.premiumPalier[i].equals(palier)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean contains(Palier palier) {
		return contains(TypePalier.FREE, palier) || contains(TypePalier.PREMIUM, palier);
	}

	public ItemStack getFreeLogo() {
		return freeLogo;
	}

	public void setFreeLogo(ItemStack freeLogo) {
		this.freeLogo = freeLogo;
	}

	public ItemStack getPremiumLogo() {
		return premiumLogo;
	}

	public void setPremiumLogo(ItemStack premiumLogo) {
		this.premiumLogo = premiumLogo;
	}

	public Palier[] getFreePalier() {
		return freePalier;
	}

	public Palier[] getPremiumPalier() {
		return premiumPalier;
	}

	@Override
	public void save(File folder) throws SaveFileException {
		if(!folder.exists())
			folder.mkdirs();
		
		for (int i = 0; i < this.freePalier.length; i++) {
			File file = new File(folder, i + ".yml");
			this.freePalier[i].save(file);
		}
		
		for (int i = 0; i < this.premiumPalier.length; i++) {
			File file = new File(folder, i + ".yml");
			this.premiumPalier[i].save(file);
		}
		
		File index = new File(folder, "index.yml");
		try {
			index.createNewFile();
			
			FileConfiguration config = YamlConfiguration.loadConfiguration(index);
			
			config.set("FreeLogo", this.freeLogo);
			config.set("PremiumLogo", this.premiumLogo);
			
			config.save(index);
		} catch (IOException e) {
			throw new SaveFileException();
		}
	}

	@Override
	public void load(File folder) throws FileNotFoundException, SettingMissingException {
		if(!folder.exists())
			throw new FileNotFoundException("Dossier de la page introuvable");
		
		File f = new File(folder, "index.yml");
		if(!f.exists())
			throw new FileNotFoundException("index.yml de la page introuvable");

		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		if(!config.contains("FreeLogo") || !config.contains("PremiumLogo"))
			throw new SettingMissingException("Parametre manquant dans index.yml de la page");
		
		this.freeLogo = config.getItemStack("FreeLogo");
		this.premiumLogo = config.getItemStack("PremiumLogo");
		
		for(File file : folder.listFiles()) {
			if(file.getName().startsWith("index"))
				continue;
			
			Palier p = new Palier(0, null, 0, 0, 0);
			p.load(file);
			
			if(p.isPremium())
				addPalier(TypePalier.PREMIUM, p);
			else
				addPalier(TypePalier.FREE, p);
		}
		
	}
	
	public static Inventory PageToInventory(Page page, String name) {
		Inventory inv = Bukkit.createInventory(null, (9 * 5), name);
		
		inv.setItem(5, page.getFreeLogo());
		inv.setItem((2*8) + 5, page.getPremiumLogo());
		
		Arrays.sort(page.getFreePalier());
		Arrays.sort(page.getPremiumPalier());
		
		for(int i = 0; i < page.getFreePalier().length; i++) {
			if(page.getFreePalier()[i] != null) {
				inv.setItem((9 + i), Palier.PalierToItemStack(page.getFreePalier()[i]));
			}
		}
		
		for(int i = 0; i < page.getPremiumPalier().length; i++) {
			if(page.getPremiumPalier()[i] != null) {
				inv.setItem(((9*3) + i), Palier.PalierToItemStack(page.getPremiumPalier()[i]));
			}
		}
		
		inv.setItem(inv.getSize() - 9, ItemShop.itemQuit());
		inv.setItem(inv.getSize() - 1, ItemShop.itemQuit());
		
		inv.setItem(inv.getSize() - 6, ItemShop.itemPreviousArrow());
		inv.setItem(inv.getSize() - 4, ItemShop.itemNextArrow());
		
		return inv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((freeLogo == null) ? 0 : freeLogo.hashCode());
		result = prime * result + Arrays.hashCode(freePalier);
		result = prime * result + ((premiumLogo == null) ? 0 : premiumLogo.hashCode());
		result = prime * result + Arrays.hashCode(premiumPalier);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Page))
			return false;
		Page other = (Page) obj;
		if (freeLogo == null) {
			if (other.freeLogo != null)
				return false;
		} else if (!freeLogo.equals(other.freeLogo))
			return false;
		if (!Arrays.equals(freePalier, other.freePalier))
			return false;
		if (premiumLogo == null) {
			if (other.premiumLogo != null)
				return false;
		} else if (!premiumLogo.equals(other.premiumLogo))
			return false;
		if (!Arrays.equals(premiumPalier, other.premiumPalier))
			return false;
		return true;
	}

}
