package com.humine.levels.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class LevelManager {

	private HashMap<String, Level> levels;
	private String section;

	public LevelManager() {
		levels = new HashMap<String, Level>();
		section = "Levels";
	}

	public void addPlayer(Player player) {
		this.levels.put(player.getName(), new Level());
	}

	public void removePlayer(Player player) {
		this.levels.remove(player.getName());
	}

	public Level getLevel(Player player) {
		return this.levels.get(player.getName());
	}

	public boolean containsPlayer(Player player) {
		if (this.levels.containsKey(player.getName()))
			return true;
		else
			return false;
	}

	public boolean hasLevel(Player player) {
		if (this.levels.containsKey(player.getName()))
			return true;
		else
			return false;
	}

	public boolean hasTheSameLevel(Player player1, Player player2) {
		if (this.levels.get(player1.getName()) == this.levels.get(player2.getName()))
			return true;
		else
			return false;
	}

	public void clearLevels() {
		this.levels.clear();
	}

	public void saveLevels(File file) throws IOException {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		config.createSection(this.section);
		for (Entry<String, Level> list : this.levels.entrySet()) {
			config.set(this.section + "." + list.getKey(), list.getValue().getLevel());
		}

		config.save(file);
	}

	public HashMap<String, Level> getLevelsOnFile(File file) throws FileNotFoundException {
		HashMap<String, Level> list = new HashMap<String, Level>();
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		if (config.contains(this.section)) {
			for (String key : config.getConfigurationSection(this.section).getKeys(false)) {
				int l = config.getInt(this.section + "." + key);
				list.put(key, new Level(l));
			}
		}

		return list;
	}
}
