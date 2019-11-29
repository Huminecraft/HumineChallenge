package fr.challenge.events.challenges;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.events.PalierUnlockEvent;
import humine.main.MainShop;
import humine.utils.Shopper;
import humine.utils.cosmetiques.Cosmetique;

public class GiveAwardPalierEvent implements Listener
{

	@EventHandler
	public void onGive(PalierUnlockEvent event) {
		ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Palier " + event.getPalier().getNumeroPalier() + " debloque !");
		Cosmetique c = event.getPalier().getCosmetique();
		
		Shopper shopper = MainShop.getShopperManager().getShopper(event.getChallenger().getPlayer());
		shopper.getHumis().addAmount(event.getPalier().getAwardHumis());
		shopper.getPixel().addAmount(event.getPalier().getAwardPixel());
		
		event.getChallenger().getLevel().addExperience(event.getPalier().getAwardExp());
		ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "===PALIER DEBLOQUE !===");
		ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Humis gagne : " + event.getPalier().getAwardHumis());
		ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Pixel gagne : " + event.getPalier().getAwardPixel());
		ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Experience gagne : " + event.getPalier().getAwardExp());
		
		if(c != null) {
			shopper.getCosmetiques().add(c);
			ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Cosmetique Debloquer !");
			ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Cosmetique : " + c.getName());
		}
	}
}
