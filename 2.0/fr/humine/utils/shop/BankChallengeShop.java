package fr.humine.utils.shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import fr.humine.ChallengeMain;
import fr.humine.utils.players.Challenger;

public class BankChallengeShop implements Iterable<ChallengeShop>{

	private ChallengeShop defaultChallengeShop;
	private Map<String, ChallengeShop> challengeShops;
	
	public BankChallengeShop(ChallengeShop defaut) {
		this.challengeShops = new HashMap<String, ChallengeShop>();
		this.defaultChallengeShop = defaut;
	}
	
	public void openShop(Player player) {
		if(this.defaultChallengeShop == null)
			return;
		
		if(this.defaultChallengeShop.isEmpty())
			return;
		
		Challenger c = ChallengeMain.getInstance().getBankChallenger().getChallenger(player);
		if(c == null)
			return;
		
		List<Page> p = new ArrayList<Page>();
		Collections.copy(p, this.defaultChallengeShop.getPages());
		
		ChallengeShop shop = new ChallengeShop(this.defaultChallengeShop.getName(), p, c);
		this.challengeShops.put(player.getName(), shop);
		shop.openShop();
	}
	
	public void closeShop(Player player) {
		ChallengeShop shop = this.challengeShops.get(player.getName());
		if(shop == null)
			return;
		
		shop.closeShop();
		this.challengeShops.remove(player.getName());
	}
	
	public void nextPage(Player player) {
		ChallengeShop shop = this.challengeShops.get(player.getName());
		if(shop == null)
			return;
		
		shop.nextPage();
	}
	
	public void previousPage(Player player) {
		ChallengeShop shop = this.challengeShops.get(player.getName());
		if(shop == null)
			return;
		
		shop.nextPage();
	}
	
	public void goToPage(Player player, int n) {
		ChallengeShop shop = this.challengeShops.get(player.getName());
		if(shop == null)
			return;
		
		shop.goToPage(n);
	}

	public void update() {
		for(ChallengeShop shop : this.challengeShops.values()) {
			List<Page> p = new ArrayList<Page>();
			Collections.copy(p, this.defaultChallengeShop.getPages());
			shop.setPages(p);
		}
	}
	
	public ChallengeShop getDefaultChallengeShop() {
		return defaultChallengeShop;
	}
	
	public void setDefaultChallengeShop(ChallengeShop defaultChallengeShop) {
		this.defaultChallengeShop = defaultChallengeShop;
	}
	
	@Override
	public Iterator<ChallengeShop> iterator() {
		return this.challengeShops.values().iterator();
	}
	
}
