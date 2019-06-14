package fr.humine.utils.challenge.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import fr.humine.utils.challenge.Challenge;

public class WeeklyChallenge extends TimeChallenge
{

	private static final long serialVersionUID = 5102009914443279864L;

	public WeeklyChallenge(List<Challenge> challenges, LocalDate startDate)
	{
		super(challenges, startDate.with(DayOfWeek.MONDAY), 7, TimeChallengeType.WEEKLY);
	}

	public WeeklyChallenge(LocalDate startDate)
	{
		super(startDate.with(DayOfWeek.MONDAY), 7, TimeChallengeType.WEEKLY);
	}
}
