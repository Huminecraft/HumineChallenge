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
import fr.humine.commands.ShowHebdoPageCommand;
import fr.humine.commands.ShowTokenCommand;
import fr.humine.commands.challenges.AddAwardCommand;
import fr.humine.commands.challenges.AddChallengeBiomeDiscoverCommand;
import fr.humine.commands.challenges.AddChallengeBreakBlockCommand;
import fr.humine.commands.challenges.AddChallengeKillCommand;
import fr.humine.commands.challenges.AddChallengePlaceBlockCommand;
import fr.humine.commands.challenges.ShowDailyChallengeCommand;
import fr.humine.commands.challenges.ShowHedboChallengeCommand;
import fr.humine.events.ChallengerQuitPassEvent;
import fr.humine.events.CreateChallengerEvent;
import fr.humine.events.QuitChallengerEvent;
import fr.humine.events.challenges.ChallengeBiomeDiscoverEvent;
import fr.humine.events.challenges.ChallengeBreakBlockEvent;
import fr.humine.events.challenges.ChallengeKillEvent;
import fr.humine.events.challenges.ChallengePlaceBlockEvent;
import fr.humine.events.challenges.GiveAwardEvent;
import fr.humine.events.challenges.GiveAwardPalierEvent;
import fr.humine.events.defaultpage.hebdopage.ClickHebdoItemEvent;
import fr.humine.events.defaultpage.pageapplepay.ClickApplePayEvent;
import fr.humine.events.defaultpage.pageapplepay.ClickQuitButtonEvent;
import fr.humine.events.defaultpage.pageapplepay.OpenPageApplePayEvent;
import fr.humine.events.defaultpage.pageunlockpalier.ClickUnlockPalierEvent;
import fr.humine.events.defaultpage.pageunlockpalier.OpenPageUnlockPalierEvent;
import fr.humine.utils.BankChallenger;
import fr.humine.utils.BankCosmetique;
import fr.humine.utils.Challenger;
import fr.humine.utils.challenges.Challenge;
import fr.humine.utils.defaultpage.PageApplePay;
import fr.humine.utils.files.LoadSystem;
import fr.humine.utils.files.SaveSystem;
import fr.humine.utils.pass.ChallengePass;
import fr.humine.utils.pass.Page;
import humine.utils.cosmetiques.Cosmetique;

public class ChallengeMain extends JavaPlugin{

	private static ChallengeMain instance;
	private static ChallengePass passMain;
	private static List<Challenge> dailyChallenge;
	private static List<Challenge> HebdoChallenge;
	
	private int currentWeek;
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
		currentWeek = 1;
		
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
					File hebdoData = new File(FOLDERDATA, "HebdoChallenge");
					try {
						SaveSystem.saveWeekHebdoChallenge(HebdoChallenge, hebdoData, currentWeek);
					}
					catch (IOException e1) {
						e1.printStackTrace();
					}
					for(Challenger challenger : bankChallenger.getChallengers()) {
						File f = new File(challenger.getChallengerFolder(), "HebdoChallenge");
						try {
							if(challenger.hasPremium())
								SaveSystem.saveWeekHebdoChallenge(challenger.getHebdoChallenge(), f, challenger.getCurrentHebdoWeek());
							else
								SaveSystem.saveWeekHebdoChallenge(HebdoChallenge, f, currentWeek);
						}
						catch (IOException e) {
							e.printStackTrace();
						}
						challenger.setCurrentHebdoWeek(currentWeek + 1); 
					}
					
					getServer().dispatchCommand(getServer().getConsoleSender(), "hebdoload");
					currentWeek++;
				}
				
			}
		}, 0L, (60 * 20));
	}
	
	@Override
	public void onDisable()
	{
		getConfig().set("currentdailydate", this.currentDailyChallengeDate.toString());
		getConfig().set("currenthebdodate", this.currentHebdoChallengeDate.toString());
		getConfig().set("semaine", this.currentWeek);
		if(!getConfig().contains("huminepass"))
			getConfig().set("huminepass", 0);
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
		File cosmetiqueFolder = new File(FOLDERDATA, "Cosmetiques");
		
		try {
			SaveSystem.savePages(passMain.getPages(), pageFolder);
			SaveSystem.saveChallenges(dailyChallenge, dailyFolder);
			SaveSystem.saveWeekHebdoChallenge(HebdoChallenge, hebdoFolder, currentWeek);
			SaveSystem.saveCosmetiques(bankCosmetique.getCosmetiques(), cosmetiqueFolder);
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
			
			if(getConfig().contains("huminepass")) {
				PageApplePay.PRIZE = getConfig().getInt("huminepass");
			}
			else {
				PageApplePay.PRIZE = 0;
			}
			
			if(getConfig().contains("semaine")) {
				this.currentWeek = getConfig().getInt("semaine");
			}
			
			File pageFolder = new File(FOLDERDATA, "Pages");
			File dailyFolder = new File(FOLDERDATA, "DailyChallenge");
			File hebdoFolder = new File(FOLDERDATA, "HebdoChallenge");
			File cosmetiqueFolder = new File(FOLDERDATA, "Cosmetiques");
			try {
				if(pageFolder.exists()) {
					List<Page> pages = LoadSystem.loadPages(pageFolder);
					passMain.setPages(pages);
				}
				
				if(dailyFolder.exists()) {
					List<Challenge> dailyList = LoadSystem.loadChallenges(dailyFolder);
					dailyChallenge.addAll(dailyList);
				}
				
				if(hebdoFolder.exists()) {
					List<Challenge> hebdoList = LoadSystem.loadWeekHebdoChallenge(hebdoFolder, currentWeek);
					HebdoChallenge.addAll(hebdoList);
				}
				
				if(cosmetiqueFolder.exists()) {
					List<Cosmetique> cosmetiqueList = LoadSystem.loadCosmetique(cosmetiqueFolder);
					bankCosmetique.setCosmetiques(cosmetiqueList);
				}
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
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
		this.getServer().getPluginManager().registerEvents(new ChallengerQuitPassEvent(), this);
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
		
		this.getServer().getPluginManager().registerEvents(new fr.humine.events.defaultpage.hebdopage.ClickQuitButtonEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ClickHebdoItemEvent(), this);
	}
	
	private void commands() {
		this.getCommand("token").setExecutor(new ShowTokenCommand());
		this.getCommand("pass").setExecutor(new ShowChallengePassCommand());
		this.getCommand("hebdomenu").setExecutor(new ShowHebdoPageCommand());
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
	
	public int getCurrentWeek()
	{
		return currentWeek;
	}
}
