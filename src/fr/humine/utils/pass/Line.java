package fr.humine.utils.pass;

import java.io.Serializable;

/**
 * Classe contenant un tableau fixe de {@link Palier}
 * 
 * @author Miza
 */
public class Line implements Serializable {
	private static final long serialVersionUID = 4076514741578193630L;
	private static final byte lineLimit = 9;
	private Palier[] paliers;

	/**
	 * Constructeur de Line
	 * 
	 * @param paliers tableau predefinis
	 */
	public Line(Palier[] paliers) {
		this.paliers = paliers;
	}

	/**
	 * Constructeur de Line
	 */
	public Line() {
		this(new Palier[lineLimit]);
	}

	/**
	 * @return true si le tableau de {@link Palier} est vide, sinon false
	 */
	public boolean isEmpty() {
		for (int i = 0; i < this.paliers.length; i++) {
			if (this.paliers[i] != null)
				return false;
		}
		return true;
	}

	/**
	 * @return true si le tableau de {@link Palier} est plein, sinon false
	 */
	public boolean isFull() {
		for (int i = 0; i < this.paliers.length; i++) {
			if (this.paliers[i] == null)
				return false;
		}
		return true;
	}

	/**
	 * Ajoute un {@link Palier} dans le tableau
	 * 
	 * @param palier le palier a ajouter
	 * @return true si il a pu etre ajouter, sinon false
	 */
	public boolean addPalier(Palier palier) {
		for (int i = 0; i < this.paliers.length; i++) {
			if (this.paliers[i] == null) {
				this.paliers[i] = palier;
				return true;
			}
		}
		return false;

	}

	/**
	 * Placer un {@link Palier} dans un emplacement precis
	 * 
	 * @param palier le palier a ajouter
	 * @param slot   l'index
	 * @return true si le {@link Palier} est ajouter, sinon false
	 */
	public boolean setPalier(Palier palier, int slot) {
		if (slot < 0 && slot >= this.paliers.length)
			return false;

		this.paliers[slot] = palier;
		return true;
	}

	/**
	 * Recupere le {@link Palier} dans un emplacement
	 * 
	 * @param slot l'index
	 * @return le {@link Palier}, sinon null
	 */
	public Palier getPalier(int slot) {
		if (slot < 0 && slot >= this.paliers.length)
			return null;

		return this.paliers[slot];
	}

	/**
	 * Permet de supprimer un {@link Palier} dans un emplacement
	 * 
	 * @param slot l'index
	 * @return si le {@link Palier} est supprimer, sinon false
	 */
	public boolean removePalier(int slot) {
		if (slot < 0 && slot >= this.paliers.length)
			return false;

		this.paliers[slot] = null;
		return true;
	}

	/**
	 * Verifie si le tableau contient un {@link Palier} precis
	 * 
	 * @param palier le palier a verifier
	 * @return true si trouver, sinon false
	 */
	public boolean contains(Palier palier) {
		for (int i = 0; i < this.paliers.length; i++) {
			if (this.paliers[i].equals(palier))
				return true;
		}
		return false;
	}

	/**
	 * @return le tableau de {@link Palier}
	 */
	public Palier[] getPaliers() {
		return paliers;
	}

	@Override
	public String toString() {
		String str = "[Line: \n";
		for (Palier p : this.paliers) {
			if (p != null)
				str += "===>" + p.toString() + "\n";
			else
				str += "===>NULL\n";
		}
		return str + "]";
	}

	/**
	 * Permet de cloner la Line tout en conservant les meme donnees
	 * 
	 * @return une nouvelle instance de la Line
	 */
	public Line clonage() {
		Palier paliers[] = new Palier[lineLimit];
		int i = 0;
		for (Palier p : this.paliers)
			paliers[i++] = (p != null) ? p.clonage() : null;
		Line line = new Line(paliers);
		return line;
	}
}
