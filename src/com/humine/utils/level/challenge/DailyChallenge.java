package com.humine.utils.level.challenge;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.humine.utils.Date;
import com.humine.utils.level.challenge.missions.Mission;
import com.humine.utils.level.challenge.missions.Recompense;

public class DailyChallenge
{
	private ArrayList<Mission>	missions;
	private String				date;
	private Recompense			recompense;

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
	
	public void showMissions(Player player) {
		String phrase = "";
		
		if(!this.missions.isEmpty()) 
		{
			for(Mission mission : this.missions) {
				phrase = "";
				phrase += "=================\n";

				if(mission.isDone())
					phrase += ChatColor.GREEN;
				else if(mission.isMissionPremium())
					phrase += ChatColor.GOLD;
				
				phrase += mission.getMissionName() + ": \n";
				phrase += (!mission.getDescription().equals("")) ? mission.getDescription() : "";
				phrase += "\n" + mission.getNumber() + " restant(s)";
				
				player.sendMessage(phrase);
			}
		}
		else
			player.sendMessage("Aucune mission aujourd'hui :(");
		
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

	public Recompense getRecompense()
	{
		return recompense;
	}

	public void setRecompense(Recompense recompense)
	{
		this.recompense = recompense;
	}
}
