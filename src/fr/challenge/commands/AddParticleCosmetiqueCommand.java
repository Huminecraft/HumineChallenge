package fr.challenge.commands;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.challenge.main.ChallengeMain;
import fr.challenge.main.ChallengeUtils;
import humine.utils.Prestige;
import humine.utils.cosmetiques.ParticleCosmetique;

public class AddParticleCosmetiqueCommand implements CommandExecutor
{

	private static final String COMMAND = "/palierparticlecosmetique <name> <MaterialPresentation> <humis> <pixel> <particle> <id> [prestige]";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player)) {
			ChallengeMain.sendMessage(sender, "Vous devez etre un joueur");
			return false;
		}
		
		if(args.length < 6) {
			ChallengeMain.sendMessage(sender, "Argument insuffisant");
			ChallengeMain.sendMessage(sender, COMMAND);
			return false;
		}
		
		Material itemPresentation = ChallengeUtils.getItem(args[1]);
		if(itemPresentation == null) {
			ChallengeMain.sendMessage(sender, "MaterialPresentation invalide");
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[2])) {
			ChallengeMain.sendMessage(sender, "Humis invalide");
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[3])) {
			ChallengeMain.sendMessage(sender, "Pixel invalide");
			return false;
		}
		
		Particle particle = ChallengeUtils.getParticle(args[4]);
		if(particle == null) {
			ChallengeMain.sendMessage(sender, "Particle invalide");
			return false;
		}
		
		if(!ChallengeUtils.isNumber(args[5])) {
			ChallengeMain.sendMessage(sender, "ID invalide");
			return false;
		}
		
		Prestige prestige = null;
		if(args.length >= 7) {
			prestige = ChallengeUtils.getPrestige(args[6]);
		}
		
		if(prestige == null)
			prestige = Prestige.COMMUN;
		
		ParticleCosmetique p = new ParticleCosmetique(args[0].replace("_", " "), new ItemStack(itemPresentation), Integer.parseInt(args[2]), Integer.parseInt(args[3]), particle, prestige);
		p.setId(args[5]);
		
		ChallengeMain.getInstance().getBankCosmetique().addCosmetique(p);
		ChallengeMain.sendMessage(sender, "Cosmetique " + p.getId() + " ajoute !");
		return true;
	}
}
