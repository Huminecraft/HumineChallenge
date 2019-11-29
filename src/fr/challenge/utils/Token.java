package fr.challenge.utils;

import fr.challenge.utils.pass.Palier;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (amount != other.amount)
			return false;
		return true;
	}
	
	
}
