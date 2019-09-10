package fr.humine.pass.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;

public class OpenChallengeShopCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("Tu dois etre un joueur");
			return false;
		}
		
		Player player = (Player) sender;
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(player);
		
		if(challenger == null)
			return false;
		
		challenger.getShop().openShop();
		return true;
	}

}
