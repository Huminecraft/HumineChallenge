package fr.humine.utils.shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ChallengeShop implements Serializable
{

	private static final long		serialVersionUID	= 5572482398621190603L;
	private String					name;
	private List<Page>				pages;
	private Map<String, Integer>	playersInShop;

	public ChallengeShop(String name, List<Page> pages)
	{
		this.name = name;
		this.pages = pages;

		this.playersInShop = new HashMap<String, Integer>();
	}

	public ChallengeShop(String name)
	{
		this(name, new ArrayList<Page>());
	}

	public boolean addPage(Page page)
	{
		return this.pages.add(page);
	}

	public boolean removePage(Page page)
	{
		return this.pages.remove(page);
	}

	public boolean contains(Page page)
	{
		return this.pages.contains(page);
	}

	public List<Page> getPages()
	{
		return pages;
	}

	public boolean isEmpty()
	{
		return this.pages.isEmpty();
	}

	public Page getFirstPage()
	{
		if (!isEmpty())
			return this.pages.get(0);
		return null;
	}

	public Page getLastPage()
	{
		if (!isEmpty())
			return this.pages.get(this.pages.size() - 1);
		return null;
	}

	public Page getPage(int n)
	{
		if (n >= 0 && n < this.pages.size())
			return this.pages.get(n);
		return null;
	}

	public Palier getPalier(TypePalier type, int numPalier)
	{
		if (type == TypePalier.FREE)
		{
			for (Page p : this.pages)
			{
				for (Palier palier : p.getFreePalier())
				{
					if (palier.getNumeroPalier() == numPalier)
						return palier;
				}
			}
		}
		else if (type == TypePalier.PREMIUM)
		{
			for (Page p : this.pages)
			{
				for (Palier palier : p.getPremiumPalier())
				{
					if (palier.getNumeroPalier() == numPalier)
						return palier;
				}
			}
		}

		return null;
	}

	public boolean containsPalier(TypePalier type, int numPalier)
	{
		if (type == TypePalier.FREE)
			return getPalier(TypePalier.FREE, numPalier) != null;
		else if (type == TypePalier.PREMIUM)
			return getPalier(TypePalier.PREMIUM, numPalier) != null;
		else
			return false;
	}

	public Map<String, Integer> getPlayersInShop()
	{
		return playersInShop;
	}

	public void addPlayerInShop(Player player)
	{
		this.playersInShop.put(player.getName(), 0);
	}

	public void removePlayerInShop(Player player)
	{
		this.playersInShop.remove(player.getName());
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void openShop(Player player)
	{
		Inventory inv = Page.PageToInventory(getFirstPage(), getName());
		addPlayerInShop(player);
		player.openInventory(inv);
	}

	public void nextPage(Player player)
	{
		if (!this.playersInShop.containsKey(player.getName()))
			return;

		int n = this.playersInShop.get(player.getName()) + 1;
		Page p = getPage(n);

		if (p == null)
			return;

		this.playersInShop.replace(player.getName(), n);

		Inventory inv = Page.PageToInventory(p, getName());
		player.openInventory(inv);
	}

	public void previousPage(Player player)
	{
		if (!this.playersInShop.containsKey(player.getName()))
			return;

		int n = this.playersInShop.get(player.getName()) - 1;
		Page p = getPage(n);

		if (p == null)
			return;

		this.playersInShop.replace(player.getName(), n);

		Inventory inv = Page.PageToInventory(p, getName());
		player.openInventory(inv);
	}

	public void goToPage(Player player, int n)
	{
		if (!this.playersInShop.containsKey(player.getName()))
			return;

		Page p = getPage(n);

		if (p == null)
			return;

		this.playersInShop.replace(player.getName(), n);

		Inventory inv = Page.PageToInventory(p, getName());
		player.openInventory(inv);
	}

	public void closeShop(Player player)
	{
		this.playersInShop.remove(player);
		player.closeInventory();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pages == null) ? 0 : pages.hashCode());
		result = prime * result + ((playersInShop == null) ? 0 : playersInShop.hashCode());
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
		ChallengeShop other = (ChallengeShop) obj;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (pages == null)
		{
			if (other.pages != null)
				return false;
		}
		else if (!pages.equals(other.pages))
			return false;
		if (playersInShop == null)
		{
			if (other.playersInShop != null)
				return false;
		}
		else if (!playersInShop.equals(other.playersInShop))
			return false;
		return true;
	}

}
