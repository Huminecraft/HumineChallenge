package fr.humine.main;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
import fr.humine.commands.DailyChallengeLoadCommand;
import fr.humine.commands.HebdoChallengeLoadCommand;
import fr.humine.commands.PalierLoadCommand;
import fr.humine.commands.ShowChallengePassCommand;
import fr.humine.commands.ShowTokenCommand;
import fr.humine.commands.challenges.AddAwardCommand;
import fr.humine.commands.challenges.AddChallengeBiomeDiscoverCommand;
import fr.humine.commands.challenges.AddChallengeBreakBlockCommand;
import fr.humine.commands.challenges.AddChallengeKillCommand;
import fr.humine.commands.challenges.AddChallengePlaceBlockCommand;
import fr.humine.commands.challenges.ShowDailyChallengeCommand;
import fr.humine.commands.challenges.ShowHedboChallengeCommand;
import fr.humine.events.CreateChallengerEvent;
import fr.humine.events.QuitChallengerEvent;
import fr.humine.events.challenges.ChallengeBiomeDiscoverEvent;
import fr.humine.events.challenges.ChallengeBreakBlockEvent;
import fr.humine.events.challenges.ChallengeKillEvent;
import fr.humine.events.challenges.ChallengePlaceBlockEvent;
import fr.humine.events.challenges.GiveAwardEvent;
import fr.humine.events.challenges.GiveAwardPalierEvent;
import fr.humine.events.defaultpage.pageapplepay.ClickApplePayEvent;
import fr.humine.events.defaultpage.pageapplepay.ClickQuitButtonEvent;
import fr.humine.events.defaultpage.pageapplepay.OpenPageApplePayEvent;
import fr.humine.events.defaultpage.pageunlockpalier.ClickUnlockPalierEvent;
import fr.humine.events.defaultpage.pageunlockpalier.OpenPageUnlockPalierEvent;
import fr.humine.utils.BankChallenger;
import fr.humine.utils.BankCosmetique;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;
import fr.humine.utils.files.LoadSystem;
import fr.humine.utils.files.SaveSystem;
import fr.humine.utils.pass.ChallengePass;
import fr.humine.utils.pass.Page;

public class ChallengeMain extends JavaPlugin{

	private static ChallengeMain instance;
	private static ChallengePass passMain;
	private static List<Challenge> dailyChallenge;
	private static List<Challenge> HebdoChallenge;

	private LocalDate currentDailyChallengeDate;
	private LocalDate currentHebdoChallengeDate;
	private BankChallenger bankChallenger;
	private BankCosmetique bankCosmetique;

	public final File FILEPALIER = new File(getDataFolder(), "paliers.hc");
	public final File FOLDERDAILYCHALLENGE = new File(getDataFolder(), "DailyChallenge");
	public final File FOLDERHEBDOCHALLENGE = new File(getDataFolder(), "HebdoChallenge");
	public final File FOLDERCHALLENGER = new File(getDataFolder(), "Challengers");
	public final File FOLDERDATA = new File(getDataFolder(), "Data");
	
