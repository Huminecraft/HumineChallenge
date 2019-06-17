package fr.humine.utils.bankid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.humine.utils.exceptions.SaveFileException;
import fr.humine.utils.exceptions.SettingMissingException;
import fr.humine.utils.shop.Savable;
import humine.utils.Prestige;
import humine.utils.cosmetiques.Cosmetique;
import humine.utils.cosmetiques.CustomHeadCosmetique;
import humine.utils.cosmetiques.MaterialHatCosmetique;
import humine.utils.cosmetiques.ParticleCosmetique;

public class BankCosmetique implements Savable {

	private List<Cosmetique> cosmetiques;

	public BankCosmetique() {
		this.cosmetiques = new ArrayList<Cosmetique>();
	}

	public boolean addCosmetique(Cosmetique cosmetique) {
		return this.cosmetiques.add(cosmetique);
	}

	public boolean removeCosmetique(Cosmetique cosmetique) {
		return this.cosmetiques.remove(cosmetique);
	}

	public Cosmetique getCosmetique(String id) {
		for (Cosmetique c : this.cosmetiques) {
			if (c.getId().equals(id))
				return c;
		}
		return null;
	}

	@Override
	public void save(File folder) throws SaveFileException {
		if (!folder.exists())
			folder.mkdirs();

		for (Cosmetique c : this.cosmetiques) {
			File f = new File(folder, c.getId() + ".yml");
			c.save(f);
		}
	}

	@Override
	public void load(File folder) throws FileNotFoundException, SettingMissingException {
		if (!folder.exists())
			throw new FileNotFoundException();

		for (File f : folder.listFiles()) {
			YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

			if (config.contains("particle")) {
				ParticleCosmetique c = new ParticleCosmetique("", null, 0, 0, null, Prestige.COMMUN);
				c.load(f);
				addCosmetique(c);
			} else if (config.contains("materialHat")) {
				MaterialHatCosmetique c = new MaterialHatCosmetique("", null, 0, 0, null, Prestige.COMMUN);
				c.load(f);
				addCosmetique(c);
			} else if (config.contains("libelle")) {
				CustomHeadCosmetique c = new CustomHeadCosmetique("", null, 0, 0, Prestige.COMMUN, null);
				c.load(f);
				addCosmetique(c);
			}
		}
	}
	
	public List<Cosmetique> getCosmetiques() {
		return cosmetiques;
	}
}
