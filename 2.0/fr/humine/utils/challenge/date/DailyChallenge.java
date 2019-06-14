package fr.humine.utils.challenge.date;

import java.time.LocalDate;
import java.util.List;

import fr.humine.utils.challenge.Challenge;

public class DailyChallenge extends TimeChallenge
{

	private static final long serialVersionUID = -314211936250454694L;

	public DailyChallenge(List<Challenge> challenges, LocalDate startDate)
	{
		super(challenges, startDate, 0, TimeChallengeType.DAILY);
	}

	public DailyChallenge(LocalDate startDate)
	{
		super(startDate, 0, TimeChallengeType.DAILY);
	}
	
}
