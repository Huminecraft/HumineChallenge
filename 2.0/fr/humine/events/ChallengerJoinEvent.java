package fr.humine.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.humine.ChallengeMain;
import fr.humine.utils.players.Challenger;
import fr.humine.utils.token.TokenAccount;


public class ChallengerJoinEvent implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if(!ChallengeMain.getInstance().getBankChallenger().contains(event.getPlayer())) {
			Challenger challenger = new Challenger(event.getPlayer());
			ChallengeMain.getInstance().getBankChallenger().addChallenger(challenger);
		}
		
		if(!ChallengeMain.getInstance().getBankLevel().contains(event.getPlayer())) {
			ChallengeMain.getInstance().getBankLevel().addPlayer(event.getPlayer());
		}
		
		if(!ChallengeMain.getInstance().getBankToken().contains(event.getPlayer().getName())) {
			TokenAccount account = new TokenAccount(event.getPlayer().getName());
			ChallengeMain.getInstance().getBankToken().addAccount(account);
		}
	}
}
