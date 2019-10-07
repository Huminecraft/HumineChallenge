package fr.humine.utils.pass;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Classe contenant un tableau fixe de {@link Palier}
 * 
 * @author Miza
 */
public class Line implements Serializable, Iterator<Palier>, Iterable<Palier> {
	
	private static final long serialVersionUID = 4076514741578193630L;
	private static final byte lineLimit = 9;
	private Palier[] paliers;
	
	private int currentIdx;

	private int currentIteration;
	/**
	 * Constructeur de Line
	 * 
	 * @param paliers tableau predefinis
	 */
	public Line(Palier[] paliers) {
		this.paliers = paliers;
		this.currentIdx = 0;
		this.currentIteration = 0;
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
		return this.currentIdx == 0;
	}

	/**
	 * @return true si le tableau de {@link Palier} est plein, sinon false
	 */
	public boolean isFull() {
		return this.currentIdx == lineLimit;
	}

	/**
	 * Ajoute un {@link Palier} dans le tableau
	 * 
	 * @param palier le palier a ajouter
	 * @return true si il a pu etre ajouter, sinon false
	 */
	public boolean addPalier(Palier palier) {
		if(isFull())
			return false;
		
		this.paliers[currentIdx++] = palier;
		Arrays.sort(this.paliers);
		return true;
	}

	/**
	 * Recupere le {@link Palier} dans un emplacement
	 * 
	 * @param slot l'index
	 * @return le {@link Palier}, sinon null
	 */
	public Palier getPalier(int slot) {
		if (slot < 0 && slot >= this.currentIdx)
			return null;

		return this.paliers[slot];
	}

	/**
	 * Permet de supprimer un {@link Palier} dans un emplacement
	 * 
	 * @return le {@link Palier} supprimer, sinon null
	 */
	public Palier removeLastPalier() {
		if (isEmpty())
			return null;
		
		Palier p = this.paliers[this.currentIdx-1];
		this.paliers[this.currentIdx--] = null;
		return p;
	}
	
	/**
	 * Permet de supprimer un {@link Palier} dans un emplacement
	 * 
	 * @param slot l'index de la ou il faut supprimer
	 * @return le {@link Palier} supprimer, sinon null
	 */
	public Palier remove(int slot) {
		if(slot < 0 || slot >= this.currentIdx)
			return null;
		
		Palier p = this.paliers[slot];
		this.paliers[slot] = this.paliers[this.currentIdx-1];
		this.paliers[this.currentIdx-1] = null;
		Arrays.sort(this.paliers);
		return p;
	}

	/**
	 * Verifie si le tableau contient un {@link Palier} precis
	 * 
	 * @param palier le palier a verifier
	 * @return true si trouver, sinon false
	 */
	public boolean contains(Palier palier) {
		return indexOf(palier) != -1;
	}
	
	/**
	 * Permet de detecter a quel index se trouve un Palier dans le tableau de {@link Palier}
	 * @param palier le palier a chercher
	 * @return l'index du Palier, -1 si introuvable
	 */
	public int indexOf(Palier palier) {
		int i;
		for(i = 0; i < this.currentIdx && !this.paliers[i].equals(palier); i++);
		return this.paliers[i].equals(palier) ? i : -1;
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

	@Override
	public Iterator<Palier> iterator() {
		return new Line(paliers);
	}

	@Override
	public boolean hasNext() {
		return this.currentIteration < this.currentIdx;
	}

	@Override
	public Palier next() {
		return this.paliers[this.currentIteration++];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentIdx;
		result = prime * result + Arrays.hashCode(paliers);
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
		Line other = (Line) obj;
		if (currentIdx != other.currentIdx)
			return false;
		if (!Arrays.equals(paliers, other.paliers))
			return false;
		return true;
	}
	
}
