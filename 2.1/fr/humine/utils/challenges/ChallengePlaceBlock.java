package fr.humine.utils.challenges;

import org.bukkit.inventory.ItemStack;

public class ChallengePlaceBlock extends ChallengeBlock{

	private static final long serialVersionUID = -515719438714683526L;

	public ChallengePlaceBlock(String title, String description, ItemStack blockToPlace, int amount, boolean premium) {
		super(title, description, blockToPlace, amount, premium);
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.PLACE_BLOCK;
	}

}
