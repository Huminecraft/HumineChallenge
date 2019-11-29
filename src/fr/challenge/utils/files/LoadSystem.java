package fr.challenge.utils.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.challenges.Challenge;
import fr.challenge.utils.pass.Page;
import humine.utils.cosmetiques.Cosmetique;

public abstract class LoadSystem
{

	private static FileConfiguration config;
	
	public static Challenger loadChallenger(File folder) throws IOException, ClassNotFoundException {
		if(!folder.exists())
			folder.mkdirs();
		
		File challengerFile = new File(folder, "Challenger.yml");
		config = YamlConfiguration.loadConfiguration(challengerFile);
		
		Challenger challenger = new Challenger(Bukkit.getPlayer(config.getString(SaveSystem.parameters[0])));
		
		challenger.getToken().setAmount(config.getInt(SaveSystem.parameters[1]));
		challenger.setPremium(config.getBoolean(SaveSystem.parameters[2]));
		challenger.getLevel().setLevel(config.getInt(SaveSystem.parameters[3]));
		challenger.getLevel().setExperience(config.getInt(SaveSystem.parameters[4]));
		challenger.getLevel().setExperienceToReach(config.getInt(SaveSystem.parameters[5]));
		challenger.setCurrentHebdoWeek(config.getInt(SaveSystem.parameters[7]));
		
		String dateStr[] = config.getString(SaveSystem.parameters[6]).split("-");
		LocalDate date = LocalDate.of(Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[2]));
		challenger.setLastConnection(date);
		
		challenger.getLevelBar().update();
		
		if(challenger.hasPremium()) {
			if(challenger.getLastConnection().isBefore(ChallengeMain.getInstance().getCurrentHebdoChallengeDate())) {
				challenger.updateHebdoChallenge(ChallengeMain.getHebdoChallenge());
			}
			else {
				List<Challenge> challenges = loadWeekHebdoChallenge(challenger.getHebdoChallengeFolder(), ChallengeMain.getInstance().getCurrentWeek());
				challenger.updateHebdoChallenge(challenges);
			}
		}
		else {
			challenger.updateHebdoChallenge(new ArrayList<>());
		}
		
		if(challenger.getLastConnection().isBefore(ChallengeMain.getInstance().getCurrentDailyChallengeDate())) {
			challenger.setLastConnection(ChallengeMain.getInstance().getCurrentDailyChallengeDate());
			challenger.updateDailyChallenge(ChallengeMain.getDailyChallenge());
		}
		else {
			List<Challenge> challenges = loadChallenges(challenger.getDailyChallengeFolder());
			challenger.updateDailyChallenge(challenges);
		}
		
		challenger.getChallengePass().update(ChallengeMain.getPassMain());
		return challenger;
	}
	
	public static List<Challenge> loadChallenges(File folder) throws IOException, ClassNotFoundException {
		if(!folder.exists())
			folder.mkdirs();
		
		List<Challenge> challenges = new ArrayList<Challenge>();
		
		ObjectInputStream in;
		for(File f : folder.listFiles()) {
			in = new ObjectInputStream(new FileInputStream(f));
			challenges.add((Challenge) in.readObject());
		}
		
		return challenges;
	}
	
	public static List<Page> loadPages(File folder) throws IOException, ClassNotFoundException {
		if(!folder.exists())
			throw new FileNotFoundException();
		
		List<Page> pages = new ArrayList<Page>();
		
		ObjectInputStream in;
		for(File f : folder.listFiles()) {
			in = new ObjectInputStream(new FileInputStream(f));
			pages.add((Page) in.readObject());
		}
		
		return pages;
	}
	
	public static List<Cosmetique> loadCosmetique(File folder) throws IOException, ClassNotFoundException {
		if(!folder.exists())
			folder.mkdirs();
		
		List<Cosmetique> cosmetiques = new ArrayList<Cosmetique>();
		
		ObjectInputStream in;
		for(File f : folder.listFiles()) {
			in = new ObjectInputStream(new FileInputStream(f));
			cosmetiques.add((Cosmetique) in.readObject());
		}
		
		return cosmetiques;
	}
	
	public static List<Challenge> loadWeekHebdoChallenge(File folder, int semaine) throws ClassNotFoundException, IOException {
		File f = new File(folder, "Week" + semaine);
		if(!f.exists())
			return new ArrayList<Challenge>();
		else
			return loadChallenges(f);
	}
}
