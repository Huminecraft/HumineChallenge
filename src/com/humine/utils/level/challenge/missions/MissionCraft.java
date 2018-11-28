package com.humine.utils.level.challenge.missions;

import org.bukkit.inventory.ItemStack;

public class MissionCraft extends Mission
{
	
	public MissionCraft(String missionName, ItemStack item)
	{
		this.setMissionName(missionName);
		this.setDescription("");
		this.setItem(item);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.CRAFT);
	}
	
	public MissionCraft(String missionName, String description, ItemStack item)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.CRAFT);
	}
	
	public MissionCraft(String missionName, String description, ItemStack item, int numberToCraft)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(numberToCraft);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.CRAFT);
	}
	
	public MissionCraft(String missionName, String description, ItemStack item, int numberToCraft, boolean premium)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setItem(item);
		this.setNumber(numberToCraft);
		this.setDone(false);
		this.setMissionPremium(premium);
		this.setMissionType(MissionType.CRAFT);
	}
}
