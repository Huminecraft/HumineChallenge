package fr.humine.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

/**
 * Permet de stocker les challengers
 * 
 * @author Miza
 */
public class BankChallenger {

	private List<Challenger> challengers;

	/**
	 * Constructeur de BankChallenge
	 * 
	 * @param challengers une liste deja existante
	 */
	public BankChallenger(List<Challenger> challengers) {
		this.challengers = challengers;
	}

	/**
	 * Constructeur de BankChallenge
	 */
	public BankChallenger() {
		this(new ArrayList<Challenger>());
	}

	/**
	 * Permet d'ajouter un nouveau challenger
	 * 
	 * @param challenger le nouveau challenger
	 * @return true si insere, sinon false
	 */
	public boolean addChallenger(Challenger challenger) {
		return this.challengers.add(challenger);
	}

	/**
	 * Permet de supprimer les challegers
	 * 
	 * @param challenger le challenger a supprimer
	 * @return true si supprime, sinon false
	 */
	public boolean removeChallenger(Challenger challenger) {
		return this.challengers.remove(challenger);
	}

	/**
	 * Verifie si le challenger est existant
	 * 
	 * @param player le joueur a verifier
	 * @return true si la liste contient le joueur, sinon false
	 */
	public boolean contains(Player player) {
		for (Challenger challenger : this.challengers) {
			if (challenger.getName().equals(player.getName()))
				return true;
		}
		return false;
	}

	/**
	 * Recupere le compte Challenger du joueur
	 * 
	 * @param player le joueur lier au compte
	 * @return Challenger du joueur, null si non trouve
	 */
	public Challenger getChallenger(Player player) {
		for (Challenger c : this.challengers) {
			if (c.getName().equals(player.getName()))
				return c;
		}
		return null;
	}

	/**
	 * Recupere le compte Challenger du joueur
	 * 
	 * @param id l'id unique du joueur
	 * @return Challenger du joueur, null si non trouve
	 */
	public Challenger getChallenger(UUID id) {
		for (Challenger c : this.challengers) {
			if (c.getPlayer().getUniqueId().equals(id))
				return c;
		}
		return null;
	}

	/**
	 * @return la liste des challengers
	 */
	public List<Challenger> getChallengers() {
		return challengers;
	}
}
