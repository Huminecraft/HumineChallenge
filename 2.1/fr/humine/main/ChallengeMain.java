package fr.humine.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.humine.commands.AddMaterialHatCosmetiqueCommand;
import fr.humine.commands.AddPalierCommand;
import fr.humine.commands.AddParticleCosmetiqueCommand;
import fr.humine.commands.PalierLoadCommand;
import fr.humine.commands.ShowChallengePassCommand;
import fr.humine.commands.ShowTokenCommand;
import fr.humine.commands.challenges.AddAwardCommand;
import fr.humine.commands.challenges.AddChallengeBiomeDiscoverCommand;
import fr.humine.commands.challenges.AddChallengeBreakBlockCommand;
import fr.humine.commands.challenges.AddChallengeKillCommand;
import fr.humine.commands.challenges.AddChallengePlaceBlockCommand;
import fr.humine.commands.challenges.ShowDailyChallengeCommand;
import fr.humine.events.CreateChallengerEvent;
import fr.humine.events.challenges.ChallengeBiomeDiscoverEvent;
import fr.humine.events.challenges.ChallengeBreakBlockEvent;
import fr.humine.events.challenges.ChallengeKillEvent;
import fr.humine.events.challenges.ChallengePlaceBlockEvent;
import fr.humine.events.challenges.GiveAwardEvent;
import fr.humine.events.challenges.GiveAwardPalierEvent;
import fr.humine.events.pageapplepay.ClickApplePayEvent;
import fr.humine.events.pageapplepay.ClickQuitButtonEvent;
import fr.humine.events.pageapplepay.OpenPageApplePayEvent;
import fr.humine.utils.BankChallenger;
import fr.humine.utils.BankCosmetique;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;
import fr.humine.utils.pass.ChallengePass;

public class ChallengeMain extends JavaPlugin{

	private static ChallengeMain instance;
	private static ChallengePass passMain;
	private static List<Challenge> dailyChallenge;
	
	private BankChallenger bankChallenger;
	private BankCosmetique bankCosmetique;

	public final File FILEPALIER = new File(getDataFolder(), "paliers.hc");
	
	@Override
	public void onEnable()
	{
		getDataFolder().mkdirs();
		
		instance = this;
		passMain = new ChallengePass();
		dailyChallenge = new ArrayList<>();
		
		this.bankChallenger = new BankChallenger();
		this.bankCosmetique = new BankCosmetique();

		for(Player player : Bukkit.getOnlinePlayers())
			bankChallenger.addChallenger(new Challenger(player));
		
		files();
		commands();
		events();
	}
	
	@Override
	public void onDisable()
	{
		for(Challenger challenger : bankChallenger.getChallengers())
			challenger.getLevelBar().dissociate();
	}
	
	private void files() {
		try
		{
			if(!FILEPALIER.exists())
				FILEPALIER.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void events() {
		this.getServer().getPluginManager().registerEvents(new CreateChallengerEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ChallengeKillEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ChallengePlaceBlockEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ChallengeBreakBlockEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ChallengeBiomeDiscoverEvent(), this);
		this.getServer().getPluginManager().registerEvents(new GiveAwardEvent(), this);		
	
		this.getServer().getPluginManager().registerEvents(new ClickApplePayEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ClickQuitButtonEvent(), this);
		this.getServer().getPluginManager().registerEvents(new OpenPageApplePayEvent(), this);
		this.getServer().getPluginManager().registerEvents(new GiveAwardPalierEvent(), this);
	}
	
	private void commands() {
		this.getCommand("token").setExecutor(new ShowTokenCommand());
		this.getCommand("pass").setExecutor(new ShowChallengePassCommand());
		this.getCommand("createpalier").setExecutor(new AddPalierCommand());
		this.getCommand("palierload").setExecutor(new PalierLoadCommand());
		this.getCommand("palierparticlecosmetique").setExecutor(new AddParticleCosmetiqueCommand());
		this.getCommand("paliermaterialhatcosmetique").setExecutor(new AddMaterialHatCosmetiqueCommand());
		this.getCommand("addchallengekill").setExecutor(new AddChallengeKillCommand());
		this.getCommand("addchallengeplaceblock").setExecutor(new AddChallengePlaceBlockCommand());
		this.getCommand("addchallengebreakblock").setExecutor(new AddChallengeBreakBlockCommand());
		this.getCommand("addchallengebiomediscover").setExecutor(new AddChallengeBiomeDiscoverCommand());
		this.getCommand("addAward").setExecutor(new AddAwardCommand());
		this.getCommand("dailychallenge").setExecutor(new ShowDailyChallengeCommand());
	}
	
	public static void sendMessage(CommandSender sender, String message) {
		String prefix = ChatColor.AQUA + "[Challenge]" + ChatColor.RESET;
		sender.sendMessage(prefix + " " + message);
	}
	
	public static ChallengeMain getInstance() {
		return instance;
	}
	
	public static ChallengePass getPassMain()
	{
		return passMain;
	}
	
	public BankChallenger getBankChallenger() {
		return bankChallenger;
	}
	
	public BankCosmetique getBankCosmetique() {
		return bankCosmetique;
	}
	
	public static List<Challenge> getDailyChallenge() {
		return dailyChallenge;
	}
}
