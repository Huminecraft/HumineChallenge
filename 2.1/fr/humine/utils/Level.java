package fr.humine.utils;

public class Level
{
	private int level;
	private int experience;
	private int experienceToReach;
	
	public Level()
	{
		this.level = 1;
		this.experience = 0;
		this.experienceToReach = 100;
	}
	
	public void levelUp() {
		this.level++;
		this.experience = 0;
		this.experienceToReach += 100;
	}
	
	public void levelDown() {
		this.level--;
		this.experience = 0;
		this.experienceToReach -= 100;
	}
	
	public void addExperience(int amount) {
		this.experience += amount;
		if(this.experience >= this.experienceToReach)
			levelUp();
	}
	
	public void removeExperience(int amount) {
		this.experience -= amount;
		if(this.experience < 0)
			this.experience = 0;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public int getExperience()
	{
		return experience;
	}
	
	public int getExperienceToReach()
	{
		return experienceToReach;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public void setExperience(int experience)
	{
		this.experience = experience;
	}
	
	public void setExperienceToReach(int experienceToReach)
	{
		this.experienceToReach = experienceToReach;
	}
	
}
