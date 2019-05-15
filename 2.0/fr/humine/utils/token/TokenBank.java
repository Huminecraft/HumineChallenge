package fr.humine.utils.token;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.humine.utils.exceptions.SettingMissingException;

/**
 * Classe permettant de gerer les tokens
 * @author miza
 *
 */
public class TokenBank{

	private String moneyNameValue;
	private HashMap<String, TokenAccount> accounts;
	
	/**
	 * Creation d'une banque de token
	 * @param moneyNameValue le nom de la monnaie 
	 */
	public TokenBank(String moneyNameValue) {
		this.moneyNameValue = moneyNameValue;
		this.accounts = new HashMap<String, TokenAccount>();
	}
	
	/**
	 * Creation d'une banque de token
	 * le nom de la monnaie par défaut de ce constructeur 
	 * est "<b>Token</b>"
	 */
	public TokenBank() {
		this("Token");
	}
	
	public boolean addAccount(TokenAccount account) {
		if(this.accounts.containsKey(account.getOwner()))
			return false;
		
		this.accounts.put(account.getOwner(), account);
		return true;
	}
	
	/**
	 * Creation d'un compte pour un joueur
	 * @param playerName le nom du joueur
	 * @return true si le compte a pu etre cree, sinon false
	 */
	public boolean createAccount(String playerName) {
		if(this.accounts.containsKey(playerName))
			return false;
		
		TokenAccount account = new TokenAccount(playerName);
		this.accounts.put(playerName, account);
		
		return true;
	}
	
	/**
	 * Creation d'un compte pour un joueur
	 * @param playerName le nom du joueur
	 * @param token le nombre de token de depart
	 * @return true si le compte a pu etre cree, sinon false
	 */
	public boolean createAccount(String playerName, int token) {
		if(this.accounts.containsKey(playerName))
			return false;
		
		TokenAccount account = new TokenAccount(playerName, token);
		this.accounts.put(playerName, account);
		
		return true;
	}
	
	/**
	 * Supprime le compte d'un joueur
	 * @param playerName le nom du joueur
	 * @return true si le compte a etait supprime, sinon false
	 */
	public boolean removeAccount(String playerName) {
		if(!this.accounts.containsKey(playerName))
			return false;
		
		this.accounts.remove(playerName);
		return true;
	}
	
	/**
	 * @param playerName le nom du joueur
	 * @return le compte du joueur
	 */
	public TokenAccount getAccount(String playerName) {
		return this.accounts.get(playerName);
	}
	
	/**
	 * Ajouter des tokens a un compte
	 * @param playerName le compte du joueur
	 * @param token le nombre de token a rajouter
	 * @return true si l'ajout a etait possible, sinon false
	 */
	public boolean addToken(String playerName, int token) {
		if(!this.accounts.containsKey(playerName))
			return false;
		
		TokenAccount a = this.accounts.get(playerName);
		a.setToken((a.getToken() + token));
		return true;
	}
	
	/**
	 * Ajouter des tokens a un compte
	 * @param account le compte du joueur
	 * @param token le nombre de token a rajouter
	 * @return true si l'ajout a etait possible, sinon false
	 */
	public boolean addToken(TokenAccount account, int token) {
		if(account == null)
			return false;
		
		account.setToken((account.getToken() + token));
		return true;
	}
	
	/**
	 * Supprime des tokens a un compte
	 * @param playerName le compte du joueur
	 * @param token le nombre de token a enlever
	 * @return true si la suppression a etait possible, sinon false
	 */
	public boolean removeToken(String playerName, int token) {
		if(!this.accounts.containsKey(playerName))
			return false;
		
		TokenAccount a = this.accounts.get(playerName);
		a.setToken((a.getToken() - token));
		return true;
	}
	
	/**
	 * supprime des tokens a un compte
	 * @param account le compte du joueur
	 * @param token le nombre de token a enlever
	 * @return true si la suppression a etait possible, sinon false
	 */
	public boolean removeToken(TokenAccount account, int token) {
		if(account == null)
			return false;
		
		account.setToken((account.getToken() - token));
		return true;
	}
	
	/**
	 * @return le nombre de compte
	 */
	public int getSize() {
		return this.accounts.size();
	}
	
	/**
	 * @return les comptes existants
	 */
	public HashMap<String, TokenAccount> getAccounts() {
		return accounts;
	}
	
	/**
	 * @return le nom de la monnaie
	 */
	public String getMoneyNameValue() {
		return moneyNameValue;
	}
	
	/**
	 * @param moneyNameValue le nom de la nouvelle monnaie
	 */
	public void setMoneyNameValue(String moneyNameValue) {
		this.moneyNameValue = moneyNameValue;
	}
	
	public static boolean save(TokenBank bank, File folder) {
		if(folder.exists()) {
			for(File f : folder.listFiles())
				f.delete();
			folder.delete();
		}
		
		if(!folder.mkdirs())
			return false;
		
		File index = new File(folder, "index.yml");
		if(!index.exists()) {
			try {
				index.createNewFile();
				FileConfiguration config = YamlConfiguration.loadConfiguration(index);
				config.set("moneyNameValue", bank.getMoneyNameValue());
				config.save(index);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		for(TokenAccount a : bank.getAccounts().values()) {
			File file = new File(folder, a.getOwner() + ".yml");
			
			if(!TokenAccount.save(a, file))
				return false;
		}
		
		return true;
		
	}
	
	public static TokenBank loads(File folder) throws FileNotFoundException, SettingMissingException {
		if(!folder.exists())
			throw new FileNotFoundException("Dossier TokenBank introuvable");
		
		File index = new File(folder, "index.yml");
		if(!index.exists())
			throw new FileNotFoundException("index.yml de TokenBank introuvable");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(index);
		
		if(!config.contains("moneyNameValue")) {
			throw new SettingMissingException();
		}
		
		TokenBank bank = new TokenBank(config.getString("moneyNameValue"));

		for(File f : folder.listFiles()) {
			if(f.getName().startsWith("index"))
				continue;
			
			TokenAccount account = TokenAccount.load(f);
			bank.addAccount(account);
		}
		
		return bank;
	}

}
