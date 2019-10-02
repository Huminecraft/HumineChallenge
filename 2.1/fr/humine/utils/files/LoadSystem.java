package fr.humine.utils.files;

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

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;

public abstract class LoadSystem
{

	private static FileConfiguration config;
	
	public static Challenger loadChallenger(File folder) throws IOException, ClassNotFoundException {
		if(!folder.exists())
			throw new FileNotFoundException();
		
		File challengerFile = new File(folder, "Challenger.yml");
		config = YamlConfiguration.loadConfiguration(challengerFile);
		
		Challenger challenger = new Challenger(Bukkit.getPlayer(config.getString(SaveSystem.parameters[0])));
		
		challenger.getToken().setAmount(config.getInt(SaveSystem.parameters[1]));
		challenger.setPremium(config.getBoolean(SaveSystem.parameters[2]));
		challenger.getLevel().setLevel(config.getInt(SaveSystem.parameters[3]));
		challenger.getLevel().setExperience(config.getInt(SaveSystem.parameters[4]));
		challenger.getLevel().setExperienceToReach(config.getInt(SaveSystem.parameters[5]));
		
		String dateStr[] = config.getString(SaveSystem.parameters[6]).split("-");
		LocalDate date = LocalDate.of(Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[2]));
		challenger.setLastConnection(date);
		
		challenger.getLevelBar().update();
		
		if(challenger.hasPremium()) {
			if(challenger.getLastConnection().isBefore(ChallengeMain.getInstance().getCurrentHebdoChallengeDate())) {
				challenger.updateHebdoChallenge(ChallengeMain.getHebdoChallenge());
			}
			else {
				File hebdoFolder = new File(folder, "HebdoChallenge");
				List<Challenge> challenges = loadChallenge(hebdoFolder);
				challenger.updateHebdoChallenge(challenges);
			}
		}
		
		if(challenger.getLastConnection().isBefore(ChallengeMain.getInstance().getCurrentDailyChallengeDate())) {
			challenger.setLastConnection(ChallengeMain.getInstance().getCurrentDailyChallengeDate());
			challenger.updateDailyChallenge(ChallengeMain.getDailyChallenge());
		}
		else {
			File dailyFolder = new File(folder, "DailyChallenge");
			List<Challenge> challenges = loadChallenge(dailyFolder);
			challenger.updateDailyChallenge(challenges);
		}
		
		
		return challenger;
	}
	
	public static List<Challenge> loadChallenge(File folder) throws IOException, ClassNotFoundException {
		if(!folder.exists())
			throw new FileNotFoundException();
		
		List<Challenge> challenges = new ArrayList<Challenge>();
		
		ObjectInputStream in;
		for(File f : folder.listFiles()) {
			in = new ObjectInputStream(new FileInputStream(f));
			challenges.add((Challenge) in.readObject());
		}
		
		return challenges;
	}
}