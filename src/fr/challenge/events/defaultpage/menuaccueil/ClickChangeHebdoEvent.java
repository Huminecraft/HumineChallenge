package fr.challenge.events.defaultpage.menuaccueil;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.challenge.utils.ItemShop;
import fr.challenge.utils.events.ihm.ClickItemMenuAccueilEvent;
import fr.challenge.utils.menu.MenuApplePay;
import fr.challenge.utils.menu.MenuChangeHebdoChallenge;

public class ClickChangeHebdoEvent implements Listener{

	@EventHandler
	public void onClick(ClickItemMenuAccueilEvent event) {
		if(event.getItem().isSimilar(ItemShop.changeHebdoItem())) {
			if(event.getChallenger().hasPremium())
				MenuChangeHebdoChallenge.openMenu(event.getChallenger());
			else
				MenuApplePay.openMenu(event.getChallenger());
		}
	}
}
