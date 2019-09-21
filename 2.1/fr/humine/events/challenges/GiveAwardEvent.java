package fr.humine.events.challenges;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Award;
import fr.humine.utils.events.ChallengeFinishEvent;

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
	}
}
