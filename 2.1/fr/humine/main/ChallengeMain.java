package fr.humine.main;

import org.bukkit.plugin.java.JavaPlugin;

import fr.humine.utils.BankChallenger;
import fr.humine.utils.BankCosmetique;

public class ChallengeMain extends JavaPlugin{

	private static ChallengeMain instance;
	private BankChallenger bankChallenger;
	private BankCosmetique bankCosmetique;
	
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
