package fr.humine.utils.challenges;

import org.bukkit.inventory.ItemStack;

public class ChallengePlaceBlock implements Challenge{

	private String title;
	private String description;
	private ItemStack block;
	private int amount;
	private int currentAmount;
	private boolean premium;
	
	public ChallengePlaceBlock(String title, String description, ItemStack blockToPlace, int amount, boolean premium) {
		this.title = title;
		this.description = description;
		this.block = blockToPlace;
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
		return ChallengeType.PLACE_BLOCK;
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

	public ItemStack getBlock() {
		return block;
	}

	public void setBlock(ItemStack block) {
		this.block = block;
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
		if(o instanceof ItemStack) {
			if(((ItemStack) o).isSimilar(block))
				return true;
		}
		
		return false;
	}

}
