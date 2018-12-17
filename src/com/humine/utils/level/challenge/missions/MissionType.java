package com.humine.utils.level.challenge.missions;

public enum MissionType
{
	ADOPTION,
	CRAFT,
	DEPLACEMENT,
	DISCOVER,
	EAT,
	KILL,
	POSE,
	RECUPERATION;
	
	
	public static MissionType getType(String value) {
		if(value.equalsIgnoreCase("adoption"))
			return ADOPTION;
		else if(value.equalsIgnoreCase("craft"))
			return CRAFT;
		else if(value.equalsIgnoreCase("deplacement"))
			return DEPLACEMENT;
		else if(value.equalsIgnoreCase("discover"))
			return DISCOVER;
		else if(value.equalsIgnoreCase("eat"))
			return EAT;
		else if(value.equalsIgnoreCase("kill"))
			return KILL;
		else if(value.equalsIgnoreCase("pose"))
			return POSE;
		else if(value.equalsIgnoreCase("recuperation"))
			return RECUPERATION;
		else
			return null;
	}
}
