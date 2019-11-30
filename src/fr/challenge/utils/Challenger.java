package fr.challenge.utils;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.challenges.Challenge;
import fr.challenge.utils.challenges.ChallengeType;
import fr.challenge.utils.pass.ChallengePass;
import humine.main.MainShop;

/**
 * Classe contenant toutes les informations du joueur par rapport au plugin
 * HumineChallenge
 * 
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
	 * 
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
	 * 
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
	public Token getToken() {
		return token;
	}

	/**
	 * @return le {@link ChallengePass} du joueur
	 */
	public ChallengePass getChallengePass() {
		return challengePass;
	}

	/**
	 * @return la {@link LevelBar} du joueur
	 */
	public LevelBar getLevelBar() {
		return levelBar;
	}

	/**
	 * @return le {@link Level} du joueur
	 */
	public Level getLevel() {
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
	public List<Challenge> getHebdoChallenge() {
		return hebdoChallenge;
	}

	/**
	 * @return le {@link LocalDate} du Challenger represenant le dernier jour de
	 *         connection du Challenger
	 */
	public LocalDate getLastConnection() {
		return lastConnection;
	}

	/**
	 * Definir le dernier de connection du Challenger
	 * 
	 * @param lastConnection la derniere connection
	 */
	public void setLastConnection(LocalDate lastConnection) {
		this.lastConnection = lastConnection;
	}

	/**
	 * @param type Le type de challenge a filtrer
	 * @return la liste des challenges journaliers du Challenger comportant le meme
	 *         {@link ChallengeType}
	 */
	public List<Challenge> getDailyChallenges(ChallengeType type) {
		List<Challenge> list = new ArrayList<>();
		for (Challenge c : dailyChallenge) {
			if (c.getType() == type)
				list.add(c);
		}
		return list;
	}

	/**
	 * @param type Le type de challenge a filtrer
	 * @return la liste des challenges hebdomadaires du Challenger comportant le
	 *         meme {@link ChallengeType}
	 */
	public List<Challenge> getHebdoChallenges(ChallengeType type) {
		List<Challenge> list = new ArrayList<>();
		for (Challenge c : hebdoChallenge) {
			if (c.getType() == type)
				list.add(c);
		}
		return list;
	}

	/**
	 * Met a jour la liste des {@link Challenge} journaliers du Challenger
	 * 
	 * @param challenges la liste de reference pour la mise a jour
	 */
	public void updateDailyChallenge(List<Challenge> challenges) {
		this.dailyChallenge = new ArrayList<>();
		for (Challenge challenge : challenges) {
			try {
				Challenge c = (Challenge) challenge.clone();
				this.dailyChallenge.add(c);
			} catch (CloneNotSupportedException e) {
				MainShop.getInstance().getLogger().severe(e.getMessage());
			}
		}
	}

	/**
	 * Met a jour la liste des {@link Challenge} hebdomadaires du Challenger
	 * 
	 * @param challenges la liste de reference pour la mise a jour
	 */
	public void updateHebdoChallenge(List<Challenge> challenges) {
		this.hebdoChallenge = new ArrayList<>();
		for (Challenge challenge : challenges) {
			try {
				Challenge c = (Challenge) challenge.clone();
				this.hebdoChallenge.add(c);
			} catch (CloneNotSupportedException e) {
				MainShop.getInstance().getLogger().severe(e.getMessage());
			}

		}
	}

	/**
	 * @return le dossier du Challenger
	 */
	public File getChallengerFolder() {
		return new File(ChallengeMain.getInstance().FOLDERCHALLENGER, player.getUniqueId().toString());
	}

	public File getDailyChallengeFolder() {
		return new File(getChallengerFolder(), "DailyChallenge");
	}

	public File getHebdoChallengeFolder() {
		return new File(getChallengerFolder(), "HebdoChallenge");
	}

	public File getChallengerYAMLFile() {
		return new File(getChallengerFolder(), "Challenger.yml");
	}

	/**
	 * @return le numero de semaine de la liste des {@link Challenge} hebdomaires
	 *         charges
	 */
	public int getCurrentHebdoWeek() {
		return currentHebdoWeek;
	}

	/**
	 * Permet de definir le numero de semaine de la liste des {@link Challenge}
	 * hebdomadaires
	 * 
	 * @param currentHebdoWeek le numero de le semaine
	 */
	public void setCurrentHebdoWeek(int currentHebdoWeek) {
		this.currentHebdoWeek = currentHebdoWeek;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((challengePass == null) ? 0 : challengePass.hashCode());
		result = prime * result + currentHebdoWeek;
		result = prime * result + ((dailyChallenge == null) ? 0 : dailyChallenge.hashCode());
		result = prime * result + ((hebdoChallenge == null) ? 0 : hebdoChallenge.hashCode());
		result = prime * result + ((lastConnection == null) ? 0 : lastConnection.hashCode());
		result = prime * result + ((levelBar == null) ? 0 : levelBar.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + (premium ? 1231 : 1237);
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Challenger other = (Challenger) obj;
		if (challengePass == null) {
			if (other.challengePass != null)
				return false;
		} else if (!challengePass.equals(other.challengePass))
			return false;
		if (currentHebdoWeek != other.currentHebdoWeek)
			return false;
		if (dailyChallenge == null) {
			if (other.dailyChallenge != null)
				return false;
		} else if (!dailyChallenge.equals(other.dailyChallenge))
			return false;
		if (hebdoChallenge == null) {
			if (other.hebdoChallenge != null)
				return false;
		} else if (!hebdoChallenge.equals(other.hebdoChallenge))
			return false;
		if (lastConnection == null) {
			if (other.lastConnection != null)
				return false;
		} else if (!lastConnection.equals(other.lastConnection))
			return false;
		if (levelBar == null) {
			if (other.levelBar != null)
				return false;
		} else if (!levelBar.equals(other.levelBar))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (premium != other.premium)
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
}
