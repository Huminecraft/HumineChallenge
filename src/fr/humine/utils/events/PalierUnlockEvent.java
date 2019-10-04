package fr.humine.utils.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.humine.utils.Challenger;
import fr.humine.utils.pass.Palier;

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
