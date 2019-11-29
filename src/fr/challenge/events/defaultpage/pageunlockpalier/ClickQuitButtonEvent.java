package fr.challenge.events.defaultpage.pageunlockpalier;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.ItemShop;
import fr.challenge.utils.defaultpage.PageUnlockPalier;

public class ClickQuitButtonEvent implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getView().getTitle().startsWith(PageUnlockPalier.NAME)) {
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
