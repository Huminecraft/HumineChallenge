package fr.humine.events.challenges;

import java.util.List;

import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;
import fr.humine.utils.challenges.ChallengeType;
import fr.humine.utils.events.ChallengeFinishEvent;
import fr.humine.utils.events.PlayerChangeBiomeEvent;

public class ChallengeBiomeDiscoverEvent implements Listener{

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if(event.getPlayer().getWorld().getBlockAt(event.getFrom()).getBiome() != event.getPlayer().getWorld().getBlockAt(event.getTo()).getBiome()) {
			Biome fromBiome = event.getPlayer().getWorld().getBlockAt(event.getFrom()).getBiome();
			Biome toBiome = event.getPlayer().getWorld().getBlockAt(event.getTo()).getBiome();
			ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new PlayerChangeBiomeEvent(event.getPlayer(), fromBiome, toBiome));
		}
	}
	
	@EventHandler
	public void onChange(PlayerChangeBiomeEvent event) {
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(event.getPlayer());
		List<Challenge> challenges = challenger.getDailyChallenges(ChallengeType.BIOME_DISCOVER);
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
