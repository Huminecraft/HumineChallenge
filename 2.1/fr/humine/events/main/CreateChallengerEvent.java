package fr.humine.events.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;

public class CreateChallengerEvent implements Listener
{

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if(!ChallengeMain.getInstance().getBankChallenger().contains(player)){
			Challenger c = new Challenger(player);
			ChallengeMain.getInstance().getBankChallenger().addChallenger(c);
		}
		
		
	}
}
