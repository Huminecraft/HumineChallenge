package fr.humine.utils.challenge;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

public class ChallengeDiscover extends Challenge
{

	private static final long serialVersionUID = -560734266805726401L;

	private Biome biomeToDiscover;
	private boolean biomeIsVisited;
	
	public ChallengeDiscover(String name, Recompense recompense, boolean premium, Biome biomeToDiscover)
	{
		this(name, "", recompense, premium, biomeToDiscover);
	}

	public ChallengeDiscover(String name, Recompense recompense, Biome biomeToDiscover)
	{
		this(name, "", recompense, false, biomeToDiscover);
	}

	public ChallengeDiscover(String name, ChallengeType type, Biome biomeToDiscover)
	{
		this(name, "", new Recompense(), false, biomeToDiscover);
	}

	public ChallengeDiscover(String name, String description, Recompense recompense, boolean premium, Biome biomeToDiscover)
	{
		super(name, description, ChallengeType.DISCOVER, recompense, premium);
		this.biomeToDiscover = biomeToDiscover;
		this.biomeIsVisited = false;
	}

	@Override
	public boolean isFinish()
	{
		return this.biomeIsVisited;
	}

	@Override
	public void updateChallenge()
	{
		this.biomeIsVisited = true;
	}
	
	public boolean isInBiome(Player player) {
		return player.getWorld().getBiome(player.getLocation().getBlockX(), player.getLocation().getBlockZ()) == biomeToDiscover;
	}
}
