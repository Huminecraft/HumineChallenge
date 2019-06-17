package fr.humine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.humine.commands.bankcosmetique.BankCosmetiqueCommand;
import fr.humine.commands.challengeshop.CreatePalierCommand;
import fr.humine.commands.challengeshop.OpenShopCommand;
import fr.humine.events.challengeshop.CloseChallengeShopEvent;
import fr.humine.events.challengeshop.NextButtonEvent;
import fr.humine.events.challengeshop.PreviousButtonEvent;
import fr.humine.events.challengeshop.QuitButtonEvent;
import fr.humine.utils.bankid.BankCosmetique;
import fr.humine.utils.bankid.BankItemStack;
import fr.humine.utils.challenge.date.DailyChallenge;
import fr.humine.utils.challenge.date.WeeklyChallenge;
import fr.humine.utils.exceptions.SaveFileException;
import fr.humine.utils.exceptions.SettingMissingException;
import fr.humine.utils.levels.LevelBank;
import fr.humine.utils.players.BankChallenger;
import fr.humine.utils.players.Challenger;
import fr.humine.utils.shop.BankChallengeShop;
import fr.humine.utils.shop.ChallengeShop;
import fr.humine.utils.token.TokenBank;

public class ChallengeMain extends JavaPlugin{
	
	private static ChallengeMain instance;
	
	private BankCosmetique bankCosmetique;
	private TokenBank bankToken;
	private BankChallengeShop bankChallengeShop;
	private LevelBank bankLevel;
	private BankChallenger bankChallenger;
	private BankItemStack bankItemStack;
	
	private DailyChallenge currentDailyChallenge;
	private WeeklyChallenge currentWeeklyChallenge;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private final File bankCosmetiqueFolder = new File(getDataFolder(), "BankCosmetique");
	private final File bankTokenFile = new File(getDataFolder(), "BankToken.txt");
	private final File challengeShopFile = new File(getDataFolder(), "ChallengeShop.txt");
	private final File bankLevelFile = new File(getDataFolder(), "BankLevel.txt");
	private final File currentDailyChallengeFile = new File(getDataFolder(), "DailyChallenge.txt");
	private final File currentWeeklyChallengeFile = new File(getDataFolder(), "WeeklyChallenge.txt");
	private final File ChallengerFolder = new File(getDataFolder(), "Challengers");
	private final File bankChallengerFile = new File(getDataFolder(), "BankChallenger.txt");
	private final File bankItemStackFile = new File(getDataFolder(), "BankItemStack.yml");
	
