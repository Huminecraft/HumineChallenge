package fr.challenge.events.challenges;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.challenges.Challenge;
import fr.challenge.utils.challenges.ChallengeType;
import fr.challenge.utils.events.ChallengeFinishEvent;

public class ChallengeFishEvent implements Listener{

	@EventHandler
	public void onFish(PlayerFishEvent event) {
		if(event.getState() == State.CAUGHT_ENTITY || event.getState() == State.CAUGHT_FISH) {
			Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(event.getPlayer());
			List<Challenge> challenges = challenger.getDailyChallenges(ChallengeType.FISH);
			if(!challenges.isEmpty()) {
				for(Challenge c : challenges) {
					if(!c.isFinish()) {
						if(c.checkCondition(event.getCaught())) {
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
