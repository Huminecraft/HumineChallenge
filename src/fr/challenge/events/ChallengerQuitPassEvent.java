package fr.challenge.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.pass.ChallengePass;

public class ChallengerQuitPassEvent implements Listener
{

	public void onQuit(InventoryCloseEvent event) {
		if(event.getView().getTitle().startsWith(ChallengePass.SHOPNAME)) {
			Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger((Player) event.getPlayer());
			challenger.getChallengePass().closeShop();
		}
	}
}
