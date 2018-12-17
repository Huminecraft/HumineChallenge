package com.humine.utils.level.challenge.missions;

import org.bukkit.entity.EntityType;

public class MissionAdoption extends Mission
{
	
	private static final long serialVersionUID = 4081519091688111744L;

	public MissionAdoption(String missionName, EntityType entityToAdopt)
	{
		this.setMissionName(missionName);
		this.setDescription("");
		this.setEntity(entityToAdopt);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.ADOPTION);
	}

	public MissionAdoption(String missionName, String description, EntityType entityToAdopt)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setEntity(entityToAdopt);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.ADOPTION);
	}

	public MissionAdoption(String missionName, String description, EntityType entityToAdopt, int numberToAdopt)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setEntity(entityToAdopt);
		this.setNumber(numberToAdopt);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.ADOPTION);
	}

	public MissionAdoption(String missionName, String description, EntityType entityToAdopt, int numberToAdopt, boolean premium)
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
