package com.humine.level.challenge.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MissionRecuperation {

	private String missionName, description;
	private MissionType type;
	private boolean done;
	
	private HashMap<Material, Integer> items;
	
	/*
	 * Class permettant la creation de mission de recuperation
	 * d'item
	 */
	public MissionRecuperation(String missionName) {
		this.missionName = missionName;
		this.type = MissionType.RECUPERATION;
		this.description = "";
		this.done = false;
		this.items = new HashMap<Material, Integer>();
	}
	
	public MissionRecuperation(String missionName, String description) {
		this.missionName = missionName;
		this.type = MissionType.RECUPERATION;
		this.description = description;
		this.done = false;
		this.items = new HashMap<Material, Integer>();
	}

	public String getMissionName() {
		return missionName;
	}

	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MissionType getType() {
		return type;
	}

	public void setType(MissionType type) {
		this.type = type;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public void addItem(ItemStack item, int countToTake) {
		this.items.put(item.getType(), countToTake);
	}
	
	public void addItem(Material item, int countToTake) {
		this.items.put(item, countToTake);
	}
	
	public void removeItem(ItemStack item) {
		this.items.remove(item.getType());
	}
	
	public void removeItem(Material item) {
		this.items.remove(item);
	}
	
	public void refresh(ItemStack item) {
		if(this.items.containsKey(item.getType())) {
			if(this.items.get(item.getType()) > 0) {
				int count = this.items.get(item.getType());
				count -= 1;
				this.items.replace(item.getType(), count);
			}
			
			if(check() == true)
				this.done = true;
		}
	}
	
	public void refresh(Material item) {
		if(this.items.containsKey(item)) {
			if(this.items.get(item) > 0) {
				int count = this.items.get(item);
				count -= 1;
				this.items.replace(item, count);
			}
			
			if(check() == true)
				this.done = true;
		}
	}
	
	private boolean check() {
		boolean finish = true;
		
		Set<Material> materials = this.items.keySet();
		Iterator<Material> iterator = materials.iterator();
		
		while(iterator.hasNext() && finish == true) {
			Material it = iterator.next();
			if(this.items.get(it) > 0)
				finish = false;
		}
		
		return finish;
	}
}
