package fr.challenge.events.defaultpage.menudaily;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.challenge.utils.ItemShop;
import fr.challenge.utils.events.ihm.ClickItemMenuDailyEvent;
import fr.challenge.utils.menu.MenuAccueil;

public class ClickQuitEvent implements Listener{

	@EventHandler
	public void onClick(ClickItemMenuDailyEvent event) {
		if(event.getItem().isSimilar(ItemShop.itemQuit())) {
			MenuAccueil.openMenu(event.getChallenger());
		}
	}
}
