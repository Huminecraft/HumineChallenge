package fr.challenge.events.challenges;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.challenges.Challenge;
import fr.challenge.utils.challenges.ChallengeType;
import fr.challenge.utils.events.ChallengeFinishEvent;
import fr.challenge.utils.events.PlayerChangeBiomeEvent;

public class ChallengeBiomeDiscoverEvent implements Listener{

	@EventHandler
	public void onChangeDaily(PlayerChangeBiomeEvent event) {
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(event.getPlayer());
		List<Challenge> challenges = challenger.getDailyChallenges(ChallengeType.BIOME_DISCOVER);
		
		if(challenger.hasPremium())
			challenges.addAll(challenger.getHebdoChallenges(ChallengeType.BIOME_DISCOVER));
		
		if(!challenges.isEmpty()) {
			for(Challenge c : challenges) {
				if(!c.isFinish()) {
					if(c.checkCondition(event.fromBiome()) || c.checkCondition(event.toBiome())) {
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
