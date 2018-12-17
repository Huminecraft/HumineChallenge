package com.humine.levels.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class LevelManager {

	private HashMap<String, Level> levels;
	private String section;
	private BossBar bar;

	public LevelManager() {
		levels = new HashMap<String, Level>();
		section = "Levels";
		this.bar = Bukkit.createBossBar("", BarColor.GREEN, BarStyle.SEGMENTED_6, new BarFlag[0]);
	}

	public void addPlayer(Player player) {
		this.levels.put(player.getName(), new Level());
		addBarBoss(player, new Level());
	}

	public void removePlayer(Player player) {
		this.levels.remove(player.getName());
		removeBarBoss(player);
	}

	public Level getLevel(Player player) {
		return this.levels.get(player.getName());
	}

	public boolean containsPlayer(Player player) {
		return this.levels.containsKey(player.getName());
	}

	public boolean hasLevel(Player player) {
		return this.levels.containsKey(player.getName());
	}

	public boolean haveTheSameLevel(Player player1, Player player2) {
		return this.levels.get(player1.getName()).getLevel() == this.levels.get(player2.getName()).getLevel();
	}

	public void clearLevels() {
		this.levels.clear();
	}
	
	public void clearBarBossForAll() {
		this.bar.removeAll();
	}

	public void saveLevels(File file) throws IOException {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		config.createSection(this.section);
		for (Entry<String, Level> list : this.levels.entrySet()) {
			config.set(this.section + "." + list.getKey() + ".Level", list.getValue().getLevel());
			config.set(this.section + "." + list.getKey() + ".Experience", list.getValue().getExperience());
			config.set(this.section + "." + list.getKey() + ".ExperienceToReach", list.getValue().getExperienceToReach());
		}

		config.save(file);
	}

	public HashMap<String, Level> getLevelsOnFile(File file) throws FileNotFoundException {
		HashMap<String, Level> list = new HashMap<String, Level>();
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		if (config.contains(this.section)) {
			for (String key : config.getConfigurationSection(this.section).getKeys(false)) {
				Level level = new Level();
				level.setLevel(config.getInt(this.section + "." + key + ".Level"));
				level.setExperience(config.getInt(this.section + "." + key + ".Experience"));
				level.setExperienceToReach(config.getInt(this.section + "." + key + ".ExperienceToReach"));
				list.put(key, level);
			}
		}

		return list;
	}

	public HashMap<String, Level> getLevels()
	{
		return levels;
	}

	public void setLevels(HashMap<String, Level> levels)
	{
		this.levels = levels;
	}
	
	public void addBarBoss(Player player, Level level) {
		this.bar.setTitle("Level: " + level.getLevel());
		this.bar.setProgress(((double) level.getExperience() / (double) level.getExperienceToReach()));
		this.bar.addPlayer(player);
	}
	
	public void removeBarBoss(Player player) {
		this.bar.removePlayer(player);
	}
	
	public void refreshBarBoss(Player player, Level level) {
		addBarBoss(player, level);
	}
}
