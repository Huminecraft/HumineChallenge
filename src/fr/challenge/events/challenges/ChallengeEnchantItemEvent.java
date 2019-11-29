package fr.challenge.events.challenges;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.challenges.Challenge;
import fr.challenge.utils.challenges.ChallengeType;
import fr.challenge.utils.events.ChallengeFinishEvent;

public class ChallengeEnchantItemEvent implements Listener {

	@EventHandler
	public void onEnchant(EnchantItemEvent event) {
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(event.getEnchanter());
		if (challenger != null) {
			List<Challenge> challenges = challenger.getDailyChallenges(ChallengeType.ENCHANT_ITEM);
			if (!challenges.isEmpty()) {
				for (Challenge c : challenges) {
					if (!c.isFinish()) {
						if(c.checkCondition(event.getItem())) {
							c.update();
							if (c.isFinish()) {
								ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new ChallengeFinishEvent(c, challenger));
							}
						}
					}
				}
			}
		}
	}
}
