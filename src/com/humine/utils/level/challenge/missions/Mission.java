package com.humine.utils.level.challenge.missions;

import java.io.Serializable;
import java.util.HashMap;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Mission implements Serializable
{

	private static final long serialVersionUID = 1502724615290483346L;
	
	private String		missionName		= "",
								description = "";
	private EntityType		entity			= null;
	private ItemStack	item			= null;
	private int			number			= 1;
	private boolean		done			= false;
	private boolean		missionPremium	= false;
	private MissionType	missionType;
	private Recompense	recompense = null;

	public Mission()
	{
	}

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

	public EntityType getEntity()
	{
		return entity;
	}

	public void setEntity(EntityType entity)
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
	
	public Recompense getRecompense()
	{
		return recompense;
	}

	public void setRecompense(Recompense recompense)
	{
		this.recompense = recompense;
	}
	
	public HashMap<String, Object> serialize() {
		HashMap<String, Object> list = new HashMap<String, Object>();
		
		list.put("Name", this.missionName);
		list.put("Description", this.description);
		list.put("Entity", this.entity.toString());
		list.put("Item", this.item);
		list.put("Number", this.number);
		list.put("Premium", this.missionPremium);
		list.put("Type", this.missionType.toString());
		if(this.recompense != null)
		{
			list.put("Recompense.Experience", this.recompense.getExperience());
			list.put("Recompense.Token", this.recompense.getToken());
		}
		else {
			list.put("Recompense.Experience", 0);
			list.put("Recompense.Token", 0);
		}
		

		return list;
	}

}
