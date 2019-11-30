package fr.challenge.utils.challenges;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.challenge.utils.Challenger;

public abstract class ChallengeCount extends AbstractChallenge{

	private static final long serialVersionUID = -6799244046477637244L;
	protected int amount;
	protected int currentAmount;
	
	public ChallengeCount(String title, String description, int amount, boolean premium) {
		super(title, description, premium);
		this.amount = amount;
		this.currentAmount = 0;
	}

	@Override
	public boolean isFinish() {
		return currentAmount >= amount;
	}

	@Override
	public void update() {
		currentAmount++;
	}
	
	@Override
	public void showChallenge(Challenger challenger) {
		super.showChallenge(challenger);
		challenger.getPlayer().sendMessage("Etat : " + currentAmount + " / " + amount);
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
	public ItemStack toItemStack() {
		ItemStack item = super.toItemStack();
		ChatColor color = (isFinish()) ? ChatColor.GREEN : ChatColor.YELLOW;
		
		ItemMeta meta = item.getItemMeta();
		List<String> lores = meta.getLore();
		lores.add(" ");
		lores.add(color + "Etat : " + getCurrentAmount() + " / " + getAmount());
		
		item.setItemMeta(meta);
		return item;
	}
}
