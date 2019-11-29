package fr.challenge.utils.challenges;

import java.io.Serializable;

import fr.challenge.utils.Token;

/**
 * Classe contenant les recompenses pour un {@link Challenge}
 * 
 * @author Miza
 */
public class Award implements Serializable {

	private static final long serialVersionUID = 1551445127015996475L;
	private int exp;
	private int token;

	/**
	 * Constructeur de Award
	 * 
	 * @param exp   nombre d'experience a gagner
	 * @param token nombre de {@link Token} a gagner
	 */
	public Award(int exp, int token) {
		this.exp = exp;
		this.token = token;
	}

	/**
	 * @return l'experience a gagner dans la recompense
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * @return le nombre de {@link Token} a gagner dans la recompense
	 */
	public int getToken() {
		return token;
	}
}
