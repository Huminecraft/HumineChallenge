package fr.humine.utils;

import java.util.ArrayList;
import java.util.List;

import humine.utils.cosmetiques.Cosmetique;

/**
 * Permet de stocker les cosmetiques lies au plugin HumineChallenge
 * et qui sont utilises pour les paliers
 * @author Miza
 */
public class BankCosmetique {

	private List<Cosmetique> cosmetiques;

	/**
	 * Constructeur de BankCosmetique
	 * @param cosmetiques liste deja construite de {@link Cosmetique}
	 */
	public BankCosmetique(List<Cosmetique> cosmetiques) {
		this.cosmetiques = cosmetiques;
	}
	
	/**
	 * Constructeur de BankCosmetique
	 * Creer une nouvelle liste de {@link Cosmetique}
	 */
	public BankCosmetique() {
		this(new ArrayList<Cosmetique>());
	}
	
	/**
	 * Permet d'ajouter un {@link Cosmetique}
	 * @param cosmetiques le cosmetique a ajouter
	 * @return true si il a pu etre ajouter, sinon false
	 */
	public boolean addCosmetique(Cosmetique cosmetiques) {
		return this.cosmetiques.add(cosmetiques);
	}
	
	/**
	 * Permet de supprimer un {@link Cosmetique}
	 * @param cosmetiques le cosmetique a supprimer
	 * @return true si supprimer, sinon false
	 */
	public boolean removeCosmetique(Cosmetique cosmetiques) {
		return this.cosmetiques.remove(cosmetiques);
	}
	
	/**
	 * Verifie si la liste contient un certains {@link Cosmetique}
	 * @param id l'id du cosmetique
	 * @return true si la liste contient le cosmetique, sinon false
	 */
	public boolean contains(String id) {
		for(Cosmetique c : this.cosmetiques) {
			if(c.getId().equals(id))
				return true;
		}
		return false;
	}
	
	/**
	 * @param id l'id du cosmetique
	 * @return le cosmetique, null si inexistant
	 */
	public Cosmetique getCosmetique(String id) {
		for(Cosmetique c : this.cosmetiques) {
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}
	
	/**
	 * @return la liste des cosmetiques
	 */
	public List<Cosmetique> getCosmetiques() {
		return cosmetiques;
	}
	
	/**
	 * Permet de definir une liste de cosmetique
	 * @param cosmetiques la nouvelle liste
	 */
	public void setCosmetiques(List<Cosmetique> cosmetiques) {
		this.cosmetiques = cosmetiques;
	}
}
