package fr.humine.utils.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;
import fr.humine.utils.pass.Page;

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
			"DailyChallenge"};
	
	private static FileConfiguration config;
	
	public static void saveChallenger(Challenger challenger, File folder) throws IOException {
		if(!folder.getParentFile().exists())
			throw new FileNotFoundException();
		if(!folder.exists()) {
			folder.mkdirs();
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
		
		config.save(challengerFile);
		
		File hebdoFolder = new File(folder, "HebdoChallenge");
		File DailyFolder = new File(folder, "DailyChallenge");
		saveChallenges(challenger.getHebdoChallenge(), hebdoFolder);
		saveChallenges(challenger.getDailyChallenge(), DailyFolder);
	}
	
	public static void saveChallenges(List<Challenge> challenges, File folder) throws IOException {
		if(!folder.getParentFile().exists())
			throw new FileNotFoundException();
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		ObjectOutputStream out;
		for(Challenge c : challenges) {
			File f = new File(folder, c.getTitle() + ".challenge");
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(c);
			out.flush();
			out.close();
		}
	}
	
	public static void savePages(List<Page> pages, File folder) throws IOException {
		if(!folder.getParentFile().exists())
			throw new FileNotFoundException();
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		ObjectOutputStream out;
		int i = 0;
		for(Page page : pages) {
			File f = new File(folder, i++ + ".page");
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(page);
			out.flush();
			out.close();
		}
	}
	
}
