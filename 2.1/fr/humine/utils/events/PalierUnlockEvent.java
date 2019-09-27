package fr.humine.utils.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.humine.utils.Challenger;
import fr.humine.utils.pass.Palier;

public class PalierUnlockEvent extends Event
{

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

	public Palier getPalier()
	{
		return palier;
	}
	
	public Challenger getChallenger()
	{
		return challenger;
	}
	
}
