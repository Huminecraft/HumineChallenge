package fr.challenge.utils.challenges;

import java.io.Serializable;

import fr.challenge.utils.Challenger;

/**
 * Classe permettant de creer des Challenges avec le plugin HumineChallenge
 * (tout est dans le nom :3) <br />
 * Les Challenges sont {@link Serializable}
 * @author Miza
 */
public interface Challenge extends Serializable, Cloneable{

	/**
	 * Contient le titre du Challenge
	 * @return le titre
	 */
	public String getTitle();
	
	/**
	 * Contient la description du Challenge (peut etre vide)
	 * @return la description
	 */
	public String getDescription();
	
	/**
	 * Le type de Challenge
	 * @return son type
	 */
	public ChallengeType getType();
	
	/**
	 * Permet de definir si le challenge est premium (donc reserver au premium)
	 * @param premium definir true ou false 
	 */
	public void setPremium(boolean premium);
	
	/**
	 * @return true si le Challenge est premium, sinon false
	 */
	public boolean isPremium();
	
	/**
	 * Permet de savoir si le challenge est termine
	 * @return true si il est fini, sinon false
	 */
	public boolean isFinish();
	
	/**
	 * Permet d'actualiser les donnees du Challenge <br />
	 * Exemple: il est a 0 / 10 | update() | 1 / 10 <br />
	 * Exemple: decouvert: False | update() | decouvert: True
	 */
	public void update();
	
	/**
	 * Permet de verifier si une action respecte les conditions du Challenge
	 * @param o l'element a verifier
	 * @return true si la condition est respecter, sinon false
	 */
	public boolean checkCondition(Object o);
	
	/**
	 * Permet d'afficher les informations du Challenge a un {@link Challenger}
	 * @param challenger la cible 
	 */
	public void showChallenge(Challenger challenger);
	
	/**
	 * Recupere la recompense liee au Challenge
	 * @return un {@link Award}
	 */
	public Award getAwards();
	
	/**
	 * Definir une nouvelle recompense au Challenge
	 * @param award la nouvelle recompense
	 */
	public void setAward(Award award);

	Object clone() throws CloneNotSupportedException;
//	/**
//	 * Permet de cloner le challenge tout en conservant les meme donnees
//	 * 
//	 * @return une nouvelle instance du Challenge
//	 */
//	public Challenge clonage();
}
