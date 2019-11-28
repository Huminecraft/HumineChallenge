package fr.humine.events.challenges;

import java.util.List;

import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;
import fr.humine.utils.challenges.ChallengeType;
import fr.humine.utils.events.ChallengeFinishEvent;

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
