package fr.humine.pass.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class OpenChallengeShopCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3)
	{
		return false;
	}

//	@Override
//	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//		
//		if(!(sender instanceof Player)) {
//			sender.sendMessage("Tu dois etre un joueur");
//			return false;
//		}
//		
//		Player player = (Player) sender;
//		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(player);
//		
//		if(challenger == null)
//			return false;
//		
//		challenger.getShop().openShop();
//		return true;
//	}

}
