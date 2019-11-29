package fr.challenge.events;

import java.io.File;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.files.SaveSystem;

public class QuitChallengerEvent implements Listener
{

	@EventHandler
	public void onQuit(PlayerQuitEvent event) throws IOException {
		Player player = event.getPlayer();
		
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(player);
		ChallengeMain.getInstance().getBankChallenger().removeChallenger(challenger);
		
		File folder = challenger.getChallengerFolder();
		SaveSystem.saveChallenger(challenger, folder);
	}
}
