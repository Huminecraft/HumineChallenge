package fr.challenge.utils.challenges;

import fr.challenge.utils.Challenger;

public abstract class AbstractChallenge implements Challenge {

	private static final long serialVersionUID = -112161687493980126L;

	protected String title;
	protected String description;
	protected boolean premium;
	protected Award award;
	
	public AbstractChallenge(String title, String description, boolean premium) {
		this.title = title;
		this.description = description;
		this.premium = premium;
		this.award = new Award(0, 0);
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	@Override
	public boolean isPremium() {
		return premium;
	}

	@Override
	public void showChallenge(Challenger challenger) {
		challenger.getPlayer().sendMessage("Titre : " + getTitle());
		challenger.getPlayer().sendMessage("Description : " + getDescription());
	}

	@Override
	public Award getAwards() {
		return award;
	}

	@Override
	public void setAward(Award award) {
		this.award = award;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
