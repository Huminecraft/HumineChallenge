package com.humine.level.challenge.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.entity.Entity;

public class MissionKill {

	private String missionName, description;
	private MissionType type;
	private boolean done;
	
	private HashMap<String, Integer> monsters;
	
	/*
	 * Class permettant la creation de mission d'extermination
	 * d'entity
	 */
	public MissionKill(String missionName) {
		this.missionName = missionName;
		this.type = MissionType.KILL;
		this.description = "";
		this.done = false;
		this.monsters = new HashMap<String, Integer>();
	}
	
	public MissionKill(String missionName, String description) {
		this.missionName = missionName;
		this.type = MissionType.KILL;
		this.description = description;
		this.done = false;
		this.monsters = new HashMap<String, Integer>();
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

	public void addMonster(Entity entity, int countToKill) {
		this.monsters.put(entity.getName(), countToKill);
	}
	
	public void removeMonster(Entity entity) {
		this.monsters.remove(entity.getName());
	}
	
	public void refresh(Entity entity) {
		if(this.monsters.containsKey(entity.getName())) {
			if(this.monsters.get(entity.getName()) > 0) {
				int count = this.monsters.get(entity.getName());
				count -= 1;
				this.monsters.replace(entity.getName(), count);
			}
			
			if(check() == true)
				this.done = true;
		}
	}
	
	private boolean check() {
		boolean finish = true;
		
		Set<String> entities = this.monsters.keySet();
		Iterator<String> iterator = entities.iterator();
		
		while(iterator.hasNext() && finish == true) {
			String entite = iterator.next();
			if(this.monsters.get(entite) > 0)
				finish = false;
		}
		
		return finish;
	}
}
