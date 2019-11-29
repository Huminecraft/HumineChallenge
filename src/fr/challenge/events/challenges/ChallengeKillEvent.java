package fr.challenge.events.challenges;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.challenges.Challenge;
import fr.challenge.utils.challenges.ChallengeType;
import fr.challenge.utils.events.ChallengeFinishEvent;

public class ChallengeKillEvent implements Listener{

	@EventHandler
	public void onKillDaily(EntityDeathEvent event) {
		if(event.getEntity().getKiller() != null) {
			Player player = event.getEntity().getKiller();
			Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(player);
			if(challenger != null) {
				List<Challenge> challenges = challenger.getDailyChallenges(ChallengeType.KILL);
				if(!challenges.isEmpty()) {
					for(Challenge c : challenges) {
						if(!c.isFinish()) {
							if(c.checkCondition(event.getEntity())) {
								c.update();
								if(c.isFinish()) {
									ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new ChallengeFinishEvent(c, challenger));
								}
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onKillHebdo(EntityDeathEvent event) {
		if(event.getEntity().getKiller() != null) {
			Player player = event.getEntity().getKiller();
			Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(player);
			if(challenger != null && challenger.hasPremium()) {
				List<Challenge> challenges = challenger.getHebdoChallenges(ChallengeType.KILL);
				if(!challenges.isEmpty()) {
					for(Challenge c : challenges) {
						if(!c.isFinish()) {
							if(c.checkCondition(event.getEntity())) {
								c.update();
								if(c.isFinish()) {
									ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new ChallengeFinishEvent(c, challenger));
								}
							}
						}
					}
				}
			}
		}
	}
}
