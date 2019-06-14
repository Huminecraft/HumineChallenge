package fr.humine.utils.challenge;

import java.io.Serializable;

public abstract class Challenge implements Serializable{

	private static final long serialVersionUID = 2034865242612946223L;
	private String name;
	private String description;
	private ChallengeType type;
	private Recompense recompense;
	private boolean premium;
	
	private boolean validated;
	
	public Challenge(String name, String description, ChallengeType type, Recompense recompense, boolean premium) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.recompense = recompense;
		this.premium = premium;
		this.validated = false;
	}
	
	public Challenge(String name, ChallengeType type, Recompense recompense, boolean premium) {
		this(name, "", type, recompense, premium);
	}
	
	public Challenge(String name, ChallengeType type, Recompense recompense) {
		this(name, "", type, recompense, false);
	}
	
	public Challenge(String name, ChallengeType type) {
		this(name, "", type, new Recompense(0, 0, 0, null), false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ChallengeType getType() {
		return type;
	}

	public void setType(ChallengeType type) {
		this.type = type;
	}

	public Recompense getRecompense() {
		return recompense;
	}

	public void setRecompense(Recompense recompense) {
		this.recompense = recompense;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public void valideChallenge() {
		this.validated = true;
	}
	
	public boolean isValidated() {
		return this.validated;
	}
	
	public abstract boolean isFinish();
	
	public abstract void updateChallenge();
}
