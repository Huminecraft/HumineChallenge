package fr.challenge.events.defaultpage.pageapplepay;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.ItemShop;
import fr.challenge.utils.events.PalierUnlockEvent;
import fr.challenge.utils.menu.MenuApplePay;
import fr.challenge.utils.pass.Page;
import fr.challenge.utils.pass.Palier;
import humine.main.MainShop;
import humine.utils.Shopper;

public class ClickApplePayEvent implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getView().getTitle().equals(MenuApplePay.NAME)) {
			if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
				Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger((Player) event.getWhoClicked());
				if(event.getCurrentItem().isSimilar(ItemShop.applePay(challenger, MenuApplePay.PRIZE))) {
					if(!challenger.hasPremium()) {
						buy(challenger);
					}
				}
			}
			event.setCancelled(true);
		}
	}

	private void buy(Challenger challenger) {
		Shopper shopper = MainShop.getShopperManager().getShopper(challenger.getPlayer());
		if(shopper.getHumis().getAmount() >= MenuApplePay.PRIZE) {
			challenger.getChallengePass().closeShop();
			ChallengeMain.sendMessage(challenger.getPlayer(), "Vous avez acheter le HuminePass, Bienvenue dans l'elite !");
			challenger.setPremium(true);
			challenger.updateHebdoChallenge(ChallengeMain.getHebdoChallenge());
			checkPalier(challenger);
		}
		else {
			ChallengeMain.sendMessage(challenger.getPlayer(), "Vous n'avez pas assez de humis :(");
		}
	}
	
	private void checkPalier(Challenger challenger) {
		for(Page page : challenger.getChallengePass().getPages()) {
			for(Palier palier : page.getPremiumLine().getPaliers()) {
				if(palier != null) {
					if(!palier.isUnlock() && challenger.getToken().getAmount() >= palier.getTokenPass()) {
						palier.setUnlock(true);
						ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new PalierUnlockEvent(challenger, palier));
					}
				}
			}
		}
	}

}
