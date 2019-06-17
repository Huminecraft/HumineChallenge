package fr.humine.events.challengeshop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.humine.ChallengeMain;
import fr.humine.utils.shop.ItemShop;

public class QuitButtonEvent implements Listener{

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getInventory().getName().equals(ChallengeMain.getInstance().getBankChallengeShop().getDefaultChallengeShop().getName())) {
			if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
				if(event.getCurrentItem().isSimilar(ItemShop.itemQuit())) {
					ChallengeMain.getInstance().getBankChallengeShop().closeShop((Player) event.getWhoClicked());
				}
			}
		}
	}
}
