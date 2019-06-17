package fr.humine.utils.levels;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class LevelBank implements Serializable {

	private static final long serialVersionUID = 7189158643912923404L;
	private Map<String, Level> players;
	
	public LevelBank(Map<String, Level> players)
	{
		this.players = players;
	}
	
	public LevelBank()
	{
		this(new HashMap<String, Level>());
	}
	
	public Level addPlayer(Player player) {
		return this.players.put(player.getName(), new Level());
	}
	
	public Level addPlayer(Player player, Level level) {
		return this.players.put(player.getName(), level);
	}
	
	public Level removePlayer(Player player) {
		return this.players.remove(player.getName());
	}
	
	public boolean contains(Player player) {
		return this.players.containsKey(player.getName());
	}
	
	public boolean contains(String playerName) {
		return this.players.containsKey(playerName);
	}
	
	public Level getLevelOf(Player player) {
		return this.players.get(player.getName());
	}
	
	public Level getLevelOf(String playerName) {
		return this.players.get(playerName);
	}
	
	public Map<String, Level> getPlayers() {
		return players;
	}
}
