package fr.humine.utils.pass;

public class Line
{
	private static final byte	lineLimit	= 9;
	private Palier[] paliers;
	
	public Line()
	{
		this.paliers = new Palier[lineLimit];
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

}
