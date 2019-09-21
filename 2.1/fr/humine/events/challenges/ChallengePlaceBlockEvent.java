package fr.humine.events.challenges;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;
import fr.humine.utils.challenges.ChallengeType;
import fr.humine.utils.events.ChallengeFinishEvent;

public class ChallengePlaceBlockEvent implements Listener{

	@EventHandler
	public void onPose(BlockPlaceEvent event) {
		if(event.getPlayer() == null)
			return;
		
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(event.getPlayer());
		List<Challenge> challenges = challenger.getDailyChallenges(ChallengeType.PLACE_BLOCK);
		if(!challenges.isEmpty()) {
			for(Challenge c : challenges) {
				if(!c.isFinish()) {
					if(c.checkCondition(event.getItemInHand())) {
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
