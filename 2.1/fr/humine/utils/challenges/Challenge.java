package fr.humine.utils.challenges;

public interface Challenge {

	public String getTitle();
	
	public String getDescription();
	
	public ChallengeType getType();
	
	public void setPremium(boolean premium);
	
	public boolean isPremium();
	
	public boolean isFinish();
	
	public void update();
	
	public boolean checkCondition(Object o);
	
	//TODO public Recompense getAwards();
}
