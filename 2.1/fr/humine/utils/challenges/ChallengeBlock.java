package fr.humine.utils.challenges;

import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

public abstract class ChallengeBlock implements Challenge{

	protected String title;
	protected String description;
	protected ItemStack block;
	protected int amount;
	protected int currentAmount;
	protected boolean premium;
	
	public ChallengeBlock(String title, String description, ItemStack blockToPlace, int amount, boolean premium) {
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

	@Override
	public void showChallenge(CommandSender sender) {
		sender.sendMessage("Titre: " + getTitle());
		sender.sendMessage("Description: " + getDescription());
		sender.sendMessage("Type: " + getType().toString().toLowerCase());
		sender.sendMessage("Bloc: " + block.getType().toString().toLowerCase());
		sender.sendMessage("Etat: " + currentAmount + "/" + amount);
	}

}
