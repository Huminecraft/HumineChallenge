package com.humine.tokens.util;

public class Token
{

	private int token;
	
	public Token()
	{
		this.token = 0;
	}
	
	public Token(int number) {
		this.token = number;
	}
	
	public void addToken(int number) {
		this.token += number;
	}
	
	public void removeToken(int number) {
		this.token = ((this.token - number) >= 0) ? (this.token - number) : 0;
	}

	public int getToken()
	{
		return token;
	}

	public void setToken(int token)
	{
		this.token = token;
	}
}
