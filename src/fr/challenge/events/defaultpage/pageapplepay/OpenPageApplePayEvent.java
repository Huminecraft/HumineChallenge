package fr.challenge.events.defaultpage.pageapplepay;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.ItemShop;
import fr.challenge.utils.defaultpage.PageApplePay;
import fr.challenge.utils.pass.ChallengePass;

public class OpenPageApplePayEvent implements Listener{

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getView().getTitle().startsWith(ChallengePass.SHOPNAME)) {
			if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
				if(event.getCurrentItem().isSimilar(ItemShop.premiumApple())) {
					Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger((Player) event.getWhoClicked());
					challenger.getChallengePass().closeShop();
					PageApplePay.openShop(challenger);
				}
			}
			event.setCancelled(true);
		}
		
	}
}
