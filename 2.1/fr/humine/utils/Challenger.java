package fr.humine.utils;

import org.bukkit.entity.Player;

import fr.humine.utils.pass.ChallengePass;

public class Challenger {

	private Player player;
	private Token token;
	private boolean premium;
	private ChallengePass challengePass;
	private LevelBar levelBar;
	
	public Challenger(Player player) {
		this.player = player;
		this.token = new Token();
		this.premium = false;
		this.challengePass = new ChallengePass(this);
		this.levelBar = new LevelBar(this);
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
	
	public Token getToken()
	{
		return token;
	}
	
	public ChallengePass getChallengePass()
	{
		return challengePass;
	}
	
	public LevelBar getLevelBar()
	{
		return levelBar;
	}
	
	public Level getLevel() 
	{
		return levelBar.getLevel();
	}
}
