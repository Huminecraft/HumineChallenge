package fr.humine.utils.challenges;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

public abstract class ChallengeBlock implements Challenge{

	private static final long serialVersionUID = 6658325060908901604L;
	protected String title;
	protected String description;
	protected Material block;
	protected int amount;
	protected int currentAmount;
	protected boolean premium;
	
	protected Award award;
	
	public ChallengeBlock(String title, String description, ItemStack blockToPlace, int amount, boolean premium) {
		this.title = title;
		this.description = description;
		this.block = blockToPlace.getType();
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

	public Material getBlock() {
		return block;
	}

	public void setBlock(ItemStack block) {
		this.block = block.getType();
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
			if(((ItemStack) o).getType() == this.block)
				return true;
		}
		
		if(o instanceof Material) {
			if(((Material) o) == this.block)
				return true;
		}
		
		return false;
	}

	@Override
	public void showChallenge(CommandSender sender) {
		sender.sendMessage("Titre: " + getTitle());
		sender.sendMessage("Description: " + getDescription());
		sender.sendMessage("Type: " + getType().toString().toLowerCase());
		sender.sendMessage("Bloc: " + block.toString().toLowerCase());
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
