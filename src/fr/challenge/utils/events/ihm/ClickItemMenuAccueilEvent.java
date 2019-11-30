package fr.challenge.utils.events.ihm;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import fr.challenge.utils.Challenger;

public class ClickItemMenuAccueilEvent extends Event implements Cancellable{

	private static final HandlerList handlers = new HandlerList();
	
	private Inventory inv;
	private InventoryView view;
	private Challenger challenger;
	private ItemStack item;
	private boolean cancel;
	
	public ClickItemMenuAccueilEvent(Inventory inv, InventoryView view, Challenger challenger, ItemStack item) {
		this.inv = inv;
		this.view = view;
		this.challenger = challenger;
		this.item = item;
		this.cancel = false;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public Inventory getInventory() {
		return inv;
	}
	
	public InventoryView getView() {
		return view;
	}
	
	public Challenger getChallenger() {
		return challenger;
	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
