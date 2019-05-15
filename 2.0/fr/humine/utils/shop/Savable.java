package fr.humine.utils.shop;

import java.io.File;
import java.io.FileNotFoundException;

import fr.humine.utils.exceptions.SaveFileException;
import fr.humine.utils.exceptions.SettingMissingException;

public interface Savable {

	public void save(File file) throws SaveFileException;
	
	public void load(File file) throws FileNotFoundException, SettingMissingException;
	
}
