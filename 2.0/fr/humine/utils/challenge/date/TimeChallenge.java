package fr.humine.utils.challenge.date;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.humine.utils.challenge.Challenge;
import fr.humine.utils.challenge.ChallengeType;

public abstract class TimeChallenge implements Serializable
{

	private static final long serialVersionUID = 3263856376206394980L;

	private List<Challenge> challenges;
	private LocalDate startDate;
	private LocalDate endDate;
	private TimeChallengeType type;
	
	public TimeChallenge(List<Challenge> challenges, LocalDate startDate, int during, TimeChallengeType type)
	{
		this.challenges = challenges;
		this.startDate = startDate;
		this.endDate = this.startDate.plusDays(during);
		this.type = type;
	}
	
	public TimeChallenge(LocalDate startDate, int during, TimeChallengeType type)
	{
		this(new ArrayList<Challenge>(), startDate, during, type);
	}
	
	public TimeChallenge(LocalDate startDate, TimeChallengeType type)
	{
		this(new ArrayList<Challenge>(), startDate, 0, type);
	}
	
	public boolean addChallenge(Challenge challenge) {
		return this.challenges.add(challenge);
	}
	
	public boolean removeChallenge(Challenge challenge) {
		return this.challenges.remove(challenge);
	}
	
	public Challenge removeChallenge(int index) {
		if(index >= 0 && index < this.challenges.size())
			return this.challenges.remove(index);
		return null;
	}
	
	public List<Challenge> getChallenges()
	{
		return challenges;
	}
	
	public List<Challenge> getChallenges(ChallengeType type) {
		List<Challenge> list = new ArrayList<Challenge>();
		for(Challenge c : this.challenges) {
			if(c.getType() == type)
				list.add(c);
		}
		return list;
	}

	public void setChallenges(List<Challenge> challenges)
	{
		this.challenges = challenges;
	}
	
	public LocalDate getStartDate()
	{
		return startDate;
	}
	
	public LocalDate getEndDate()
	{
		return endDate;
	}
	
	public TimeChallengeType getType()
	{
		return type;
	}
	
	public boolean isFinish() {
		for(Challenge c : this.challenges) {
			if(!c.isFinish())
				return false;
		}
		return true;
	}
}
