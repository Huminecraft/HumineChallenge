package fr.challenge.events.challenges;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.challenges.Challenge;
import fr.challenge.utils.challenges.ChallengeType;
import fr.challenge.utils.events.ChallengeFinishEvent;

public class ChallengeBreakBlockEvent implements Listener {

	@EventHandler
	public void onPoseDaily(BlockBreakEvent event) {
		if(event.getPlayer() == null)
			return;
		
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(event.getPlayer());
		List<Challenge> challenges = challenger.getDailyChallenges(ChallengeType.BREAK_BLOCK);
		if(!challenges.isEmpty()) {
			for(Challenge c : challenges) {
				if(!c.isFinish()) {
					if(c.checkCondition(event.getBlock().getType())) {
						c.update();
						if(c.isFinish()) {
							ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new ChallengeFinishEvent(c, challenger));
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPoseHebdo(BlockBreakEvent event) {
		if(event.getPlayer() == null)
			return;
		
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(event.getPlayer());
		if(challenger.hasPremium()) {
			List<Challenge> challenges = challenger.getHebdoChallenges(ChallengeType.BREAK_BLOCK);
			if(!challenges.isEmpty()) {
				for(Challenge c : challenges) {
					if(!c.isFinish()) {
						if(c.checkCondition(event.getBlock().getType())) {
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
