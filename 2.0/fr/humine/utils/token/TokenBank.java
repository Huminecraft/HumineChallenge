package fr.humine.utils.token;

import java.io.Serializable;
import java.util.HashMap;


/**
 * Classe permettant de gerer les tokens
 * @author miza
 *
 */
public class TokenBank implements Serializable{

	private static final long serialVersionUID = -8878858346782253585L;
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
	
	public boolean contains(String playerName) {
		return getAccount(playerName) != null;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((moneyNameValue == null) ? 0 : moneyNameValue.hashCode());
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
		TokenBank other = (TokenBank) obj;
		if (accounts == null)
		{
			if (other.accounts != null)
				return false;
		}
		else if (!accounts.equals(other.accounts))
			return false;
		if (moneyNameValue == null)
		{
			if (other.moneyNameValue != null)
				return false;
		}
		else if (!moneyNameValue.equals(other.moneyNameValue))
			return false;
		return true;
	}
	
	
}
