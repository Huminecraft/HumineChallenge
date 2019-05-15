package fr.humine.utils.shop;

import org.bukkit.entity.Player;

import humine.utils.cosmetiques.Cosmetique;

public interface Achievement {

	public void giveRecompense(Player player);
	
	public Cosmetique getCosmetique();
}
