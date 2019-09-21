package fr.humine.utils;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

public class LevelBar
{
	private Level level;
	private BossBar bossBar;
	private Challenger challenger;
	
	public LevelBar(Challenger challenger)
	{
		this.level = new Level();
		this.challenger = challenger;
		
		this.bossBar = Bukkit.createBossBar("Niveau: " + level.getLevel(), BarColor.GREEN, BarStyle.SEGMENTED_6);
		this.bossBar.setProgress(((100.0 * (double) this.level.getExperience()) / (double) this.level.getExperienceToReach()) / 100.0);
		this.bossBar.addPlayer(this.challenger.getPlayer());
		show();
	}
	
	public Challenger getChallenger()
	{
		return challenger;
	}
	
	public Level getLevel()
	{
		return level;
	}
	
	public BossBar getBossBar()
	{
		return bossBar;
	}
	
	public void show() {
		this.bossBar.setVisible(true);
	}
	
	public void hide() {
		this.bossBar.setVisible(false);
	}
	
	public void dissociate() {
		this.bossBar.removePlayer(this.challenger.getPlayer());
	}
	
	public void update() {
		this.bossBar.setTitle("Niveau: " + this.level.getLevel());
		this.bossBar.setProgress(((100.0 * (double) this.level.getExperience()) / (double) this.level.getExperienceToReach()) / 100.0);
	}
}
