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
			return CRAFT;
		else if(value.equalsIgnoreCase("discover"))
			return CRAFT;
		else if(value.equalsIgnoreCase("eat"))
			return CRAFT;
		else if(value.equalsIgnoreCase("kill"))
			return CRAFT;
		else if(value.equalsIgnoreCase("pose"))
			return CRAFT;
		else if(value.equalsIgnoreCase("recuperation"))
			return CRAFT;
		else
			return null;
	}
}
