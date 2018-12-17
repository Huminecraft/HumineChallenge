package com.humine.tokens.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class TokenManager
{

	private HashMap<String, Token> tokens;
	private String section;
	
	public TokenManager()
	{
		this.tokens = new HashMap<String, Token>();
		this.section = "Tokens";
	}
	
	public void addPlayer(Player player) {
		this.tokens.put(player.getName(), new Token());
	}
	
	public void removePlayer(Player player) {
		this.tokens.remove(player.getName());
	}
	
	public Token getToken(Player player) {
		return this.tokens.get(player.getName());
	}

	public boolean containsPlayer(Player player) {
		return this.tokens.containsKey(player.getName());
	}

	public boolean hasToken(Player player) {
		return this.tokens.containsKey(player.getName());
	}

	public boolean haveTheSameNumberOfToken(Player player1, Player player2) {
		return this.tokens.get(player1.getName()).getToken() == this.tokens.get(player2.getName()).getToken();
	}
	
	public void saveTokens(File file) throws IOException {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		config.createSection(this.section);
		for (Entry<String, Token> list : this.tokens.entrySet()) {
			config.set(this.section + "." + list.getKey() + ".Token", list.getValue().getToken());
		}

		config.save(file);
	}

	public HashMap<String, Token> getLevelsOnFile(File file) throws FileNotFoundException {
		HashMap<String, Token> list = new HashMap<String, Token>();
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		if (config.contains(this.section)) {
			for (String key : config.getConfigurationSection(this.section).getKeys(false)) {
				Token token = new Token();
				token.setToken(config.getInt(this.section + "." + key + ".Token"));
				list.put(key, token);
			}
		}

		return list;
	}

	public HashMap<String, Token> getTokens()
	{
		return tokens;
	}

	public void setTokens(HashMap<String, Token> tokens)
	{
		this.tokens = tokens;
	}
	
	public void clearTokens() {
		this.tokens.clear();
	}
}
