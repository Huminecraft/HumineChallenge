package fr.humine.utils.players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.humine.utils.shop.Palier;

public class ChallengerData implements Serializable {

	private static final long serialVersionUID = -7312239780723940025L;
	private List<Palier> palierUnlock;
	private int dailyChallengeFinished;
	private int weeklyChallengeFinished;
	
	public ChallengerData(List<Palier> palierUnlock) {
		this.palierUnlock = palierUnlock;
		this.dailyChallengeFinished = 0;
		this.weeklyChallengeFinished = 0;
	}
	
	public ChallengerData() {
		this(new ArrayList<Palier>());
	}
	
	public boolean addPalierUnlock(Palier palier) {
		return this.palierUnlock.add(palier);
	}
	
	public boolean removePalierUnlock(Palier palier) {
		return this.palierUnlock.remove(palier);
	}
	
	public List<Palier> getPalierUnlock() {
		return palierUnlock;
	}
	
	public int getAmountDailyChallengeFinished() {
		return dailyChallengeFinished;
	}
	
	public int getAmountWeeklyChallengeFinished() {
		return weeklyChallengeFinished;
	}
	
	public void setAmountDailyChallengeFinished(int dailyChallengeFinished) {
		this.dailyChallengeFinished = dailyChallengeFinished;
	}
	
	public void setAmountWeeklyChallengeFinished(int weeklyChallengeFinished) {
		this.weeklyChallengeFinished = weeklyChallengeFinished;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dailyChallengeFinished;
		result = prime * result + ((palierUnlock == null) ? 0 : palierUnlock.hashCode());
		result = prime * result + weeklyChallengeFinished;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChallengerData other = (ChallengerData) obj;
		if (dailyChallengeFinished != other.dailyChallengeFinished)
			return false;
		if (palierUnlock == null) {
			if (other.palierUnlock != null)
				return false;
		} else if (!palierUnlock.equals(other.palierUnlock))
			return false;
		if (weeklyChallengeFinished != other.weeklyChallengeFinished)
			return false;
		return true;
	}
	
	
}
