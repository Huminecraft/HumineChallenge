package fr.humine.events.pageapplepay;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.ItemShop;
import fr.humine.utils.PageApplePay;

public class ClickQuitButtonEvent implements Listener{

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getInventory().getName().equals(PageApplePay.NAME)) {
			if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
				if(event.getCurrentItem().isSimilar(ItemShop.itemQuit())) {
					quit((Player) event.getWhoClicked());
				}
			}
			event.setCancelled(true);
		}
	}
	
	private void quit(Player player) {
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(player);
		challenger.getChallengePass().openShop();
	}
	
	
}
