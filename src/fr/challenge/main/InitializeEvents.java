package fr.challenge.main;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.challenge.utils.Challenger;
import fr.challenge.utils.events.PlayerChangeBiomeEvent;
import fr.challenge.utils.events.ihm.ClickItemEvent;
import fr.challenge.utils.events.ihm.ClickItemMenuAccueilEvent;
import fr.challenge.utils.events.ihm.ClickItemMenuChangeHebdoEvent;
import fr.challenge.utils.events.ihm.ClickItemMenuDailyEvent;
import fr.challenge.utils.events.ihm.ClickItemMenuHebdoEvent;
import fr.challenge.utils.menu.MenuAccueil;
import fr.challenge.utils.menu.MenuChangeHebdoChallenge;
import fr.challenge.utils.menu.MenuDailyChallenge;
import fr.challenge.utils.menu.MenuHebdoChallenge;

public class InitializeEvents implements Listener{

	@EventHandler
	public void onClickItem(InventoryClickEvent event) {
		if(event.getView().getTitle().equals(MenuAccueil.NAME) ||
				event.getView().getTitle().equals(MenuDailyChallenge.NAME) ||
					event.getView().getTitle().equals(MenuHebdoChallenge.NAME) ||
						event.getView().getTitle().equals(MenuChangeHebdoChallenge.NAME)) {
			
			if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
				if(event.getWhoClicked() instanceof Player) {
					Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger((Player) event.getWhoClicked());
					ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new ClickItemEvent(challenger, event.getInventory(), event.getView(), event.getCurrentItem()));
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onClickMenuAccueil(ClickItemEvent event) {
		if(event.getView().getTitle().equals(MenuAccueil.NAME)) {
			ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new ClickItemMenuAccueilEvent(event.getInventory(), event.getView(), event.getChallenger(), event.getItem()));
		}
	}
	
	@EventHandler
	public void onClickMenuDaily(ClickItemEvent event) {
		if(event.getView().getTitle().equals(MenuDailyChallenge.NAME)) {
			ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new ClickItemMenuDailyEvent(event.getInventory(), event.getView(), event.getChallenger(), event.getItem()));
		}
	}
	
	@EventHandler
	public void onClickMenuHebdo(ClickItemEvent event) {
		if(event.getView().getTitle().equals(MenuHebdoChallenge.NAME)) {
			ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new ClickItemMenuHebdoEvent(event.getInventory(), event.getView(), event.getChallenger(), event.getItem()));
		}
	}
	
	@EventHandler
	public void onClickMenuChangeHebdo(ClickItemEvent event) {
		if(event.getView().getTitle().equals(MenuChangeHebdoChallenge.NAME)) {
			ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new ClickItemMenuChangeHebdoEvent(event.getInventory(), event.getView(), event.getChallenger(), event.getItem()));
		}
	}
	
	@EventHandler
	public void onChangeBiome(PlayerMoveEvent event) {
		if(event.getPlayer().getWorld().getBlockAt(event.getFrom()).getBiome() != event.getPlayer().getWorld().getBlockAt(event.getTo()).getBiome()) {
			Biome fromBiome = event.getPlayer().getWorld().getBlockAt(event.getFrom()).getBiome();
			Biome toBiome = event.getPlayer().getWorld().getBlockAt(event.getTo()).getBiome();
			ChallengeMain.getInstance().getServer().getPluginManager().callEvent(new PlayerChangeBiomeEvent(event.getPlayer(), fromBiome, toBiome));
		}
	}
}
