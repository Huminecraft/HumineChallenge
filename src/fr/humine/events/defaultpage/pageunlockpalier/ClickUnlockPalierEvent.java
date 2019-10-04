package fr.humine.events.defaultpage.pageunlockpalier;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.ItemShop;
import fr.humine.utils.defaultpage.PageUnlockPalier;
import fr.humine.utils.events.PalierUnlockEvent;
import fr.humine.utils.pass.Page;
import fr.humine.utils.pass.Palier;
import humine.main.MainShop;

public class ClickUnlockPalierEvent implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getView().getTitle().startsWith(PageUnlockPalier.NAME)) {
			if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
				Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger((Player) event.getWhoClicked());
				Palier palier = getPalier(challenger, event.getView().getTitle());
				if(event.getCurrentItem().isSimilar(ItemShop.palierPay(challenger.getPlayer(), palier))) {
					buy(challenger, palier);
				}
			}
			event.setCancelled(true);
		}
	}
	
	private void buy(Challenger player, Palier palier) {
		int humis = MainShop.getInstance().getBankHumis().getMoney(player.getPlayer());
		if(humis >= palier.getHumis()) {
			player.getPlayer().closeInventory();
			MainShop.getInstance().getBankHumis().removeMoney(player.getPlayer(), palier.getHumis());
			palier.setUnlock(true);
			player.getToken().setAmount(palier.getTokenPass());
			ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new PalierUnlockEvent(player, palier));

			if(player.hasPremium()) {
				Palier palierPremium = getPremiumPalier(player, palier.getNumeroPalier());
				if(palierPremium != null) {
					palierPremium.setUnlock(true);
					ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new PalierUnlockEvent(player, palierPremium));
				}
			}
		}
		else {
			ChallengeMain.sendMessage(player.getPlayer(), "Vous n'avez pas assez de Humis");
		}
	}

	private Palier getPalier(Challenger challenger, String name)
	{
		int numeroPalier = Integer.parseInt(name.split(" ")[2]);
		for(Page page : challenger.getChallengePass().getPages()) {
			for(Palier p : page.getFreeLine().getPaliers()) {
				if(p != null && p.getNumeroPalier() == numeroPalier)
					return p;
			}
		}
		return null;
	}
	
	private Palier getPremiumPalier(Challenger challenger, int num)
	{
		for(Page page : challenger.getChallengePass().getPages()) {
			for(Palier p : page.getPremiumLine().getPaliers()) {
				if(p != null && p.getNumeroPalier() == num)
					return p;
			}
		}
		return null;
	}

}
