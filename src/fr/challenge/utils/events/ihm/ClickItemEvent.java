package fr.challenge.utils.events.ihm;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import fr.challenge.utils.Challenger;

public class ClickItemEvent extends Event implements Cancellable
{

	private static HandlerList handler = new HandlerList();
	private boolean cancel;
	
	private Challenger challenger;
	private Inventory inv;
	private ItemStack item;
	private InventoryView view;
	
	public ClickItemEvent(Challenger challenger, Inventory inv, InventoryView view, ItemStack item)
	{
		this.challenger = challenger;
		this.inv = inv;
		this.item = item;
		this.view = view;
		this.cancel = false;
	}
	
	public Challenger getChallenger() {
		return challenger;
	}
	
	public Inventory getInventory()
	{
		return inv;
	}
	public ItemStack getItem()
	{
		return item;
	}
	
	public InventoryView getView()
	{
		return view;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handler;
	}
	
	public static HandlerList getHandlerList() {
		return handler;
	}

	@Override
	public boolean isCancelled()
	{
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel)
	{
		this.cancel = cancel;
	}
}
