package fr.challenge.utils.challenges;

import org.bukkit.block.Biome;

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
