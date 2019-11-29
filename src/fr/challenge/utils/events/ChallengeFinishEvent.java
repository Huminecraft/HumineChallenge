package fr.challenge.utils.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.challenge.utils.Challenger;
import fr.challenge.utils.challenges.Challenge;

/**
 * Event permettant de detecter un {@link Challenge} termine
 * 
 * @author Miza
 */
public class ChallengeFinishEvent extends Event {

	private static final HandlerList handlers = new HandlerList();;
	private Challenge challenge;
	private Challenger challenger;

	public ChallengeFinishEvent(Challenge challenge, Challenger challenger) {
		this.challenge = challenge;
		this.challenger = challenger;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * @return Le {@link Challenger} ayant termine le {@link Challenge}
	 */
	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	/**
	 * @return le {@link Challenge} termine
	 */
	public Challenger getChallenger() {
		return challenger;
	}

	public void setChallenger(Challenger challenger) {
		this.challenger = challenger;
	}

}
