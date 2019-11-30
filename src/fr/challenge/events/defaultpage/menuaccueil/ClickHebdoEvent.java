package fr.challenge.events.defaultpage.menuaccueil;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.challenge.utils.ItemShop;
import fr.challenge.utils.events.ihm.ClickItemMenuAccueilEvent;
import fr.challenge.utils.menu.MenuHebdoChallenge;
import fr.challenge.utils.menu.MenuApplePay;

public class ClickHebdoEvent implements Listener{

	@EventHandler
	public void onClick(ClickItemMenuAccueilEvent event) {
		if(event.getItem().isSimilar(ItemShop.hebdoItem())) {
			if(event.getChallenger().hasPremium())
				MenuHebdoChallenge.openMenu(event.getChallenger());
			else
				MenuApplePay.openMenu(event.getChallenger());
		}
	}
}
