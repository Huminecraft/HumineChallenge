package fr.humine.utils.challenges;

import java.io.Serializable;

import org.bukkit.command.CommandSender;

public interface Challenge extends Serializable{

	public String getTitle();
	
	public String getDescription();
	
	public ChallengeType getType();
	
	public void setPremium(boolean premium);
	
	public boolean isPremium();
	
	public boolean isFinish();
	
	public void update();
	
	public boolean checkCondition(Object o);
	
	public void showChallenge(CommandSender sender);
	
	public Award getAwards();
	
	public void setAward(Award award);
	
}
