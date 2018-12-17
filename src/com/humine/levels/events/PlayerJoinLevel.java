package com.humine.levels.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.humine.main.ChallengeMain;

public class PlayerJoinLevel implements Listener
{

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if(!ChallengeMain.getInstance().getLevelManager().containsPlayer(event.getPlayer()))
			ChallengeMain.getInstance().getLevelManager().addPlayer(event.getPlayer());
		
		ChallengeMain.getInstance().getLevelManager().addBarBoss(event.getPlayer(), ChallengeMain.getInstance().getLevelManager().getLevel(event.getPlayer()));
	}
}
