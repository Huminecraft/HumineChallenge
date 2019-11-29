package fr.challenge.utils.challenges;

import org.bukkit.inventory.ItemStack;

/**
 * {@link Challenge} Permettant de creer un defi de destruction de bloc
 * 
 * @author Miza
 */
public class ChallengeBreakBlock extends ChallengeBlock {

	private static final long serialVersionUID = 3263184043899325268L;

	public ChallengeBreakBlock(String title, String description, ItemStack blockToPlace, int amount, boolean premium) {
		super(title, description, blockToPlace, amount, premium);
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.BREAK_BLOCK;
	}

//	@Override
//	public Challenge clonage() {
//		ChallengeBreakBlock b = new ChallengeBreakBlock(new String(title), new String(description),
//				new ItemStack(block), amount, premium);
//		Award a = new Award(award.getExp(), award.getToken());
//		b.setAward(a);
//		b.setCurrentAmount(currentAmount);
//		return b;
//	}

}
