package humine.utils.token;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Classe reprensentant un compte bancaire de token
 * 
 * @author miza
 *
 */
public class TokenAccount {

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

	public static boolean save(TokenAccount account, File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		config.set("Owner", account.getOwner());
		config.set("Token", account.getToken());
		
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}
	
	public static TokenAccount load(File file) {
		if(!file.exists())
			return null;
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		if(!config.contains("Owner") || !config.contains("Token")) {
			return null;
		}
		
		TokenAccount account = new TokenAccount(config.getString("Owner"), config.getInt("Token"));
		return account;
	}
}