	@Override
	public void onEnable()
	{
		getDataFolder().mkdirs();
		
		instance = this;
		passMain = new ChallengePass();
		dailyChallenge = new ArrayList<>();
		HebdoChallenge = new ArrayList<>();
		
		this.bankChallenger = new BankChallenger();
		this.bankCosmetique = new BankCosmetique();

		files();
		commands();
		events();
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			File folder = new File(FOLDERCHALLENGER, player.getName());
			if(!folder.exists()) {
				Challenger challenger = new Challenger(player);
				bankChallenger.addChallenger(challenger);
				continue;
			}

			try
			{
				Challenger challenger = LoadSystem.loadChallenger(folder);
				bankChallenger.addChallenger(challenger);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run()
			{
				if(currentDailyChallengeDate.isBefore(LocalDate.now())) {
					currentDailyChallengeDate = LocalDate.now();
					getServer().dispatchCommand(getServer().getConsoleSender(), "dailyload");
				}
				
				if(LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY && currentHebdoChallengeDate.isBefore(LocalDate.now())) {
					currentHebdoChallengeDate = LocalDate.now();
					getServer().dispatchCommand(getServer().getConsoleSender(), "hebdoload");
				}
				
			}
		}, 0L, (60 * 20));
	}
	
	@Override
	public void onDisable()
	{
		getConfig().set("currentdailydate", this.currentDailyChallengeDate.toString());
		getConfig().set("currenthebdodate", this.currentHebdoChallengeDate.toString());
		saveConfig();
		
		for(Challenger challenger : bankChallenger.getChallengers()) {
			challenger.getLevelBar().dissociate();
			File folder = new File(FOLDERCHALLENGER, challenger.getName());
			folder.mkdirs();
			try
			{
				SaveSystem.saveChallenger(challenger, folder);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		File pageFolder = new File(FOLDERDATA, "Pages");
		File dailyFolder = new File(FOLDERDATA, "DailyChallenge");
		File hebdoFolder = new File(FOLDERDATA, "HebdoChallenge");
		
		try {
			SaveSystem.savePages(passMain.getPages(), pageFolder);
			SaveSystem.saveChallenges(dailyChallenge, dailyFolder);
			SaveSystem.saveChallenges(HebdoChallenge, hebdoFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void files() {
		try
		{
			if(!FILEPALIER.exists())
				FILEPALIER.createNewFile();
			
			if(!FOLDERDAILYCHALLENGE.exists())
				FOLDERDAILYCHALLENGE.mkdirs();
			
			if(!FOLDERHEBDOCHALLENGE.exists())
				FOLDERHEBDOCHALLENGE.mkdirs();
			
			if(!FOLDERCHALLENGER.exists())
				FOLDERCHALLENGER.mkdirs();
			
			if(!FOLDERCHALLENGER.exists())
				FOLDERCHALLENGER.mkdirs();
			
			if(!FOLDERDATA.exists())
				FOLDERDATA.mkdirs();
			
			if(getConfig().contains("currentdailydate")) {
				String date[] = getConfig().getString("currentdailydate").split("-");
				this.currentDailyChallengeDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
			}
			else {
				this.currentDailyChallengeDate = LocalDate.now();
			}
			
			if(getConfig().contains("currenthebdodate")) {
				String date[] = getConfig().getString("currenthebdodate").split("-");
				this.currentHebdoChallengeDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
			}
			else {
				this.currentHebdoChallengeDate = LocalDate.now();
			}
			
			File pageFolder = new File(FOLDERDATA, "Pages");
			File dailyFolder = new File(FOLDERDATA, "DailyChallenge");
			File hebdoFolder = new File(FOLDERDATA, "HebdoChallenge");
			
			if(pageFolder.exists()) {
				try {
						List<Page> pages = LoadSystem.loadPages(pageFolder);
						passMain.setPages(pages);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				
				
			}
			
			if(dailyFolder.exists()) {
				try {
					List<Challenge> dailyList = LoadSystem.loadChallenges(dailyFolder);
					dailyChallenge.addAll(dailyList);
				} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				}
			}
			
			if(dailyFolder.exists()) {
				try {
					List<Challenge> hebdoList = LoadSystem.loadChallenges(hebdoFolder);
					HebdoChallenge.addAll(hebdoList);
				} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				}
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void events() {
		this.getServer().getPluginManager().registerEvents(new CreateChallengerEvent(), this);
		this.getServer().getPluginManager().registerEvents(new QuitChallengerEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ChallengeKillEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ChallengePlaceBlockEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ChallengeBreakBlockEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ChallengeBiomeDiscoverEvent(), this);
		this.getServer().getPluginManager().registerEvents(new GiveAwardEvent(), this);		
	
		this.getServer().getPluginManager().registerEvents(new ClickApplePayEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ClickQuitButtonEvent(), this);
		this.getServer().getPluginManager().registerEvents(new OpenPageApplePayEvent(), this);
		this.getServer().getPluginManager().registerEvents(new GiveAwardPalierEvent(), this);
		
		this.getServer().getPluginManager().registerEvents(new fr.humine.events.defaultpage.pageunlockpalier.ClickQuitButtonEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ClickUnlockPalierEvent(), this);
		this.getServer().getPluginManager().registerEvents(new OpenPageUnlockPalierEvent(), this);
	}
	
	private void commands() {
		this.getCommand("token").setExecutor(new ShowTokenCommand());
		this.getCommand("pass").setExecutor(new ShowChallengePassCommand());
		this.getCommand("createpalier").setExecutor(new AddPalierCommand());
		this.getCommand("palierload").setExecutor(new PalierLoadCommand());
		this.getCommand("dailyload").setExecutor(new DailyChallengeLoadCommand());
		this.getCommand("hebdoload").setExecutor(new HebdoChallengeLoadCommand());
		this.getCommand("palierparticlecosmetique").setExecutor(new AddParticleCosmetiqueCommand());
		this.getCommand("paliermaterialhatcosmetique").setExecutor(new AddMaterialHatCosmetiqueCommand());
		this.getCommand("addchallengekill").setExecutor(new AddChallengeKillCommand());
		this.getCommand("addchallengeplaceblock").setExecutor(new AddChallengePlaceBlockCommand());
		this.getCommand("addchallengebreakblock").setExecutor(new AddChallengeBreakBlockCommand());
		this.getCommand("addchallengebiomediscover").setExecutor(new AddChallengeBiomeDiscoverCommand());
		this.getCommand("addAward").setExecutor(new AddAwardCommand());
		this.getCommand("dailychallenge").setExecutor(new ShowDailyChallengeCommand());
		this.getCommand("hebdochallenge").setExecutor(new ShowHedboChallengeCommand());
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
	
	public static List<Challenge> getHebdoChallenge()
	{
		return HebdoChallenge;
	}
	public LocalDate getCurrentDailyChallengeDate()
	{
		return currentDailyChallengeDate;
	}
	
	public LocalDate getCurrentHebdoChallengeDate()
	{
		return currentHebdoChallengeDate;
	}
}
