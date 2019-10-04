package fr.humine.utils.events;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.humine.utils.Challenger;

/**
 * Event permettant de detecter un {@link Challenger} changeant de Biome
 * 
 * @author Miza
 */
public class PlayerChangeBiomeEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private Biome fromBiome;
	private Biome toBiome;

	public PlayerChangeBiomeEvent(Player player, Biome ancienBiome, Biome nouveauBiome) {
		this.player = player;
		this.fromBiome = ancienBiome;
		this.toBiome = nouveauBiome;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * @return Le joueur changeant de biome
	 */
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return Le {@link Biome} de depart ou se trouvait le joueur
	 */
	public Biome fromBiome() {
		return fromBiome;
	}

	public void setFromBiome(Biome fromBiome) {
		this.fromBiome = fromBiome;
	}

	/**
	 * @return Le nouveau {@link Biome} dans laquelle se trouve le joueur
	 */
	public Biome toBiome() {
		return toBiome;
	}

	public void setToBiome(Biome toBiome) {
		this.toBiome = toBiome;
	}

}
