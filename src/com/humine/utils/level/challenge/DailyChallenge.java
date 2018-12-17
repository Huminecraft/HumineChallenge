package com.humine.utils.level.challenge;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.humine.main.ChallengeMain;
import com.humine.utils.Date;
import com.humine.utils.level.challenge.missions.Mission;

public class DailyChallenge implements Serializable
{
	private static final long	serialVersionUID	= -3792436731689471711L;

	private ArrayList<Mission>	missions;
	private String				date;

	public DailyChallenge()
	{
		missions = new ArrayList<Mission>();
		this.date = Date.getDateTodayInString();
	}

	public DailyChallenge(String date)
	{
		missions = new ArrayList<Mission>();
		this.date = date;
	}

	public void showMissions(Player player)
	{
		if (!ChallengeMain.getInstance().getMissionWaiting().isEmpty())
		{
			for (Mission mission : this.missions)
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

	private void message(Player player, Mission mission, ChatColor color)
	{
		player.sendMessage(color + mission.getMissionName() + ":   type: " + mission.getMissionType());

		if (!mission.getDescription().equals(""))
			player.sendMessage(color + mission.getDescription());

		player.sendMessage(color + "" + mission.getNumber() + " restant(s)");
	}

	public ArrayList<Mission> getMissions()
	{
		return missions;
	}

	public void setMissions(ArrayList<Mission> missions)
	{
		this.missions = missions;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

}
