package com.humine.utils.level.challenge.missions;

import java.util.HashMap;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public abstract class Mission
{

	private String		missionName		= "",
								description = "";
	private Entity		entity			= null;
	private ItemStack	item			= null;
	private int			number			= 1;
	private boolean		done			= false;
	private boolean		missionPremium	= false;
	private MissionType	missionType;

	public String getMissionName()
	{
		return missionName;
	}

	public void setMissionName(String missionName)
	{
		this.missionName = missionName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Entity getEntity()
	{
		return entity;
	}

	public void setEntity(Entity entity)
	{
		this.entity = entity;
	}

	public boolean isDone()
	{
		return done;
	}

	public void setDone(boolean done)
	{
		this.done = done;
	}

	public boolean isMissionPremium()
	{
		return missionPremium;
	}

	public void setMissionPremium(boolean missionPremium)
	{
		this.missionPremium = missionPremium;
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public ItemStack getItem()
	{
		return item;
	}

	public void setItem(ItemStack item)
	{
		this.item = item;
	}

	public MissionType getMissionType()
	{
		return missionType;
	}

	public void setMissionType(MissionType missionType)
	{
		this.missionType = missionType;
	}

	public HashMap<String, Object> serialize()
	{
		HashMap<String, Object> list = new HashMap<String, Object>();

		list.put("MissionName", this.missionName);
		list.put("Description", this.description);
		list.put("Entity", this.entity);
		list.put("Item", this.item);
		list.put("Number", this.number);
		list.put("Type", this.missionType);
		list.put("Done", this.done);
		list.put("premium", this.missionPremium);

		return list;
	}
}
