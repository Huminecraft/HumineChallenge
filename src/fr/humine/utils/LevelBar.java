package fr.humine.utils;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

/**
 * Classe contenant la bar de boss representant le {@link Level} du joueur
 * @author Miza
 */
public class LevelBar
{
	private Level level;
	private BossBar bossBar;
	private Challenger challenger;
	
	/**
	 * Constructeur de LevelBar
	 * @param challenger le challenger a lier
	 */
	public LevelBar(Challenger challenger)
	{
		this.level = new Level();
		this.challenger = challenger;
		
		this.bossBar = Bukkit.createBossBar("Niveau : " + level.getLevel(), BarColor.GREEN, BarStyle.SEGMENTED_6);
		this.bossBar.setProgress(((100.0 * (double) this.level.getExperience()) / (double) this.level.getExperienceToReach()) / 100.0);
		this.bossBar.addPlayer(this.challenger.getPlayer());
		show();
	}
	
	/**
	 * @return Le {@link Challenger} lier a la LevelBar
	 */
	public Challenger getChallenger()
	{
		return challenger;
	}
	
	/**
	 * @return Le {@link Level} lier a LevelBar et au {@link Challenger}
	 */
	public Level getLevel()
	{
		return level;
	}
	
	/**
	 * @return retourne la {@link BossBar} du joueur
	 */
	public BossBar getBossBar()
	{
		return bossBar;
	}
	
	/**
	 * Affiche la BarLevel
	 */
	public void show() {
		this.bossBar.setVisible(true);
	}
	
	/**
	 * Cache la BarLevel
	 */
	public void hide() {
		this.bossBar.setVisible(false);
	}
	
	/**
	 * Permet de dissocier le joueur de la {@link BossBar}
	 */
	public void dissociate() {
		this.bossBar.removePlayer(this.challenger.getPlayer());
	}
	
	/**
	 * Permet de rafraichir les nouvelles donnees sur la {@link BossBar}
	 */
	public void update() {
		this.bossBar.setTitle("Niveau : " + this.level.getLevel());
		this.bossBar.setProgress(((100.0 * (double) this.level.getExperience()) / (double) this.level.getExperienceToReach()) / 100.0);
	}
}
