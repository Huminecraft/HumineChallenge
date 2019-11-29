package fr.challenge.utils.challenges;

import org.bukkit.inventory.ItemStack;

public class ChallengeEnchantItem extends ChallengeBlock{

	private static final long serialVersionUID = -8700208285003960966L;

	public ChallengeEnchantItem(String title, String description, ItemStack block, int amount, boolean premium) {
		super(title, description, block, amount, premium);
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.ENCHANT_ITEM;
	}

//	@Override
//	public Challenge clonage() {
//		return null;
//	}

}
