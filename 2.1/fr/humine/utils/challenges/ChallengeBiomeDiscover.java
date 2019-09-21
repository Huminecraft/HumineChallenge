package fr.humine.utils.challenges;

import org.bukkit.block.Biome;
import org.bukkit.command.CommandSender;

public class ChallengeBiomeDiscover implements Challenge{

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
	public void showChallenge(CommandSender sender) {
		sender.sendMessage("Titre: " + getTitle());
		sender.sendMessage("Description: " + getDescription());
		sender.sendMessage("Type: " + getType().toString().toLowerCase());
		sender.sendMessage("Biome: " + biome.toString().toLowerCase());
		sender.sendMessage("Etat: " + isFinish());
	}

	@Override
	public Award getAwards() {
		return award;
	}

	@Override
	public void setAward(Award award) {
		this.award = award;
	}

}
