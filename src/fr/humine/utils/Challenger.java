package fr.humine.utils;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.challenges.Challenge;
import fr.humine.utils.challenges.ChallengeType;
import fr.humine.utils.pass.ChallengePass;

/**
 * Classe contenant toutes les informations du joueur par rapport
 * au plugin HumineChallenge
 * @author Miza
 */
public class Challenger {

	private Player player;
	private Token token;
	private boolean premium;
	private ChallengePass challengePass;
	private LevelBar levelBar;
	private int currentHebdoWeek;
	
	private List<Challenge> dailyChallenge;
	private List<Challenge> hebdoChallenge;
	
	private LocalDate lastConnection;
	
	/**
	 * Constructeur du Challenger
	 * @param player le joueur auquel il faut creer un compte Challenger
	 */
	public Challenger(Player player) {
		this.player = player;
		this.token = new Token();
		this.premium = false;
		this.challengePass = new ChallengePass(this);
		this.levelBar = new LevelBar(this);
		this.lastConnection = LocalDate.now();
		this.currentHebdoWeek = ChallengeMain.getInstance().getCurrentWeek();
		this.dailyChallenge = new ArrayList<>();
		this.hebdoChallenge = new ArrayList<>();
		updateDailyChallenge(ChallengeMain.getDailyChallenge());
		updateHebdoChallenge(ChallengeMain.getHebdoChallenge());
	}
	
	/**
	 * @return true si le joueur possede le HumineSurvival, sinon false
	 */
	public boolean hasPremium() {
		return premium;
	}
	
	/**
	 * Definir si le joueur possede le HumineSurvival
	 * @param premium true si il le possede, sinon false
	 */
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	/**
	 * @return Le pseudo du joueur
	 */
	public String getName() {
		return player.getName();
	}
	
	/**
	 * @return Le {@link Player} possedant le compte Challenger
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * @return les {@link Token} du joueurs
	 */
	public Token getToken()
	{
		return token;
	}
	
	/**
	 * @return le {@link ChallengePass} du joueur
	 */
	public ChallengePass getChallengePass()
	{
		return challengePass;
	}
	
	/**
	 * @return la {@link LevelBar} du joueur
	 */
	public LevelBar getLevelBar()
	{
		return levelBar;
	}
	
	/**
	 * @return le {@link Level} du joueur
	 */
	public Level getLevel() 
	{
		return levelBar.getLevel();
	}
	
	/**
	 * @return la liste des challenges journaliers du Challenger
	 */
	public List<Challenge> getDailyChallenge() {
		return dailyChallenge;
	}
	
	/**
	 * @return la liste des challenges hebdomadaires du Challenger
	 */
	public List<Challenge> getHebdoChallenge()
	{
		return hebdoChallenge;
	}
	
	/**
	 * @return le {@link LocalDate} du Challenger represenant le dernier jour
	 * de connection du Challenger
	 */
	public LocalDate getLastConnection()
	{
		return lastConnection;
	}
	
	/**
	 * Definir le dernier de connection du Challenger
	 * @param lastConnection la derniere connection
	 */
	public void setLastConnection(LocalDate lastConnection)
	{
		this.lastConnection = lastConnection;
	}
	
	/**
	 * @param type Le type de challenge a filtrer
	 * @return la liste des challenges journaliers du Challenger comportant le
	 * meme {@link ChallengeType}
	 */
	public List<Challenge> getDailyChallenges(ChallengeType type) {
		List<Challenge> list = new ArrayList<>();
		for(Challenge c : dailyChallenge) {
			if(c.getType() == type)
				list.add(c);
		}
		return list;
	}
	
	/**
	 * @param type Le type de challenge a filtrer
	 * @return la liste des challenges hebdomadaires du Challenger comportant le
	 * meme {@link ChallengeType}
	 */
	public List<Challenge> getHebdoChallenges(ChallengeType type) {
		List<Challenge> list = new ArrayList<>();
		for(Challenge c : hebdoChallenge) {
			if(c.getType() == type)
				list.add(c);
		}
		return list;
	}
	
	/**
	 * Met a jour la liste des {@link Challenge} journaliers du Challenger 
	 * @param challenges la liste de reference pour la mise a jour
	 */
	public void updateDailyChallenge(List<Challenge> challenges) {
		this.dailyChallenge = new ArrayList<>();
		for(Challenge challenge : challenges) {
			Challenge c = challenge.clonage();
			this.dailyChallenge.add(c);
		}
	}
	
	/**
	 * Met a jour la liste des {@link Challenge} hebdomadaires du Challenger 
	 * @param challenges la liste de reference pour la mise a jour
	 */
	public void updateHebdoChallenge(List<Challenge> challenges) {
		this.hebdoChallenge = new ArrayList<>();
		for(Challenge challenge : challenges) {
			Challenge c = challenge.clonage();
			this.hebdoChallenge.add(c);
		}
	}
	
	/**
	 * @return le dossier du Challenger
	 */
	public File getChallengerFolder()
	{
		return new File(ChallengeMain.getInstance().FOLDERCHALLENGER, player.getName());
	}
	
	/**
	 * @return le numero de semaine de la liste des {@link Challenge} hebdomaires
	 * charges
	 */
	public int getCurrentHebdoWeek()
	{
		return currentHebdoWeek;
	}
	
	/**
	 * Permet de definir le numero de semaine de la liste des {@link Challenge}
	 * hebdomadaires
	 * @param currentHebdoWeek le numero de le semaine
	 */
	public void setCurrentHebdoWeek(int currentHebdoWeek)
	{
		this.currentHebdoWeek = currentHebdoWeek;
	}
}
