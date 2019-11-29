package fr.challenge.events.defaultpage.pageunlockpalier;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.defaultpage.PageUnlockPalier;
import fr.challenge.utils.pass.ChallengePass;
import fr.challenge.utils.pass.Page;
import fr.challenge.utils.pass.Palier;
import humine.utils.cosmetiques.AbstractParticleCosmetique;

public class OpenPageUnlockPalierEvent implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getView().getTitle().startsWith(ChallengePass.SHOPNAME)) {
			if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
				if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Palier")) {
					Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger((Player) event.getWhoClicked());
					Palier palier[] = getPalier(challenger, event.getCurrentItem(), Integer.parseInt(event.getCurrentItem().getItemMeta().getDisplayName().split(" ")[1]));
					if(palier[1] != null) {
						if(palier[1].getCosmetique() != null && palier[1].getCosmetique() instanceof AbstractParticleCosmetique) {
							if(palier[0] != null) {
								if(palier[0].isUnlock()) {
									if(!palier[1].isUnlock()) {
										PageUnlockPalier.openShop(challenger, palier[1], true, true);
										return;
									}
								}
							}
							PageUnlockPalier.openShop(challenger, palier[1], false, true);
						}
						else {
							if(palier[0] != null && palier[0].isUnlock() && !palier[1].isUnlock())
								PageUnlockPalier.openShop(challenger, palier[1], true, false);
						}
					}
				}
			}
		}
	}

	private Palier[] getPalier(Challenger challenger, ItemStack item, int numeroPalier) {
		Palier palier[] = new Palier[2];
		
		if(numeroPalier <= 1)
			return null;
		
		for(Page page : challenger.getChallengePass().getPages()) {
			for(Palier p : page.getFreeLine().getPaliers()) {
				if(p != null && p.getNumeroPalier() == (numeroPalier -1))
					palier[0] = p;
				else if(p != null && p.getNumeroPalier() == numeroPalier) {
					palier[1] = p;
					return palier;
				}
			}
		}
		return null;
	}
	
}
