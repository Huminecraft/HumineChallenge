package com.humine.utils.level.challenge.missions;

import org.bukkit.block.Biome;

public class MissionDiscover extends Mission
{

	public MissionDiscover(String missionName, Biome biome)
	{
		this.setMissionName(missionName);
		this.setDescription("");
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.DISCOVER);
	}

	public MissionDiscover(String missionName, String description, Biome biome)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.DISCOVER);
	}

	public MissionDiscover(String missionName, String description, Biome biome, boolean premium)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setDone(false);
		this.setMissionPremium(premium);
		this.setMissionType(MissionType.DISCOVER);
	}
}
