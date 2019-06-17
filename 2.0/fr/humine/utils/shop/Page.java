package fr.humine.utils.shop;

import java.io.Serializable;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class Page implements Serializable
{

	private static final long serialVersionUID = 9072389256378500539L;

	private static final byte	lineLimit	= 9;

	private Palier[]			freePalier;
	private Palier[]			premiumPalier;

	public Page(Palier[] freePalier, Palier[] premiumPalier)
	{
		this.freePalier = freePalier;
		this.premiumPalier = premiumPalier;
	}

	public Page(Palier[] freePalier)
	{
		this(freePalier, new Palier[lineLimit]);
	}

	public Page()
	{
		this(new Palier[lineLimit], new Palier[lineLimit]);
	}

	public boolean freePalierIsEmpty()
	{
		for (int i = 0; i < this.freePalier.length; i++)
		{
			if (this.freePalier[i] != null)
				return false;
		}
		return true;
	}

	public boolean premiumPalierIsEmpty()
	{
		for (int i = 0; i < this.premiumPalier.length; i++)
		{
			if (this.premiumPalier[i] != null)
				return false;
		}
		return true;
	}

	public boolean isEmpty()
	{
		return freePalierIsEmpty() && premiumPalierIsEmpty();
	}

	public boolean freePalierIsFull()
	{
		for (int i = 0; i < this.freePalier.length; i++)
		{
			if (this.freePalier[i] == null)
				return false;
		}
		return true;
	}

	public boolean premiumPalierIsFull()
	{
		for (int i = 0; i < this.freePalier.length; i++)
		{
			if (this.freePalier[i] == null)
				return false;
		}
		return true;
	}

	public boolean isFull()
	{
		return freePalierIsFull() && premiumPalierIsFull();
	}

	public boolean addPalier(Palier palier)
	{
		if (palier.getType() == TypePalier.FREE)
		{
			if (!freePalierIsFull())
			{
				for (int i = 0; i < this.freePalier.length; i++)
				{
					if (this.freePalier[i] == null)
					{
						this.freePalier[i] = palier;
						return true;
					}
				}
			}
		}
		else if (palier.getType() == TypePalier.PREMIUM)
		{
			if (!premiumPalierIsFull())
			{
				for (int i = 0; i < this.premiumPalier.length; i++)
				{
					if (this.premiumPalier[i] == null)
					{
						this.premiumPalier[i] = palier;
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean setPalier(Palier palier, int slot)
	{
		if (palier.getType() == TypePalier.FREE)
		{
			if (slot >= 0 && slot < this.freePalier.length)
			{
				this.freePalier[slot] = palier;
				return true;
			}
		}
		else if (palier.getType() == TypePalier.PREMIUM)
		{
			if (slot >= 0 && slot < this.premiumPalier.length)
			{
				this.premiumPalier[slot] = palier;
				return true;
			}
		}

		return false;
	}

	public Palier getPalier(TypePalier type, int slot)
	{
		if (type == TypePalier.FREE)
		{
			if (slot >= 0 && slot < this.freePalier.length)
			{
				return this.freePalier[slot];
			}
		}
		else if (type == TypePalier.PREMIUM)
		{
			if (slot >= 0 && slot < this.premiumPalier.length)
			{
				return this.premiumPalier[slot];
			}
		}

		return null;
	}

	public boolean removePalier(Palier palier)
	{
		if (palier.getType() == TypePalier.FREE)
		{
			for (int i = 0; i < this.freePalier.length; i++)
			{
				if (this.freePalier[i] != null)
				{
					if (this.freePalier[i].equals(palier))
					{
						this.freePalier[i] = null;
						return true;
					}
				}
			}
		}
		else if (palier.getType() == TypePalier.PREMIUM)
		{
			for (int i = 0; i < this.premiumPalier.length; i++)
			{
				if (this.premiumPalier[i] != null)
				{
					if (this.premiumPalier[i].equals(palier))
					{
						this.premiumPalier[i] = null;
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean removePalier(TypePalier type, int slot)
	{
		if (type == TypePalier.FREE)
		{
			if (slot >= 0 && slot < this.freePalier.length)
			{
				this.freePalier[slot] = null;
				return true;
			}
		}
		else if (type == TypePalier.PREMIUM)
		{
			if (slot >= 0 && slot < this.premiumPalier.length)
			{
				this.premiumPalier[slot] = null;
				return true;
			}
		}

		return false;
	}

	public boolean contains(Palier palier)
	{
		if (palier.getType() == TypePalier.FREE)
		{
			for (int i = 0; i < this.freePalier.length; i++)
			{
				if (this.freePalier[i].equals(palier))
				{
					return true;
				}
			}
		}
		else if (palier.getType() == TypePalier.PREMIUM)
		{
			for (int i = 0; i < this.premiumPalier.length; i++)
			{
				if (this.premiumPalier[i].equals(palier))
				{
					return true;
				}
			}
		}

		return false;
	}

	public Palier[] getFreePalier()
	{
		return freePalier;
	}

	public Palier[] getPremiumPalier()
	{
		return premiumPalier;
	}

	public static Inventory PageToInventory(Page page, String name, boolean isPremium)
	{
		Inventory inv = Bukkit.createInventory(null, (9 * 5), name);

		inv.setItem(5, ItemShop.freeApple());
		inv.setItem((2 * 8) + 5, ItemShop.premiumApple());

		Arrays.sort(page.getFreePalier());
		Arrays.sort(page.getPremiumPalier());

		for (int i = 0; i < page.getFreePalier().length; i++)
		{
			if (page.getFreePalier()[i] != null)
			{
				inv.setItem((9 + i), Palier.PalierToItemStack(page.getFreePalier()[i], isPremium));
			}
		}

		for (int i = 0; i < page.getPremiumPalier().length; i++)
		{
			if (page.getPremiumPalier()[i] != null)
			{
				inv.setItem(((9 * 3) + i), Palier.PalierToItemStack(page.getPremiumPalier()[i], isPremium));
			}
		}

		inv.setItem(inv.getSize() - 9, ItemShop.itemQuit());
		inv.setItem(inv.getSize() - 1, ItemShop.itemQuit());

		inv.setItem(inv.getSize() - 6, ItemShop.itemPreviousArrow());
		inv.setItem(inv.getSize() - 4, ItemShop.itemNextArrow());

		return inv;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(freePalier);
		result = prime * result + Arrays.hashCode(premiumPalier);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (!Arrays.equals(freePalier, other.freePalier))
			return false;
		if (!Arrays.equals(premiumPalier, other.premiumPalier))
			return false;
		return true;
	}

}
