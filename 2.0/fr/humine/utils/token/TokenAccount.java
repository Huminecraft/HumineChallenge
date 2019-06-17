package fr.humine.utils.token;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.humine.utils.exceptions.SaveFileException;
import fr.humine.utils.exceptions.SettingMissingException;
import fr.humine.utils.shop.Savable;

/**
 * Classe reprensentant un compte bancaire de token
 * 
 * @author miza
 *
 */
public class TokenAccount implements Savable{

	private String owner;
	private int token;

	public TokenAccount(String owner, int token) {
		this.owner = owner;
		this.token = token;
	}

	public TokenAccount(String owner) {
		this(owner, 0);
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + token;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenAccount other = (TokenAccount) obj;
		if (owner == null)
		{
			if (other.owner != null)
				return false;
		}
		else if (!owner.equals(other.owner))
			return false;
		if (token != other.token)
			return false;
		return true;
	}

	@Override
	public void save(File file) throws SaveFileException, IOException
	{
		if(!file.exists()) {
			file.createNewFile();
		}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set("TokenAccount.Owner", this.owner);
		config.set("TokenAccount.Token", this.token);
		config.save(file);
	}

	@Override
	public void load(File file) throws FileNotFoundException, SettingMissingException
	{
		if(!file.exists())
			throw new FileNotFoundException();
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		if(!config.contains("TokenAccount.Owner") || !config.contains("TokenAccount.Token"))
			throw new SettingMissingException();
		
		this.owner = config.getString("TokenAccount.Owner");
		this.token = config.getInt("TokenAccount.Token");
	}
	
	
}
