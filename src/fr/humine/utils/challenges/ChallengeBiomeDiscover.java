package fr.humine.utils.challenges;

import org.bukkit.block.Biome;

import fr.humine.utils.Challenger;

/**
 * {@link Challenge} Permettant de creer un defi de decouverte de biome
 * 
 * @author Miza
 */
public class ChallengeBiomeDiscover implements Challenge{

	private static final long serialVersionUID = 8598010162097323245L;
	private String title;
	private String description;
	private Biome biome;
	private boolean premium;
	private boolean discover;
	private Award award;
	
	public ChallengeBiomeDiscover(String title, String description, Biome biomeToDiscover, boolean premium) {
		this.title = title;
		this.description = description;
		this.biome = biomeToDiscover;
		this.premium = premium;
		this.discover = false;
		this.award = new Award(0, 0);
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.BIOME_DISCOVER;
	}

	@Override
	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	@Override
	public boolean isPremium() {
		return premium;
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
		challenger.getPlayer().sendMessage("Titre: " + getTitle());
		challenger.getPlayer().sendMessage("Description: " + getDescription());
		challenger.getPlayer().sendMessage("Type: " + getType().toString().toLowerCase());
		challenger.getPlayer().sendMessage("Biome: " + biome.toString().toLowerCase());
		challenger.getPlayer().sendMessage("Etat: " + isFinish());
	}

	@Override
	public Award getAwards() {
		return award;
	}

	@Override
	public void setAward(Award award) {
		this.award = award;
	}

	@Override
	public Challenge clonage()
	{
		ChallengeBiomeDiscover discover = new ChallengeBiomeDiscover(new String(title), new String(description), biome, premium);
		Award a = new Award(award.getExp(), award.getToken());
		discover.setAward(a);
		discover.discover = this.discover;
		return discover;
	}

}
