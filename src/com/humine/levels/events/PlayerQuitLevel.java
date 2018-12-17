package com.humine.levels.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.humine.main.ChallengeMain;

public class PlayerQuitLevel implements Listener
{

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		ChallengeMain.getInstance().getLevelManager().removeBarBoss(event.getPlayer());
	}
}
