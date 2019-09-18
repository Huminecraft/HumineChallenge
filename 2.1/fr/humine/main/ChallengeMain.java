package fr.humine.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.humine.commands.ShowChallengePassCommand;
import fr.humine.commands.ShowTokenCommand;
import fr.humine.events.main.CreateChallengerEvent;
import fr.humine.utils.BankChallenger;
import fr.humine.utils.BankCosmetique;
import fr.humine.utils.Challenger;

public class ChallengeMain extends JavaPlugin{

	private static ChallengeMain instance;
	private BankChallenger bankChallenger;
	private BankCosmetique bankCosmetique;
	
	@Override
	public void onEnable()
	{
		instance = this;
		this.bankChallenger = new BankChallenger();
		this.bankCosmetique = new BankCosmetique();
	
		for(Player player : Bukkit.getOnlinePlayers())
			bankChallenger.addChallenger(new Challenger(player));
		
		commands();
		events();
	}
	
	private void events() {
		this.getServer().getPluginManager().registerEvents(new CreateChallengerEvent(), this);
	}
	
	private void commands() {
		this.getCommand("token").setExecutor(new ShowTokenCommand());
		this.getCommand("pass").setExecutor(new ShowChallengePassCommand());
	}
	
	public static void sendMessage(CommandSender sender, String message) {
		String prefix = ChatColor.AQUA + "[Challenge]" + ChatColor.RESET;
		sender.sendMessage(prefix + " " + message);
	}
	
	public static ChallengeMain getInstance() {
		return instance;
	}
	
	public BankChallenger getBankChallenger() {
		return bankChallenger;
	}
	
	public BankCosmetique getBankCosmetique() {
		return bankCosmetique;
	}
}
