package fr.challenge.events.defaultpage.hebdopage;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.challenge.utils.ItemShop;
import fr.challenge.utils.defaultpage.PageHebo;

public class ClickQuitButtonEvent implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getView().getTitle().equals(PageHebo.NAME)) {
			if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
				if(event.getCurrentItem().isSimilar(ItemShop.itemQuit())) {
					quit((Player) event.getWhoClicked());
				}
			}
			event.setCancelled(true);
		}
	}
	
	private void quit(Player player) {
		player.closeInventory();
	}
}
