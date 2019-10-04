package fr.humine.utils.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;

public class ChallengeFinishEvent extends Event{

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

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	public Challenger getChallenger() {
		return challenger;
	}

	public void setChallenger(Challenger challenger) {
		this.challenger = challenger;
	}

}
