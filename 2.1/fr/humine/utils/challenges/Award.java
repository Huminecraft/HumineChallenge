package fr.humine.utils.challenges;

import java.io.Serializable;

public class Award implements Serializable{

	private static final long serialVersionUID = 1551445127015996475L;
	private int exp;
	private int token;
	
	public Award(int exp, int token) {
		this.exp = exp;
		this.token = token;
	}
	
	public int getExp() {
		return exp;
	}
	
	public int getToken() {
		return token;
	}
}
