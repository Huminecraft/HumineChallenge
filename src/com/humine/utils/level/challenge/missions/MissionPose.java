package com.humine.utils.level.challenge.missions;

import org.bukkit.inventory.ItemStack;

public class MissionPose extends Mission
{

	public MissionPose(String missionName, ItemStack item)
	{
		this.setMissionName(missionName);
		this.setDescription("");
		this.setItem(item);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.POSE);
	}

	public MissionPose(String missionName, String description, ItemStack item)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.POSE);
	}

	public MissionPose(String missionName, String description, ItemStack item, int numberToPose)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(numberToPose);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.POSE);
	}

	public MissionPose(String missionName, String description, ItemStack item, int numberToPose, boolean missionPremium)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(numberToPose);
		this.setDone(false);
		this.setMissionPremium(missionPremium);
		this.setMissionType(MissionType.POSE);
	}
}
