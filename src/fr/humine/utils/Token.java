package fr.humine.utils;

import fr.humine.utils.pass.Palier;

/**
 * Classe contenant la monnaie numerique utilisee pour les {@link Palier}
 * @author Miza
 */
public class Token
{
	private int amount;
	
	/**
	 * Constructeur de Token
	 * @param amount la quantite de Token de depart
	 */
	public Token(int amount)
	{
		this.amount = amount;
	}
	
	/**
	 * Constructeur de Token <br />
	 * quantite par defaut: 0
	 */
	public Token()
	{
		this(0);
	}
	
	/**
	 * Ajoute des Token au montant courant
	 * @param amount le nombre de Token a ajouter
	 */
	public void addToken(int amount) {
		this.amount += amount;
	}
	
	/**
	 * Supprime des Token au montant courant
	 * @param amount le nombre de Token a supprimer
	 */
	public void removeToken(int amount) {
		this.amount -= amount;
	}
	
	/**
	 * @return le montant courant
	 */
	public int getAmount()
	{
		return amount;
	}
	
	/**
	 * Definir le nouveau montant courant
	 * @param amount le nouveau montant
	 */
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
}
