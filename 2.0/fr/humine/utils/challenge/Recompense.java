package fr.humine.utils.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.humine.utils.exceptions.SaveFileException;
import fr.humine.utils.exceptions.SettingMissingException;
import fr.humine.utils.shop.ChallengeAchivement;
import fr.humine.utils.shop.Savable;
import humine.utils.cosmetiques.Cosmetique;

public class Recompense implements ChallengeAchivement, Savable{

	private int humis;
	private int pixel;
	private int exp;
	private Cosmetique cosmetique;
	
	public Recompense(int humis, int pixel, int exp, Cosmetique cosmetique) {
		this.humis = humis;
		this.pixel = pixel;
		this.exp = exp;
		this.cosmetique = cosmetique;
	}
	
	@Override
	public void giveRecompense(Player player) {
		//TODO changer le parametre
	}

	@Override
	public Cosmetique getCosmetique() {
		return this.cosmetique;
	}

	@Override
	public int getHumis() {
		return this.humis;
	}

	@Override
	public int getPixel() {
		return this.pixel;
	}

	@Override
	public int getExperience() {
		return this.exp;
	}

	public void setHumis(int humis) {
		this.humis = humis;
	}

	public void setPixel(int pixel) {
		this.pixel = pixel;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setCosmetique(Cosmetique cosmetique) {
		this.cosmetique = cosmetique;
	}

	@Override
	public void save(File file) throws SaveFileException {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new SaveFileException();
			}
		}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set("Recompense.Humis", this.humis);
		config.set("Recompense.Pixel", this.pixel);
		config.set("Recompense.Exp", this.exp);
		config.set("Recompense.Cosmetique", (this.cosmetique != null) ? this.cosmetique.getId() : null);
		try {
			config.save(file);
		} catch (IOException e) {
			throw new SaveFileException();
		}
	}

	@Override
	public void load(File file) throws FileNotFoundException, SettingMissingException {
		// TODO Auto-generated method stub
		
	}

}
