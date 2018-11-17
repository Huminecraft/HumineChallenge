package com.humine.level.challenge.util;

import java.util.ArrayList;


public class Challenge {

	private String challengeName;

	private ArrayList<MissionKill> kills;
	private ArrayList<MissionRecuperation> recuperations;

	private boolean finish;

	/*
	 * Class Challenge qui contiendra toutes les missions journalieres 
	 * 
	 * TODO une fois le challenge terminé faire les recompenses
	 */
	public Challenge(String name) {
		this.challengeName = name;
		this.kills = new ArrayList<MissionKill>();
		this.recuperations = new ArrayList<MissionRecuperation>();
		this.finish = false;
	}

	public void addMissionKill(MissionKill mission) {
		this.kills.add(mission);
	}

	public void addMissionRecuperation(MissionRecuperation mission) {
		this.recuperations.add(mission);
	}

	public boolean removeMissionKill(MissionKill mission) {
		boolean remove = false;
		int i = 0;

		while (i < this.kills.size() && remove == false) {
			if (this.kills.get(i).getMissionName().equals(mission.getMissionName())) {
				this.kills.remove(i);
				remove = true;
			}

			i++;
		}

		return remove;
	}

	public boolean removeMissionKill(String mission) {
		boolean remove = false;
		int i = 0;

		while (i < this.kills.size() && remove == false) {
			if (this.kills.get(i).getMissionName().equals(mission)) {
				this.kills.remove(i);
				remove = true;
			}

			i++;
		}

		return remove;
	}

	public boolean removeMissionRecuperation(MissionRecuperation mission) {
		boolean remove = false;
		int i = 0;

		while (i < this.recuperations.size() && remove == false) {
			if (this.recuperations.get(i).getMissionName().equals(mission.getMissionName())) {
				this.recuperations.remove(i);
				remove = true;
			}

			i++;
		}

		return remove;
	}

	public boolean removeMissionRecuperation(String mission) {
		boolean remove = false;
		int i = 0;

		while (i < this.recuperations.size() && remove == false) {
			if (this.recuperations.get(i).getMissionName().equals(mission)) {
				this.recuperations.remove(i);
				remove = true;
			}

			i++;
		}

		return remove;
	}

	public MissionKill getMissionKill(MissionKill mission) {
		MissionKill m = null;
		boolean find = false;
		int i = 0;

		while (i < this.kills.size() && find == false) {
			if (this.kills.get(i).getMissionName().equals(mission.getMissionName())) {
				m = this.kills.get(i);
				find = true;
			}

			i++;
		}

		return m;
	}

	public MissionRecuperation getMissionRecuperation(MissionRecuperation mission) {
		MissionRecuperation m = null;
		boolean find = false;
		int i = 0;

		while (i < this.recuperations.size() && find == false) {
			if (this.recuperations.get(i).getMissionName().equals(mission.getMissionName())) {
				m = this.recuperations.get(i);
				find = true;
			}

			i++;
		}

		return m;
	}

	public boolean checkFinish() {
		boolean fini = true;
		int i = 0;

		while (i < this.kills.size() && fini == true) {
			if (!this.kills.get(i).isDone()) {
				fini = false;
			}
			i++;
		}

		i = 0;

		while (i < this.recuperations.size() && fini == true) {
			if (!this.recuperations.get(i).isDone()) {
				fini = false;
			}
			i++;
		}

		return fini;
	}

	public String getChallengeName() {
		return challengeName;
	}

	public void setChallengeName(String challengeName) {
		this.challengeName = challengeName;
	}

	public ArrayList<MissionKill> getKills() {
		return kills;
	}

	public void setKills(ArrayList<MissionKill> kills) {
		this.kills = kills;
	}

	public ArrayList<MissionRecuperation> getRecuperations() {
		return recuperations;
	}

	public void setRecuperations(ArrayList<MissionRecuperation> recuperations) {
		this.recuperations = recuperations;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}
}
