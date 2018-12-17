package com.humine.utils.level.challenge.missions;

import org.bukkit.inventory.ItemStack;

public class MissionRecuperation extends Mission
{

	private static final long serialVersionUID = 6493928351118849368L;

	public MissionRecuperation(String missionName, ItemStack item)
	{
		this.setMissionName(missionName);
		this.setDescription("");
		this.setItem(item);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.RECUPERATION);
	}

	public MissionRecuperation(String missionName, String description, ItemStack item)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.RECUPERATION);
	}

	public MissionRecuperation(String missionName, String description, ItemStack item, int numberToTake)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(numberToTake);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.RECUPERATION);
	}

	public MissionRecuperation(String missionName, String description, ItemStack item, int numberToTake,
			boolean missionPremium)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(numberToTake);
		this.setDone(false);
		this.setMissionPremium(missionPremium);
		this.setMissionType(MissionType.RECUPERATION);
	}
}
