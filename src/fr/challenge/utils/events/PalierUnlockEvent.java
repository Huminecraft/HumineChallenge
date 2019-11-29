package fr.challenge.utils.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.challenge.utils.Challenger;
import fr.challenge.utils.pass.Palier;

/**
 * Event permettant de detecter un {@link Palier} debloquer
 * 
 * @author Miza
 */
public class PalierUnlockEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Challenger challenger;
	private Palier palier;

	public PalierUnlockEvent(Challenger challenger, Palier palier) {
		this.challenger = challenger;
		this.palier = palier;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * @return le {@link Palier} debloquer
	 */
	public Palier getPalier() {
		return palier;
	}

	/**
	 * @return Le {@link Challenger} ayant debloquer le {@link Palier}
	 */
	public Challenger getChallenger() {
		return challenger;
	}

}
