package com.humine.utils.level.challenge.commands.all;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.humine.main.ChallengeMain;

public class GetTokenCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(ChallengeMain.getInstance().getTokenManager().containsPlayer(player)) {
				int token = ChallengeMain.getInstance().getTokenManager().getToken(player).getToken();
				ChallengeMain.sendMessage(player, "Nombre de token: " + ChatColor.GREEN + token);
				return true;
			}
			else
				ChallengeMain.sendMessage(player, "Vous avez pas de token !");
		}
		
		return false;
	}

}
