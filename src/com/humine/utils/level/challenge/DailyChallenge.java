package com.humine.utils.level.challenge;

import java.util.ArrayList;

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
