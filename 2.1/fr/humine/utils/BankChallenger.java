package fr.humine.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class BankChallenger {

	private List<Challenger> challengers;

	public BankChallenger(List<Challenger> challengers) {
		this.challengers = challengers;
	}
	
	public BankChallenger() {
		this(new ArrayList<Challenger>());
	}
	
	public boolean addChallenger(Challenger challenger) {
		return this.challengers.add(challenger);
	}
	
	public boolean removeChallenger(Challenger challenger) {
		return this.challengers.remove(challenger);
	}
	
	public boolean contains(Player player) {
		for(Challenger challenger : this.challengers) {
			if(challenger.getName().equals(player.getName()))
				return true;
		}
		return false;
	}
	
	public Challenger getChallenger(Player player) {
		for(Challenger c : this.challengers) {
			if(c.getName().equals(player.getName()))
				return c;
		}
		return null;
	}
	
	public Challenger getChallenger(String playerName) {
		for(Challenger c : this.challengers) {
			if(c.getName().equals(playerName))
				return c;
		}
		return null;
	}
}
