package com.humine.utils.level.challenge.missions;

import org.bukkit.entity.Entity;

public class MissionAdoption extends Mission
{
	
	public MissionAdoption(String missionName, Entity entityToAdopt)
	{
		this.setMissionName(missionName);
		this.setDescription("");
		this.setEntity(entityToAdopt);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.ADOPTION);
	}

	public MissionAdoption(String missionName, String description, Entity entityToAdopt)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setEntity(entityToAdopt);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.ADOPTION);
	}

	public MissionAdoption(String missionName, String description, Entity entityToAdopt, int numberToAdopt)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setEntity(entityToAdopt);
		this.setNumber(numberToAdopt);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.ADOPTION);
	}

	public MissionAdoption(String missionName, String description, Entity entityToAdopt, int numberToAdopt, boolean premium)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setEntity(entityToAdopt);
		this.setNumber(numberToAdopt);
		this.setDone(false);
		this.setMissionPremium(premium);
		this.setMissionType(MissionType.ADOPTION);
	}
}
