package com.humine.utils.level.challenge.missions;

import org.bukkit.entity.Entity;

public class MissionKill extends Mission
{

	public MissionKill(String missionName, Entity entityToKill)
	{
		this.setMissionName(missionName);
		this.setDescription("");
		this.setEntity(entityToKill);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.KILL);
	}

	public MissionKill(String missionName, String description, Entity entityToKill)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setEntity(entityToKill);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.KILL);
	}

	public MissionKill(String missionName, String description, Entity entityToKill, int numberToKill)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setEntity(entityToKill);
		this.setNumber(numberToKill);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.KILL);
	}

	public MissionKill(String missionName, String description, Entity entityToKill, int numberToKill, boolean premium)
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
