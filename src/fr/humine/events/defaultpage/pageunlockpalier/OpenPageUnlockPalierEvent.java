package fr.humine.events.defaultpage.pageunlockpalier;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.defaultpage.PageUnlockPalier;
import fr.humine.utils.pass.ChallengePass;
import fr.humine.utils.pass.Page;
import fr.humine.utils.pass.Palier;

public class OpenPageUnlockPalierEvent implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getView().getTitle().startsWith(ChallengePass.SHOPNAME)) {
			if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
				if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Palier")) {
					Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger((Player) event.getWhoClicked());
					Palier palier[] = getPalier(challenger, event.getCurrentItem(), Integer.parseInt(event.getCurrentItem().getItemMeta().getDisplayName().split(" ")[1]));
					if(palier[0] != null && palier[1] != null && (palier[0].isUnlock() && !palier[1].isUnlock())) {
						PageUnlockPalier.openShop(challenger, palier[1]);
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
