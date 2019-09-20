package fr.humine.utils.challenges;

import org.bukkit.entity.Entity;

public class ChallengeKill implements Challenge{

	private String title;
	private String description;
	private Entity entity;
	private int amount;
	private int currentAmount;
	private boolean premium;
	
	public ChallengeKill(String title, String description, Entity entityToKill, int amount, boolean premium) {
		this.title = title;
		this.description = description;
		this.entity = entityToKill;
		this.amount = amount;
		this.currentAmount = 0;
		this.premium = premium;
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.KILL;
	}

	@Override
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	@Override
	public boolean isPremium() {
		return premium;
	}

	@Override
	public boolean isFinish() {
		return currentAmount >= amount;
	}

	@Override
	public void update() {
		currentAmount++;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(int currentAmount) {
		this.currentAmount = currentAmount;
	}

	@Override
	public boolean checkCondition(Object o) {
		if(o.getClass().toString().equals(entity.getClass().toString()))
			return true;
		
		return false;
	}

}
