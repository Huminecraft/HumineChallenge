package fr.humine.utils.shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import fr.humine.utils.exceptions.SaveFileException;
import fr.humine.utils.exceptions.SettingMissingException;

public interface Savable
{
	
	public void save(File file) throws SaveFileException, IOException;

	public void load(File file) throws FileNotFoundException, SettingMissingException;
}
