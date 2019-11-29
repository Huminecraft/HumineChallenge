package fr.challenge.utils.challenges;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

/**
 * {@link Challenge} Permettant de creer un defi lier au block <br />
 * Challengeblock est une classe mere abstraite
 * 
 * @author Miza
 */
public abstract class ChallengeBlock extends ChallengeCount {

	private static final long serialVersionUID = 6658325060908901604L;
	protected Material block;

	public ChallengeBlock(String title, String description, ItemStack block, int amount, boolean premium) {
		super(title, description, amount, premium);
		this.block = block.getType();
	}

	public Material getBlock() {
		return block;
	}

	public void setBlock(ItemStack block) {
		this.block = block.getType();
	}

	@Override
	public boolean checkCondition(Object o) {
		if (o instanceof ItemStack) {
			if (((ItemStack) o).getType() == this.block)
				return true;
		}

		if (o instanceof Material) {
			if (((Material) o) == this.block)
				return true;
		}
		
		if (o instanceof Block) {
			if (((Block) o).getType() == this.block)
				return true;
		}

		return false;
	}
}
