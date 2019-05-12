package humine.utils.levels;

public class Level {

	private int level;
	private int experience;
	private int experienceToReach;

	public Level() {
		this.level = 1;
		this.experience = 0;
		this.experienceToReach = 150;
	}

	public Level(int startLevel) {
		this.level = startLevel;
		this.experience = 0;
		this.experienceToReach = (int) ((startLevel == 1) ? 150 : 150.0 * Math.pow(1.5, startLevel));
	}
	
	public boolean canPassNextLevel() {
		return (this.experience >= this.experienceToReach);
	}

	public void addLevel(int l) {
		this.level += l;
		this.experience = this.experience % this.experienceToReach;
		this.experienceToReach *= Math.pow(1.5, l);
	}

	public void removeLevel(int l) {
		this.level -= l;
		this.experience = 0;
		this.experienceToReach /= Math.pow(1.5, l);
	}

	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int l) {
		this.level = l;
	}

	public int getExperience()
	{
		return experience;
	}

	public void setExperience(int experience)
	{
		this.experience = experience;
	}

	public int getExperienceToReach()
	{
		return experienceToReach;
	}

	public void setExperienceToReach(int experienceToReach)
	{
		this.experienceToReach = experienceToReach;
	}
}
