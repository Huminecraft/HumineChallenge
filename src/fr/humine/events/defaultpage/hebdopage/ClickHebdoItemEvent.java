package fr.humine.events.defaultpage.hebdopage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;
import fr.humine.utils.defaultpage.PageHebo;
import fr.humine.utils.files.LoadSystem;
import fr.humine.utils.files.SaveSystem;

public class ClickHebdoItemEvent implements Listener
{

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getView().getTitle().equals(PageHebo.NAME)) {
			if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.BOOK && event.getCurrentItem().getItemMeta().getDisplayName().contains("Semaine")) {
				int week = Integer.parseInt(event.getCurrentItem().getItemMeta().getDisplayName().split(" ")[1]);
				Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger((Player) event.getWhoClicked());
				saveChallenges(challenger);
				List<Challenge> challenges = loadChallenge(challenger, week);
				if(challenges != null) {
					challenger.setCurrentHebdoWeek(week);
					challenger.updateHebdoChallenge(challenges);
					ChallengeMain.sendMessage(challenger.getPlayer(), "Chargement des challenges hebdomadaires de la semaine " + week + " termines");
				}
				else
					ChallengeMain.sendMessage(challenger.getPlayer(), "Impossible de charger les challenges hebdomadaires de la semaine " + week);
			
				challenger.getPlayer().closeInventory();
			}
			event.setCancelled(true);
		}
	}

	private void saveChallenges(Challenger challenger)
	{
		File folder = new File(challenger.getChallengerFolder(), "HebdoChallenge");
		try {
			SaveSystem.saveWeekHebdoChallenge(challenger.getHebdoChallenge(), folder, challenger.getCurrentHebdoWeek());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Challenge> loadChallenge(Challenger challenger, int week)
	{
		File folder = new File(challenger.getChallengerFolder(), "HebdoChallenge");
		try {
			return LoadSystem.loadWeekHebdoChallenge(folder, week);
		}
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
