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
import fr.humine.utils.pass.ChallengePass;

public class OpenPageApplePayEvent implements Listener{

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getInventory().getName().equals(ChallengePass.SHOPNAME)) {
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
