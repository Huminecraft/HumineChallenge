package fr.humine.commands.bankcosmetique;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.humine.ChallengeMain;
import humine.utils.cosmetiques.Cosmetique;

public class BankCosmetiqueCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<Cosmetique> cosmetiques;
		if(args.length >= 1)
			cosmetiques = search(args[0]);
		else
			cosmetiques = ChallengeMain.getInstance().getBankCosmetique().getCosmetiques();
		
		if(cosmetiques.isEmpty()) {
			sender.sendMessage("Aucun cosmetique");
			return true;
		}
		
		for(Cosmetique c : cosmetiques) {
			sender.sendMessage(ChatColor.GOLD + c.toString());
			sender.sendMessage("===========");
		}
		return true;
	}

	private List<Cosmetique> search(String str) {
		List<Cosmetique> cosmetiques = new ArrayList<>();
		
		for(Cosmetique c : ChallengeMain.getInstance().getBankCosmetique().getCosmetiques()) {
			if(c.getName().contains(str))
				cosmetiques.add(c);
			else if(c.getId().contains(str))
				cosmetiques.add(c);
		}
		
		return cosmetiques;
	}

}
