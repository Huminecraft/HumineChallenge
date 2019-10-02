package fr.humine.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.challenges.Challenge;
import fr.humine.utils.challenges.ChallengeType;
import fr.humine.utils.pass.ChallengePass;

public class Challenger {

	private Player player;
	private Token token;
	private boolean premium;
	private ChallengePass challengePass;
	private LevelBar levelBar;
	
	private List<Challenge> dailyChallenge;
	private List<Challenge> hebdoChallenge;
	
	private LocalDate lastConnection;
	
	public Challenger(Player player) {
		this.player = player;
		this.token = new Token();
		this.premium = false;
		this.challengePass = new ChallengePass(this);
		this.levelBar = new LevelBar(this);
		this.lastConnection = LocalDate.now();
		updateDailyChallenge(ChallengeMain.getDailyChallenge());
		updateHebdoChallenge(ChallengeMain.getHebdoChallenge());
	}
	
	public boolean hasPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
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
	
	public List<Challenge> getDailyChallenge() {
		return dailyChallenge;
	}
	
	public List<Challenge> getHebdoChallenge()
	{
		return hebdoChallenge;
	}
	
	public LocalDate getLastConnection()
	{
		return lastConnection;
	}
	
	public void setLastConnection(LocalDate lastConnection)
	{
		this.lastConnection = lastConnection;
	}
	
	public List<Challenge> getDailyChallenges(ChallengeType type) {
		List<Challenge> list = new ArrayList<>();
		for(Challenge c : dailyChallenge) {
			if(c.getType() == type)
				list.add(c);
		}
		return list;
	}
	
	public List<Challenge> getHebdoChallenges(ChallengeType type) {
		List<Challenge> list = new ArrayList<>();
		for(Challenge c : hebdoChallenge) {
			if(c.getType() == type)
				list.add(c);
		}
		return list;
	}
	
	public void updateDailyChallenge(List<Challenge> challenges) {
		this.dailyChallenge = new ArrayList<>(challenges);
	}
	
	public void updateHebdoChallenge(List<Challenge> challenges)
	{
		this.hebdoChallenge = new ArrayList<>(challenges);
	}
}
