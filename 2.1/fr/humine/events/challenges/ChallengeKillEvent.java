package fr.humine.events.challenges;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;
import fr.humine.utils.challenges.ChallengeType;
import fr.humine.utils.events.ChallengeFinishEvent;

public class ChallengeKillEvent implements Listener{

	@EventHandler
	public void onKill(EntityDeathEvent event) {
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
}
