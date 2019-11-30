package fr.challenge.utils.challenges;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.challenge.utils.Challenger;

/**
 * {@link Challenge} Permettant de creer un defi de decouverte de biome
 * 
 * @author Miza
 */
public class ChallengeBiomeDiscover extends AbstractChallenge{

	private static final long serialVersionUID = 8598010162097323245L;
	private Biome biome;
	private boolean discover;
	
	public ChallengeBiomeDiscover(String title, String description, Biome biomeToDiscover, boolean premium) {
		super(title, description, premium);
		this.biome = biomeToDiscover;
		this.discover = false;
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.BIOME_DISCOVER;
	}

	@Override
	public boolean isFinish() {
		return discover;
	}

	@Override
	public void update() {
		discover = true;
	}

	@Override
	public boolean checkCondition(Object o) {
		if(o instanceof Biome) {
			if(((Biome) o) == biome)
				return true;
		}
		return false;
	}

	@Override
	public void showChallenge(Challenger challenger) {
		super.showChallenge(challenger);
		challenger.getPlayer().sendMessage("Type : " + getType().toString().toLowerCase());
		challenger.getPlayer().sendMessage("Biome : " + biome.toString().toLowerCase());
		challenger.getPlayer().sendMessage("Etat : " + isFinish());
	}

	@Override
	public ItemStack toItemStack() {
		ItemStack item = super.toItemStack();
		ChatColor color = (isFinish()) ? ChatColor.GREEN : ChatColor.YELLOW;
		
		ItemMeta meta = item.getItemMeta();
		List<String> lores = meta.getLore();
		lores.add(" ");
		lores.add(color + "Etat : " + (isFinish() ? "Trouve" : "Non trouve"));
		
		item.setItemMeta(meta);
		return item;
	}
	
//	@Override
//	public Challenge clonage()
//	{
//		ChallengeBiomeDiscover discover = new ChallengeBiomeDiscover(new String(title), new String(description), biome, premium);
//		Award a = new Award(award.getExp(), award.getToken());
//		discover.setAward(a);
//		discover.discover = this.discover;
//		return discover;
//	}

}
