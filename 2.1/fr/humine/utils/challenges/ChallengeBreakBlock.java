package fr.humine.utils.challenges;

import org.bukkit.inventory.ItemStack;

public class ChallengeBreakBlock extends ChallengeBlock{

	public ChallengeBreakBlock(String title, String description, ItemStack blockToPlace, int amount, boolean premium) {
		super(title, description, blockToPlace, amount, premium);
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.BREAK_BLOCK;
	}

}
