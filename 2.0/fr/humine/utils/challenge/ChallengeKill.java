package fr.humine.utils.challenge;

import org.bukkit.entity.Entity;

public class ChallengeKill extends Challenge
{

	private static final long serialVersionUID = -8920936375622919115L;
	private String entityName;
	private int numberToKill;
	private int currentNumberKilled;
	
	public ChallengeKill(String name, Recompense recompense, boolean premium, Entity entity)
	{
		this(name, "", recompense, premium, entity, 1);
	}

	public ChallengeKill(String name, Recompense recompense, Entity entity)
	{
		this(name, "", recompense, false, entity, 1);
	}

	public ChallengeKill(String name, ChallengeType type, Entity entity)
	{
		this(name, "", new Recompense(), false, entity, 1);
	}

	public ChallengeKill(String name, String description, Recompense recompense, boolean premium, Entity entity, int nbToKill)
	{
		super(name, description, ChallengeType.KILL, recompense, premium);
		this.entityName = entity.getName();
		this.numberToKill = nbToKill;
		this.currentNumberKilled = 0;
	}

	public int getNumberToKill()
	{
		return numberToKill;
	}
	
	public void setNumberToKill(int numberToKill)
	{
		this.numberToKill = numberToKill;
	}
	
	public int getCurrentNumberKilled()
	{
		return currentNumberKilled;
	}
	
	public void setCurrentNumberKilled(int currentNumberKilled)
	{
		this.currentNumberKilled = currentNumberKilled;
	}
	
	public String getEntityName()
	{
		return entityName;
	}
	
	public void setEntityName(Entity entity)
	{
		this.entityName = entity.getName();
	}
	@Override
	public void updateChallenge()
	{
		this.currentNumberKilled++;
	}

	@Override
	public boolean isFinish()
	{
		return this.currentNumberKilled >= this.numberToKill;
	}

}
