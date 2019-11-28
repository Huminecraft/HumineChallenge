package fr.humine.utils.challenges;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * {@link Challenge} Permettant de creer un defi d'assassinat sur des entites
 * 
 * @author Miza
 */
public class ChallengeKill extends ChallengeCount {

	private static final long serialVersionUID = -4436621919615400988L;
	private EntityType entity;

	public ChallengeKill(String title, String description, EntityType entityToKill, int amount, boolean premium) {
		super(title, description, amount, premium);
		this.entity = entityToKill;
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.KILL;
	}

	public EntityType getEntity() {
		return entity;
	}

	public void setEntity(EntityType entity) {
		this.entity = entity;
	}

	@Override
	public boolean checkCondition(Object o) {
		if (o instanceof Entity) {
			if (((Entity) o).getType() == entity)
				return true;
		}

		return false;
	}

	@Override
	public Challenge clonage() {
		ChallengeKill kill = new ChallengeKill(new String(title), new String(description), entity, amount, premium);
		Award a = new Award(award.getExp(), award.getToken());
		kill.setAward(a);
		kill.setCurrentAmount(currentAmount);
		return kill;
	}

}
