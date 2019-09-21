package fr.humine.utils.challenges;

public class Award {

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
