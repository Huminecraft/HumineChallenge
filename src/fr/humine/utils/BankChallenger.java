package fr.humine.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

/**
 * Permet de stocker les challengers
 * 
 * @author Miza
 */
public class BankChallenger {

	private Map<Player, Challenger> challengers;

	/**
	 * Constructeur de BankChallenge
	 * 
	 * @param challengers une liste deja existante
	 */
	public BankChallenger(Map<Player, Challenger> challengers) {
		this.challengers = challengers;
	}

	/**
	 * Constructeur de BankChallenge
	 */
	public BankChallenger() {
		this(new HashMap<Player, Challenger>());
	}

	/**
	 * Permet d'ajouter un nouveau challenger
	 * 
	 * @param challenger le nouveau challenger
	 * @return true si insere, sinon false
	 */
	public Challenger addChallenger(Challenger challenger) {
		return this.challengers.put(challenger.getPlayer(), challenger);
	}

	/**
	 * Permet de supprimer les challegers
	 * 
	 * @param challenger le challenger a supprimer
	 * @return true si supprime, sinon false
	 */
	public Challenger removeChallenger(Challenger challenger) {
		return this.challengers.remove(challenger.getPlayer());
	}

	/**
	 * Verifie si le challenger est existant
	 * 
	 * @param player le joueur a verifier
	 * @return true si la liste contient le joueur, sinon false
	 */
	public boolean contains(Player player) {
		return this.challengers.containsKey(player);
	}

	/**
	 * Recupere le compte Challenger du joueur
	 * 
	 * @param player le joueur lier au compte
	 * @return Challenger du joueur, null si non trouve
	 */
	public Challenger getChallenger(Player player) {
		return this.challengers.get(player);
	}

	/**
	 * @return la liste des challengers
	 */
	public Collection<Challenger> getChallengers() {
		return challengers.values();
	}
	
	/**
	 * @return la liste des joueurs
	 */
	public Set<Player> getPlayers() {
		return challengers.keySet();
	}
	
	/**
	 * @return la hashmap cle: Player, valeur: Challenger
	 */
	public Map<Player, Challenger> getMapChallengers() {
		return challengers;
	}
}
