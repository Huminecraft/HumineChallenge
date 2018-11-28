package com.humine.utils.level.challenge.missions;

import org.bukkit.inventory.ItemStack;

public class MissionEat extends Mission
{
	public MissionEat(String missionName, ItemStack item)
	{
		this.setMissionName(missionName);
		this.setDescription("");
		this.setItem(item);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.EAT);
	}

	public MissionEat(String missionName, String description, ItemStack item)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.EAT);
	}

	public MissionEat(String missionName, String description, ItemStack item, int numberToEat)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(numberToEat);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.EAT);
	}

	public MissionEat(String missionName, String description, ItemStack item, int numberToEat, boolean missionPremium)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(numberToEat);
		this.setDone(false);
		this.setMissionPremium(missionPremium);
		this.setMissionType(MissionType.EAT);
	}
}
