package fr.humine.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.humine.main.ChallengeMain;
import humine.utils.cosmetiques.ParticleCosmetique;

public class AddParticleCosmetiqueCommand implements CommandExecutor
{

	private static final String COMMAND = "createparticlecosmetique";
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player)) {
			ChallengeMain.sendMessage(sender, "Vous devez etre un joueur");
			return false;
		}
		
		
		//ParticleCosmetique p = new ParticleCosmetique(name, itemShop, humisPrice, pixelPrice, particle, prestige);
		return false;
	}
}
