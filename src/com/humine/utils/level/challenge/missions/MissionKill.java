package com.humine.utils.level.challenge.missions;

import org.bukkit.entity.EntityType;

public class MissionKill extends Mission
{

	private static final long serialVersionUID = 2507963977235734153L;

	public MissionKill(String missionName, EntityType entityToKill)
	{
		this.setMissionName(missionName);
		this.setDescription("");
		this.setEntity(entityToKill);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.KILL);
	}

	public MissionKill(String missionName, String description, EntityType entityToKill)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setEntity(entityToKill);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.KILL);
	}

	public MissionKill(String missionName, String description, EntityType entityToKill, int numberToKill)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setEntity(entityToKill);
		this.setNumber(numberToKill);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.KILL);
	}

	public MissionKill(String missionName, String description, EntityType entityToKill, int numberToKill, boolean premium)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setEntity(entityToKill);
		this.setNumber(numberToKill);
		this.setDone(false);
		this.setMissionPremium(premium);
		this.setMissionType(MissionType.KILL);
	}
}
