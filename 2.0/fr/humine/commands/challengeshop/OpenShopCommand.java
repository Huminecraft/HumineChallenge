package fr.humine.commands.challengeshop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.humine.ChallengeMain;

public class OpenShopCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Vous devez etre un joueur");
			return false;
		}
		
		Player player = (Player) sender;
		ChallengeMain.getInstance().getBankChallengeShop().openShop(player);
		return true;
	}
}
