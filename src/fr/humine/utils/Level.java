package fr.humine.utils;

/**
 * Classe representant le Level d'un joueur, elle est representer dans le jeu
 * en haut de l'ecran accompagnee d'une {@link LevelBar}
 * @author Miza
 */
public class Level
{
	private int level;
	private int experience;
	private int experienceToReach;
	
	private static final int ECART = 100;
	
	/**
	 * Constructeur de Level
	 */
	public Level()
	{
		this.level = 1;
		this.experience = 0;
		this.experienceToReach = ECART;
	}
	
	/**
	 * Permet d'augmenter le joueur d'un niveau <br />
	 * L'experience est remise a zero et l'experience a atteindre augmente
	 */
	public void levelUp() {
		this.level++;
		this.experience = 0;
		this.experienceToReach += ECART;
	}
	
	/**
	 * Permet de reduire le joueur d'un niveau <br />
	 * L'experience est remise a zero et l'experience a atteindre diminue
	 */
	public void levelDown() {
		this.level--;
		this.experience = 0;
		this.experienceToReach -= ECART;
	}
	
	/**
	 * Ajoute de l'experience, si il depasse l'experience a atteindre, il augmentera
	 * automatique de niveau
	 * @param amount le nombre d'experience a ajouter
	 */
	public void addExperience(int amount) {
		this.experience += amount;
		if(this.experience >= this.experienceToReach) {
			int rest = this.experience - this.experienceToReach;
			levelUp();
			addExperience(rest);
		}
	}
	
	/**
	 * Supprime de l'experience, si (exp est inferieur a 0) alors on remet a 0 par defaut
	 * @param amount le nombre d'experience a supprimer
	 */
	public void removeExperience(int amount) {
		this.experience -= amount;
		if(this.experience < 0)
			this.experience = 0;
	}
	
	/**
	 * @return le Level du joueur
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * @return l'experience du joueur
	 */
	public int getExperience()
	{
		return experience;
	}
	
	/**
	 * @return l'experience a atteindre du joueur
	 */
	public int getExperienceToReach()
	{
		return experienceToReach;
	}
	
	/**
	 * definir le Level du joueur
	 * @param level le level a definir
	 */
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	/**
	 * definir l'experience du joueur
	 * @param experience l'experience a definir
	 */
	public void setExperience(int experience)
	{
		this.experience = experience;
	}
	
	/**
	 * definir l'experience d'atteinte du joueur
	 * @param experienceToReach l'experience d'atteinte a definir
	 */
	public void setExperienceToReach(int experienceToReach)
	{
		this.experienceToReach = experienceToReach;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + experience;
		result = prime * result + experienceToReach;
		result = prime * result + level;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Level other = (Level) obj;
		if (experience != other.experience)
			return false;
		if (experienceToReach != other.experienceToReach)
			return false;
		if (level != other.level)
			return false;
		return true;
	}
	
	
}
