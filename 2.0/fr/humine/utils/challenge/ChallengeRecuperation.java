package fr.humine.utils.challenge;

import org.bukkit.inventory.ItemStack;

public class ChallengeRecuperation extends Challenge
{

	private static final long serialVersionUID = 626827236799149128L;
	private String itemName;
	private int NumberToTake;
	private int currentNumberTaked;
	
	public ChallengeRecuperation(String name, Recompense recompense, boolean premium, ItemStack item)
	{
		this(name, "", recompense, premium, item, 1);
	}

	public ChallengeRecuperation(String name, Recompense recompense, ItemStack item)
	{
		this(name, "", recompense, false, item, 1);
	}

	public ChallengeRecuperation(String name, ChallengeType type, ItemStack item)
	{
		this(name, "", new Recompense(), false, item, 1);
	}

	public ChallengeRecuperation(String name, String description, Recompense recompense, boolean premium, ItemStack item, int nbToTake)
	{
		super(name, description, ChallengeType.RECUPERATION, recompense, premium);
		this.itemName = item.getItemMeta().getDisplayName();
		this.NumberToTake = nbToTake;
		this.currentNumberTaked = 0;
	}

	public String getItemName()
	{
		return itemName;
	}
	
	public void setItemName(ItemStack item)
	{
		this.itemName = item.getItemMeta().getDisplayName();
	}
	
	public int getNumberToTake()
	{
		return NumberToTake;
	}
	
	public void setNumberToTake(int numberToTake)
	{
		NumberToTake = numberToTake;
	}
	
	public int getCurrentNumberTaked()
	{
		return currentNumberTaked;
	}
	
	public void setCurrentNumberTaked(int currentNumberTaked)
	{
		this.currentNumberTaked = currentNumberTaked;
	}
	
	@Override
	public boolean isFinish()
	{
		return this.currentNumberTaked >= this.NumberToTake;
	}

	@Override
	public void updateChallenge()
	{
		this.currentNumberTaked++;
	}
}
