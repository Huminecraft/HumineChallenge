package fr.humine.events;

import java.io.File;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.files.LoadSystem;

public class CreateChallengerEvent implements Listener
{

	@EventHandler
	public void onJoin(PlayerJoinEvent event) throws ClassNotFoundException, IOException {
		Player player = event.getPlayer();
		
		File folder = new File(ChallengeMain.getInstance().FOLDERCHALLENGER, player.getName());
		Challenger challenger;
		if(folder.exists()) {
			challenger = LoadSystem.loadChallenger(folder);
		}
		else {
			challenger = new Challenger(player);
		}
		
		ChallengeMain.getInstance().getBankChallenger().addChallenger(challenger);
		
	}
}