	@Override
	public void onEnable() {
		if(!getDataFolder().exists())
			getDataFolder().mkdir();
		
		try {
			loadBankCosmetique(bankCosmetiqueFolder);
			loadBankItemStack(bankItemStackFile);
			loadBankToken(bankTokenFile);
			loadBankChallengeShop(challengeShopFile);
			
			
			this.bankLevel = loadBankLevel(bankLevelFile);
			this.currentDailyChallenge = loadCurrentDailyChallenge(currentDailyChallengeFile);
			this.currentWeeklyChallenge = loadCurrentWeeklyChallenge(currentWeeklyChallengeFile);
			this.bankChallenger = loadBankChallenger(bankChallengerFile);
		} catch (FileNotFoundException | SettingMissingException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initializeCommands();
		initializeEvents();
	}
	
	private void initializeEvents() {
		// debut event challenge shop
		this.getServer().getPluginManager().registerEvents(new CloseChallengeShopEvent(), this);
		this.getServer().getPluginManager().registerEvents(new NextButtonEvent(), this);
		this.getServer().getPluginManager().registerEvents(new PreviousButtonEvent(), this);
		this.getServer().getPluginManager().registerEvents(new QuitButtonEvent(), this);
		// fin event challenge shop
	}

	private void initializeCommands() {
		this.getCommand("createpalier").setExecutor(new CreatePalierCommand());
		this.getCommand("challengeshop").setExecutor(new OpenShopCommand());
		this.getCommand("bankcosmetique").setExecutor(new BankCosmetiqueCommand());
	}

	private void loadBankItemStack(File file) throws FileNotFoundException, SettingMissingException {
		if(!file.exists())
			this.bankItemStack = new BankItemStack();
		
		BankItemStack bank = new BankItemStack();
		bank.load(file);
		this.bankItemStack = bank;
	}

	private BankChallenger loadBankChallenger(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		if(!file.exists())
			return new BankChallenger();
		
		in = new ObjectInputStream(new FileInputStream(file));
		return (BankChallenger) in.readObject();
	}

	private WeeklyChallenge loadCurrentWeeklyChallenge(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		if(!file.exists())
			return null;
		
		in = new ObjectInputStream(new FileInputStream(file));
		return (WeeklyChallenge) in.readObject();
	}

	private DailyChallenge loadCurrentDailyChallenge(File file) throws ClassNotFoundException, IOException {
		if(!file.exists())
			return null;
		
		in = new ObjectInputStream(new FileInputStream(file));
		return (DailyChallenge) in.readObject();
	}

	private LevelBank loadBankLevel(File file) throws ClassNotFoundException, IOException {
		if(!file.exists())
			return new LevelBank();
		
		in = new ObjectInputStream(new FileInputStream(file));
		return (LevelBank) in.readObject();
	}


	private void loadBankChallengeShop(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		if(!file.exists()) {
			ChallengeShop defaultShop = new ChallengeShop("ChallengeShop", new Challenger());
			BankChallengeShop bank = new BankChallengeShop(defaultShop);
			this.bankChallengeShop = bank;
		}
		
		in = new ObjectInputStream(new FileInputStream(file));
		ChallengeShop shop = (ChallengeShop) in.readObject();
		this.bankChallengeShop = new BankChallengeShop(shop);
	}


	private void loadBankToken(File file) throws ClassNotFoundException, IOException {
		if(!file.exists())
			this.bankToken = new TokenBank("Token");
		
		in = new ObjectInputStream(new FileInputStream(file));
		this.bankToken = (TokenBank) in.readObject();
	}


	private void loadBankCosmetique(File folder) throws FileNotFoundException, SettingMissingException {
		if(!folder.exists())
			this.bankCosmetique = new BankCosmetique();
		
		BankCosmetique c = new BankCosmetique();
		c.load(folder);
		this.bankCosmetique = c;
	}


	@Override
	public void onDisable() {
		try {
			saveBankCosmetique(bankCosmetiqueFolder);
			saveBankItemStack(bankItemStackFile);
			saveBankToken(bankTokenFile);
			saveChallengeShop(challengeShopFile);
			saveBankLevel(bankLevelFile);
			saveCurrentDailyChallenge(currentDailyChallengeFile);
			saveCurrentWeeklyChallenge(currentWeeklyChallengeFile);
			saveBankChallenger(bankChallengerFile);
		} catch (SaveFileException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveBankItemStack(File file) throws IOException, SaveFileException {
		if(this.bankItemStack != null) {
			this.bankItemStack.save(file);
		}
	}

	private void saveBankChallenger(File file) throws FileNotFoundException, IOException {
		if(!file.exists())
			file.createNewFile();
		
		out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(this.bankChallenger);
		out.flush();
	}

	private void saveCurrentWeeklyChallenge(File file) throws FileNotFoundException, IOException {
		if(!file.exists())
			file.createNewFile();
		
		out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(this.currentWeeklyChallenge);
		out.flush();
	}

	private void saveCurrentDailyChallenge(File file) throws IOException {
		if(!file.exists())
			file.createNewFile();
		
		out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(this.currentDailyChallenge);
		out.flush();
	}

	private void saveBankLevel(File file) throws FileNotFoundException, IOException {
		if(!file.exists())
			file.createNewFile();
		
		out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(this.bankLevel);
		out.flush();
	}

	private void saveChallengeShop(File file) throws FileNotFoundException, IOException {
		if(!file.exists())
			file.createNewFile();
		
		out = new ObjectOutputStream(new FileOutputStream(file));
		if(this.bankChallengeShop == null)
			this.bankChallengeShop = new BankChallengeShop(new ChallengeShop("ChallengeShop", new Challenger()));
		
		ChallengeShop shop = this.bankChallengeShop.getDefaultChallengeShop();
		if(shop == null) {
			shop = new ChallengeShop("ChallengeShop", new Challenger("null"));
			Bukkit.broadcastMessage("DEBUG SAVE NULL");
			out.writeObject(shop);
		} else {
			Bukkit.broadcastMessage("DEBUG SAVE NOT NULL");
			out.writeObject(shop);
		}
		
		out.flush();
	}

	private void saveBankToken(File file) throws IOException {
		if(!file.exists())
			file.createNewFile();
		
		out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(this.bankToken);
		out.flush();
	}

	private void saveBankCosmetique(File folder) throws SaveFileException {
		if(this.bankCosmetique != null) {
			this.bankCosmetique.save(folder);
		}
	}

	public static ChallengeMain getInstance() {
		return instance;
	}

	public BankCosmetique getBankCosmetique() {
		return bankCosmetique;
	}

	public void setBankCosmetique(BankCosmetique bankCosmetique) {
		this.bankCosmetique = bankCosmetique;
	}

	public TokenBank getBankToken() {
		return bankToken;
	}

	public void setBankToken(TokenBank bankToken) {
		this.bankToken = bankToken;
	}

	public BankChallengeShop getBankChallengeShop() {
		return bankChallengeShop;
	}

	public void setBankChallengeShop(BankChallengeShop bankChallengeShop) {
		this.bankChallengeShop = bankChallengeShop;
	}

	public LevelBank getBankLevel() {
		return bankLevel;
	}

	public void setBankLevel(LevelBank bankLevel) {
		this.bankLevel = bankLevel;
	}

	public DailyChallenge getCurrentDailyChallenge() {
		return currentDailyChallenge;
	}

	public void setCurrentDailyChallenge(DailyChallenge currentDailyChallenge) {
		this.currentDailyChallenge = currentDailyChallenge;
	}

	public WeeklyChallenge getCurrentWeeklyChallenge() {
		return currentWeeklyChallenge;
	}

	public void setCurrentWeeklyChallenge(WeeklyChallenge currentWeeklyChallenge) {
		this.currentWeeklyChallenge = currentWeeklyChallenge;
	}

	public File getBankCosmetiqueFolder() {
		return bankCosmetiqueFolder;
	}

	public File getBankTokenFile() {
		return bankTokenFile;
	}

	public File getChallengeShopFile() {
		return challengeShopFile;
	}

	public File getBankLevelFile() {
		return bankLevelFile;
	}

	public File getCurrentDailyChallengeFile() {
		return currentDailyChallengeFile;
	}

	public File getCurrentWeeklyChallengeFile() {
		return currentWeeklyChallengeFile;
	}

	public File getChallengerFolder() {
		return ChallengerFolder;
	}

	public BankChallenger getBankChallenger() {
		return bankChallenger;
	}

	public void setBankChallenger(BankChallenger bankChallenger) {
		this.bankChallenger = bankChallenger;
	}

	public File getBankChallengerFile() {
		return bankChallengerFile;
	}

	public BankItemStack getBankItemStack() {
		return bankItemStack;
	}

	public void setBankItemStack(BankItemStack bankItemStack) {
		this.bankItemStack = bankItemStack;
	}

	public File getBankItemStackFile() {
		return bankItemStackFile;
	}
}