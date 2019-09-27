package fr.humine.events.challenges;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Award;
import fr.humine.utils.events.ChallengeFinishEvent;
import fr.humine.utils.events.PalierUnlockEvent;
import fr.humine.utils.pass.Page;
import fr.humine.utils.pass.Palier;

public class GiveAwardEvent implements Listener{

	@EventHandler
	public void onFinish(ChallengeFinishEvent event) {
		Challenger challenger = event.getChallenger();
		Award award = event.getChallenge().getAwards();
		
		challenger.getLevel().addExperience(award.getExp());
		challenger.getToken().addToken(award.getToken());
		
		challenger.getLevelBar().update();
		
		ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Challenge " + event.getChallenge().getTitle() + " Termine !");
		ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Experience gagnee: " + award.getExp());
		ChallengeMain.sendMessage(event.getChallenger().getPlayer(), "Token gagne: " + award.getToken());
		
		checkPalier(challenger);
	}
	
	public void checkPalier(Challenger challenger) {
		for(Page page : challenger.getChallengePass().getPages()) {
			for(Palier palier : page.getFreeLine().getPaliers()) {
				if(palier != null) {
					if(!palier.isUnlock() && challenger.getToken().getAmount() >= palier.getTokenPass()) {
						palier.setUnlock(true);
						ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new PalierUnlockEvent(challenger, palier));
					}
				}
			}
		}
		
		if(challenger.hasPremium()) {
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
}
