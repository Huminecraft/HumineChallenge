package com.humine.levels.util;

public class Level {

	private int level;

	public Level() {
		this.level = 0;
	}

	public Level(int startLevel) {
		this.level = startLevel;
	}

	public void addLevel(int l) {
		this.level += l;
	}

	public void removeLevel(int l) {
		this.level -= l;
	}

	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int l) {
		this.level = l;
	}
}
