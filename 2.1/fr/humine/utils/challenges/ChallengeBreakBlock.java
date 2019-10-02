package fr.humine.utils.challenges;

import org.bukkit.inventory.ItemStack;

public class ChallengeBreakBlock extends ChallengeBlock{

	private static final long serialVersionUID = 3263184043899325268L;

	public ChallengeBreakBlock(String title, String description, ItemStack blockToPlace, int amount, boolean premium) {
		super(title, description, blockToPlace, amount, premium);
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.BREAK_BLOCK;
	}

}
