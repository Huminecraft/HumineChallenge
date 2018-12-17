package com.humine.utils.level.challenge;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import com.humine.main.ChallengeMain;
import com.humine.utils.level.challenge.missions.Mission;
import com.humine.utils.level.challenge.missions.MissionType;
import com.humine.utils.level.challenge.missions.Recompense;

public abstract class DailyChallengeManager
{

	public static void saveDailyChallenge(DailyChallenge challenge) throws IOException
	{
		File folder = new File(ChallengeMain.getInstance().getDailyChallengeFolder(), challenge.getDate());

		if (folder.exists())
			folder.delete();
		
		folder.mkdirs();

		if(!challenge.getMissions().isEmpty()){
			
			File file;
			for (int i = 0; i < challenge.getMissions().size(); i++)
			{
				file = new File(folder, challenge.getMissions().get(i).getMissionName() + ".yml");
				file.createNewFile();
				
				FileConfiguration config = YamlConfiguration.loadConfiguration(file);

				for (Entry<String, Object> entry : challenge.getMissions().get(i).serialize().entrySet())
				{
					config.set(entry.getKey(), entry.getValue());
				}

				config.save(file);
			}
		}
		
	}

	public static DailyChallenge getDailyChallenge(String date)
	{
		File folder = new File(ChallengeMain.getInstance().getDailyChallengeFolder(), date);
		DailyChallenge challenge = new DailyChallenge();

		if (!folder.exists())
			return null;

		challenge.setDate(folder.getName());

		FileConfiguration config;
		Mission mission;

		for (File file : folder.listFiles())
		{
			config = YamlConfiguration.loadConfiguration(file);
			mission = new Mission();
			
			mission.setMissionName(config.getString("Name"));
			mission.setDescription(config.getString("Description"));
			mission.setEntity(getEntity(config.getString(("Entity"))));
			mission.setItem(config.getItemStack("Item"));
			mission.setNumber(config.getInt("Number"));
			mission.setMissionPremium(config.getBoolean("Premium"));
			mission.setMissionType(MissionType.getType(config.getString("Type")));
			
			Recompense recompense = new Recompense();
			recompense.setExperience(config.getInt("Recompense.Experience"));
			recompense.setToken(config.getInt("Recompense.Token"));
			mission.setRecompense(recompense);

			challenge.getMissions().add(mission);
		}

		return challenge;
	}
	
	private static EntityType getEntity(String str) {
		for(EntityType entity : EntityType.values()) {
			if(entity.toString().equalsIgnoreCase(str))
				return entity;
		}
		return null;
	}
}
