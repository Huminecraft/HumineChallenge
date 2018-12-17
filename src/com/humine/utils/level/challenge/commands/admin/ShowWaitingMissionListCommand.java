package com.humine.utils.level.challenge.commands.admin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.humine.main.ChallengeMain;
import com.humine.utils.level.challenge.missions.Mission;

public class ShowWaitingMissionListCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			showMissions((Player) sender);
			return true;
		}
		return false;
	}

	public void showMissions(Player player)
	{
		if (!ChallengeMain.getInstance().getMissionWaiting().isEmpty())
		{
			for (Mission mission : ChallengeMain.getInstance().getMissionWaiting())
			{
				player.sendMessage("=================");

				if (mission.isDone())
					message(player, mission, ChatColor.GREEN);
				else if (mission.isMissionPremium())
					message(player, mission, ChatColor.GOLD);
				else
					message(player, mission, ChatColor.RESET);

			}
		}
		else
			ChallengeMain.sendMessage(player, "Aucune mission temporaire :(");

	}
	
	private void message(Player player, Mission mission, ChatColor color) {
		player.sendMessage(color + mission.getMissionName() + ":   type: " + mission.getMissionType());
		
		if (!mission.getDescription().equals(""))
			player.sendMessage(color + mission.getDescription());

		player.sendMessage(color + "" + mission.getNumber() + " restant(s)");
	}

}
