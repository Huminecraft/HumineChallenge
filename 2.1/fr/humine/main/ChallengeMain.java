package fr.humine.main;

import java.io.File;
import java.io.IOException;

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
import fr.humine.events.main.CreateChallengerEvent;
import fr.humine.utils.BankChallenger;
import fr.humine.utils.BankCosmetique;
import fr.humine.utils.Challenger;
import fr.humine.utils.pass.ChallengePass;

public class ChallengeMain extends JavaPlugin{

	private static ChallengeMain instance;
	private static ChallengePass passMain;
	
	private BankChallenger bankChallenger;
	private BankCosmetique bankCosmetique;

	public final File FILEPALIER = new File(getDataFolder(), "paliers.hc");
	
	@Override
	public void onEnable()
	{
		getDataFolder().mkdirs();
		
		instance = this;
		passMain = new ChallengePass();
		
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
	}
	
	private void commands() {
		this.getCommand("token").setExecutor(new ShowTokenCommand());
		this.getCommand("pass").setExecutor(new ShowChallengePassCommand());
		this.getCommand("createpalier").setExecutor(new AddPalierCommand());
		this.getCommand("palierload").setExecutor(new PalierLoadCommand());
		this.getCommand("palierparticlecosmetique").setExecutor(new AddParticleCosmetiqueCommand());
		this.getCommand("paliermaterialhatcosmetique").setExecutor(new AddMaterialHatCosmetiqueCommand());
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
}
