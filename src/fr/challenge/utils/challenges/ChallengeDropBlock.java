package fr.challenge.utils.challenges;

import org.bukkit.inventory.ItemStack;

public class ChallengeDropBlock extends ChallengeBlock{

	private static final long serialVersionUID = 2220935373495569916L;

	public ChallengeDropBlock(String title, String description, ItemStack block, int amount, boolean premium) {
		super(title, description, block, amount, premium);
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.DROP_BLOCK;
	}

//	@Override
//	public Challenge clonage() {
//		return null;
//	}

}
