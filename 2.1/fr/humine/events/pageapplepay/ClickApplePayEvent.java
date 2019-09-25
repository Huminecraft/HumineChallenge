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
import humine.main.MainShop;

public class ClickApplePayEvent implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getInventory().getName().equals(PageApplePay.NAME)) {
			if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
				Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger((Player) event.getWhoClicked());
				if(event.getCurrentItem().isSimilar(ItemShop.applePay(challenger, PageApplePay.PRIZE))) {
					if(!challenger.hasPremium()) {
						buy(challenger);
					}
				}
			}
			event.setCancelled(true);
		}
	}

	private void buy(Challenger challenger) {
		if(MainShop.getInstance().getBankHumis().getMoney(challenger.getPlayer()) >= PageApplePay.PRIZE) {
			challenger.getChallengePass().closeShop();
			ChallengeMain.sendMessage(challenger.getPlayer(), "Vous avez acheter le HuminePass, Bienvenue dans l'elite !");
			challenger.setPremium(true);
		}
		else {
			ChallengeMain.sendMessage(challenger.getPlayer(), "Vous n'avez pas assez de humis :(");
		}
	}

}
