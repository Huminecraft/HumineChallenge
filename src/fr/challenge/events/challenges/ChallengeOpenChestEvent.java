package fr.challenge.events.challenges;

import java.util.List;

import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.challenges.Challenge;
import fr.challenge.utils.challenges.ChallengeType;
import fr.challenge.utils.events.ChallengeFinishEvent;

public class ChallengeOpenChestEvent implements Listener {

	@EventHandler
	public void onOpen(InventoryOpenEvent event) {
		if (event.getInventory().getHolder() instanceof Chest || event.getInventory().getHolder() instanceof DoubleChest) {
			Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger((Player) event.getPlayer());
			if (challenger != null) {
				List<Challenge> challenges = challenger.getDailyChallenges(ChallengeType.OPEN_CHEST);
				if (!challenges.isEmpty()) {
					for (Challenge c : challenges) {
						if (!c.isFinish()) {
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
