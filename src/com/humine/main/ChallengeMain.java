package com.humine.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.humine.levels.events.PlayerJoinLevel;
import com.humine.levels.events.PlayerQuitLevel;
import com.humine.levels.util.Level;
import com.humine.levels.util.LevelManager;
import com.humine.tokens.util.Token;
import com.humine.tokens.util.TokenManager;
import com.humine.utils.level.challenge.DailyChallenge;
import com.humine.utils.level.challenge.commands.admin.CreateDailyChallengeCommand;
import com.humine.utils.level.challenge.commands.admin.CreateMissionCommand;
import com.humine.utils.level.challenge.commands.admin.InsertMissionIntoDailyCommand;
import com.humine.utils.level.challenge.commands.admin.ShowDailyChallengeCommand;
import com.humine.utils.level.challenge.commands.admin.ShowWaitingMissionListCommand;
import com.humine.utils.level.challenge.commands.all.GetTokenCommand;
import com.humine.utils.level.challenge.missions.Mission;

public class ChallengeMain extends JavaPlugin {
	
	private static ChallengeMain instance;
	
	private DailyChallenge challengeOfToday;
	private ArrayList<Mission> missionWaiting;
	private LevelManager levelManager;
	private TokenManager tokenManager;
	
	private File dailyChallengeFolder;

	@Override
	public void onEnable() {
		instance = this;
		this.missionWaiting = new ArrayList<Mission>();
		this.dailyChallengeFolder = new File(this.getDataFolder(), "DailyChallenge");
		
		try
		{
			initializeLevel();
			initializeToken();
		}
		catch (IOException e)
		{
			System.err.println("Erreur Initialisation !");
		}
		
		if(!dailyChallengeFolder.exists())
			dailyChallengeFolder.mkdirs();
		
		this.getServer().getPluginManager().registerEvents(new PlayerJoinLevel(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuitLevel(), this);
		
		this.getCommand("createmission").setExecutor(new CreateMissionCommand());
		this.getCommand("createdailychallenge").setExecutor(new CreateDailyChallengeCommand());
		this.getCommand("showmissions").setExecutor(new ShowWaitingMissionListCommand());
		this.getCommand("insertmissions").setExecutor(new InsertMissionIntoDailyCommand());
		this.getCommand("showdaily").setExecutor(new ShowDailyChallengeCommand());
		this.getCommand("token").setExecutor(new GetTokenCommand());
	}
	
	private void initializeLevel() throws IOException {
		this.levelManager = new LevelManager();
		File file = new File(this.getDataFolder(), "levels.yml");
		if(!file.exists()) {
			file.createNewFile();
		}
		else {
			HashMap<String, Level> levels = this.levelManager.getLevelsOnFile(file);
			this.levelManager.setLevels(levels);
		}
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(!this.levelManager.containsPlayer(player)) {
				this.levelManager.addPlayer(player);
				Level level = new Level();
				this.levelManager.addBarBoss(player, level);
			}
			else
				this.levelManager.addBarBoss(player, this.levelManager.getLevel(player));
		}
	}
	
	private void initializeToken() throws IOException {
		this.tokenManager = new TokenManager();
		File file = new File(this.getDataFolder(), "tokens.yml");
		if(!file.exists()) {
			file.createNewFile();
		}
		else {
			HashMap<String, Token> tokens = this.tokenManager.getLevelsOnFile(file);
			this.tokenManager.setTokens(tokens);
		}
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(!this.tokenManager.containsPlayer(player))
				this.tokenManager.addPlayer(player);
		}
	}
	
	
	
	@Override
	public void onDisable()
	{
		try
		{
			clearLevels();
			clearTokens();
			this.missionWaiting.clear();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void clearLevels() throws IOException {
		File file = new File(this.getDataFolder(), "levels.yml");
		this.levelManager.saveLevels(file);
		this.levelManager.clearBarBossForAll();
		this.levelManager.clearLevels();
	}
	
	private void clearTokens() throws IOException {
		File file = new File(this.getDataFolder(), "tokens.yml");
		this.tokenManager.saveTokens(file);
		this.tokenManager.clearTokens();
	}

	public static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.AQUA + "[HumineChallenge] " + ChatColor.RESET + message);
	}

	public static ChallengeMain getInstance() {
		return instance;
	}

	public DailyChallenge getChallengeOfToday()
	{
		return challengeOfToday;
	}

	public void setChallengeOfToday(DailyChallenge challengeOfToday)
	{
		this.challengeOfToday = challengeOfToday;
	}

	public File getDailyChallengeFolder()
	{
		return dailyChallengeFolder;
	}

	public ArrayList<Mission> getMissionWaiting()
	{
		return missionWaiting;
	}

	public LevelManager getLevelManager()
	{
		return levelManager;
	}

	public void setLevelManager(LevelManager levelManager)
	{
		this.levelManager = levelManager;
	}

	public TokenManager getTokenManager()
	{
		return tokenManager;
	}

	public void setTokenManager(TokenManager tokenManager)
	{
		this.tokenManager = tokenManager;
	}
}
