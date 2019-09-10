package fr.humine.utils;

import org.bukkit.entity.Player;

import fr.humine.pass.utils.ChallengeShop;

public class Challenger {

	private Player player;
	private boolean premium;
	private int token;
	
	private ChallengeShop shop;
	
	public Challenger(Player player) {
		this.player = player;
		this.premium = false;
		this.token = 0;
		this.shop = new ChallengeShop(this);
	}
	
	public boolean hasPremium() {
		return premium;
	}
	
	public String getName() {
		return player.getName();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getToken() {
		return token;
	}
	
	public void setToken(int token) {
		this.token = token;
	}
	
	public ChallengeShop getShop() {
		return shop;
	}
	
	public void setShop(ChallengeShop shop) {
		this.shop = shop;
	}
}
