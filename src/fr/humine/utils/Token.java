package fr.humine.utils;

public class Token
{
	private int amount;
	
	public Token(int amount)
	{
		this.amount = amount;
	}
	
	public Token()
	{
		this(0);
	}
	
	public void addToken(int amount) {
		this.amount += amount;
	}
	
	public void removeToken(int amount) {
		this.amount -= amount;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
}
