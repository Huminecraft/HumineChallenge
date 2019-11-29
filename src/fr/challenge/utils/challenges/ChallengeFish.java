package fr.challenge.utils.challenges;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class ChallengeFish extends ChallengeCount{

	private static final long serialVersionUID = -2738009590499264363L;
	private EntityType type;
	
	public ChallengeFish(String title, String description, int amount, EntityType entity, boolean premium) {
		super(title, description, amount, premium);
		this.type = entity;
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.FISH;
	}

	@Override
	public boolean checkCondition(Object o) {
		if(o instanceof Entity)
			return ((Entity) o).getType() == this.type;
		
		if(o instanceof EntityType)
			return ((EntityType) o) == this.type;
		
		return false;
	}

}
