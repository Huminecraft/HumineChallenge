package fr.humine.events.challengeshop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import fr.humine.ChallengeMain;

public class CloseChallengeShopEvent implements Listener{

	@EventHandler
	public void onQuit(InventoryCloseEvent event) {
		if(event.getInventory().getName().equals(ChallengeMain.getInstance().getBankChallengeShop().getDefaultChallengeShop().getName())) {
			ChallengeMain.getInstance().getBankChallengeShop().closeShop((Player) event.getPlayer());
		}
	}
}
