package fr.humine.utils.challenges;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class ChallengeKill implements Challenge{

	private String title;
	private String description;
	private EntityType entity;
	private int amount;
	private int currentAmount;
	private boolean premium;
	
	private Award award;
	
	public ChallengeKill(String title, String description, EntityType entityToKill, int amount, boolean premium) {
		this.title = title;
		this.description = description;
		this.entity = entityToKill;
		this.amount = amount;
		this.currentAmount = 0;
		this.premium = premium;
		this.award = new Award(0, 0);
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

	public EntityType getEntity() {
		return entity;
	}

	public void setEntity(EntityType entity) {
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
		if(o instanceof Entity) {
			if(((Entity) o).getType() == entity)
				return true;
		}
		
		return false;
	}

	@Override
	public void showChallenge(CommandSender sender) {
		sender.sendMessage("Titre: " + getTitle());
		sender.sendMessage("Description: " + getDescription());
		sender.sendMessage("Type: " + getType().toString().toLowerCase());
		sender.sendMessage("Cible: " + entity.toString().toLowerCase());
		sender.sendMessage("Etat: " + currentAmount + "/" + amount);
	}

	@Override
	public Award getAwards() {
		return award;
	}

	@Override
	public void setAward(Award award) {
		this.award = award;
	}

}
