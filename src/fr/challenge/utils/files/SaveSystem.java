package fr.challenge.utils.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.Challenger;
import fr.challenge.utils.challenges.Challenge;
import fr.challenge.utils.pass.Page;
import humine.utils.cosmetiques.Cosmetique;

public abstract class SaveSystem
{

	public static final String[] parameters = {
			"Player",
			"Token",
			"Premium",
			"Level",
			"Experience",
			"ExperienceToreach",
			"LastConnection",
			"CurrentHebdoWeek"};
	
	private static FileConfiguration config;
	
	public static void saveChallenger(Challenger challenger, File folder) throws IOException {
		if(!folder.exists()) {
			folder.mkdirs();
		} else {
			for(File f : folder.listFiles()) {
				f.delete();
			}
		}
		
		File challengerFile = new File(folder, "Challenger.yml");
		if(!challengerFile.exists())
			challengerFile.createNewFile();
		config = YamlConfiguration.loadConfiguration(challengerFile);
		
		config.set(parameters[0], challenger.getPlayer().getName());
		config.set(parameters[1], challenger.getToken().getAmount());
		config.set(parameters[2], challenger.hasPremium());
		config.set(parameters[3], challenger.getLevel().getLevel());
		config.set(parameters[4], challenger.getLevel().getExperience());
		config.set(parameters[5], challenger.getLevel().getExperienceToReach());
		config.set(parameters[6], challenger.getLastConnection().toString());
		config.set(parameters[7], challenger.getCurrentHebdoWeek());
		
		config.save(challengerFile);
		
		File hebdoFolder = challenger.getHebdoChallengeFolder();
		File dailyFolder = challenger.getDailyChallengeFolder();
		saveWeekHebdoChallenge(challenger.getHebdoChallenge(), hebdoFolder, ChallengeMain.getInstance().getCurrentWeek());
		saveChallenges(challenger.getDailyChallenge(), dailyFolder);
		
	}
	
	public static void saveChallenges(List<Challenge> challenges, File folder) throws IOException {
		if(!folder.exists()) {
			folder.mkdirs();
		}
		else {
			for(File f : folder.listFiles())
				f.delete();
		}
		
		ObjectOutputStream out;
		for(Challenge c : challenges) {
			File f = new File(folder, c.getTitle() + ".challenge");
			f.setWritable(true);
			f.setReadable(true);
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(c);
			out.flush();
			out.close();
		}
	}
	
	public static void savePages(List<Page> pages, File folder) throws IOException {
		if(!folder.exists()) {
			folder.mkdirs();
		}
		else {
			for(File f : folder.listFiles())
				f.delete();
		}
		
		ObjectOutputStream out;
		int i = 0;
		for(Page page : pages) {
			File f = new File(folder, i++ + ".page");
			f.setWritable(true);
			f.setReadable(true);
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(page);
			out.flush();
			out.close();
		}
	}
	
	public static void saveCosmetiques(List<Cosmetique> cosmetiques, File folder) throws IOException {
		if(!folder.exists()) {
			folder.mkdirs();
		}
		else {
			for(File f : folder.listFiles())
				f.delete();
		}
		
		ObjectOutputStream out;
		for(Cosmetique c: cosmetiques) {
			File f = new File(folder, c.getId() + ".cosmetique");
			f.setWritable(true);
			f.setReadable(true);
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(c);
			out.flush();
			out.close();
		}
	}
	
	public static void saveWeekHebdoChallenge(List<Challenge> challenges, File folder, int semaine) throws IOException {
		File f = new File(folder, "Week" + semaine);
		saveChallenges(challenges, f);
	}
}
