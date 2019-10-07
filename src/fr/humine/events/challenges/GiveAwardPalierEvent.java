package fr.humine.events.challenges;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.events.PalierUnlockEvent;
import humine.main.MainShop;
import humine.utils.cosmetiques.Cosmetique;

public class GiveAwardPalierEvent implements Listener
{

	@EventHandler
	public void onGive(PalierUnlockEvent event) {
		ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Palier " + event.getPalier().getNumeroPalier() + " debloque !");
		Cosmetique c = event.getPalier().getCosmetique();
		if(c != null) {
			MainShop.getInstance().getInventories().getStockOfPlayer(event.getChallenger().getPlayer().getName()).addCosmetique(c);
			ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Cosmetique Debloquer !");
			ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Cosmetique : " + c.getName());
		}
	}
}
