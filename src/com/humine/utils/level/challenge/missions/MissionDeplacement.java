package com.humine.utils.level.challenge.missions;

public class MissionDeplacement extends Mission
{

	private static final long serialVersionUID = 6576079673719659446L;

	public MissionDeplacement(String missionName)
	{
		this.setMissionName(missionName);
		this.setDescription("");
		this.setNumber(1);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.DEPLACEMENT);
	}

	public MissionDeplacement(String missionName, int distance)
	{
		this.setMissionName(missionName);
		this.setDescription("");
		this.setNumber(distance);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.DEPLACEMENT);
	}

	public MissionDeplacement(String missionName, String description, int distance)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setNumber(distance);
		this.setDone(false);
		this.setMissionPremium(false);
		this.setMissionType(MissionType.DEPLACEMENT);
	}

	public MissionDeplacement(String missionName, String description, int distance, boolean premium)
	{
		this.setMissionName(missionName);
		this.setDescription(description);
		this.setNumber(distance);
		this.setDone(false);
		this.setMissionPremium(premium);
		this.setMissionType(MissionType.DEPLACEMENT);
	}
}
