package fr.humine.utils.pass;

import java.io.Serializable;

public class Line implements Serializable
{
	private static final long serialVersionUID = 4076514741578193630L;
	private static final byte	lineLimit	= 9;
	private Palier[] paliers;
	
	public Line(Palier[] paliers) {
		this.paliers = paliers;
	}
	
	public Line()
	{
		this(new Palier[lineLimit]);
	}

	public boolean isEmpty()
	{
		for (int i = 0; i < this.paliers.length; i++)
		{
			if (this.paliers[i] != null)
				return false;
		}
		return true;
	}

	public boolean isFull()
	{
		for (int i = 0; i < this.paliers.length; i++)
		{
			if (this.paliers[i] == null)
				return false;
		}
		return true;
	}

	public boolean addPalier(Palier palier)
	{
		for (int i = 0; i < this.paliers.length; i++)
		{
			if (this.paliers[i] == null) {
				this.paliers[i] = palier;
				return true;
			}
		}
		return false;

	}

	public boolean setPalier(Palier palier, int slot)
	{
		if(slot < 0 && slot >= this.paliers.length)
			return false;
		
		this.paliers[slot] = palier;
		return true;
	}

	public Palier getPalier(int slot)
	{
		if(slot < 0 && slot >= this.paliers.length)
			return null;
		
		return this.paliers[slot];
	}

	public boolean removePalier(int slot)
	{
		if(slot < 0 && slot >= this.paliers.length)
			return false;
		
		this.paliers[slot] = null;
		return true;
	}

	public boolean contains(Palier palier)
	{
		for (int i = 0; i < this.paliers.length; i++)
		{
			if (this.paliers[i].equals(palier))
				return true;
		}
		return false;
	}

	public Palier[] getPaliers() {
		return paliers;
	}
	
	@Override
	public String toString()
	{
		String str = "[Line: \n";
		for(Palier p : this.paliers) {
			if(p != null)
				str += "===>" + p.toString() + "\n";
			else
				str += "===>NULL\n";
		}
		return str + "]";
	}

	public Line clonage() {
		Palier paliers[] = new Palier[lineLimit];
		int i = 0;
		for(Palier p : this.paliers)
			paliers[i++] = (p != null) ? p.clonage() : null;
		Line line = new Line(paliers);
		return line;
	}
}
