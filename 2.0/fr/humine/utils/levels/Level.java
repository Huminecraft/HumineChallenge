package fr.humine.utils.levels;

import java.io.Serializable;

public class Level implements Serializable{

	private static final long serialVersionUID = 4934168850287003757L;
	
	private int level;
	private int experience;
	private int experienceToReach;

	private static final int PALIER = 100;
	
	public Level() {
		this.level = 1;
		this.experience = 0;
		this.experienceToReach = 150;
	}
	
	public boolean canPassNextLevel() {
		return (this.experience >= this.experienceToReach);
	}

	public void addOneLevel() {
		this.level++;
		this.experience = 0;
		this.experienceToReach += PALIER;
	}
	
	public void removeOneLevel() {
		this.level--;
		this.experience = 0;
		this.experienceToReach -= PALIER;
	}

	public int getLevel() {
		return this.level;
	}

	public int getExperience()
	{
		return experience;
	}

	public void addExperience(int experience)
	{
		this.experience += experience;
	}
	
	public void removeExperience(int experience)
	{
		this.experience = ((this.experience - experience) < 0) ? 0 : this.experience - experience;
	}

	public int getExperienceToReach()
	{
		return experienceToReach;
	}
}
