package fr.humine.utils.shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.humine.utils.players.Challenger;

public class ChallengeShop implements Serializable {

	private static final long serialVersionUID = 5572482398621190603L;
	private String name;
	private List<Page> pages;

	private Challenger challenger;
	private int currentPage;

	public ChallengeShop(String name, List<Page> pages, Challenger challenger) {
		this.name = name;
		this.pages = pages;
		this.challenger = challenger;
		this.currentPage = 0;
		
		if(challenger != null) {
			updateData();
		}
	}
	
	private void updateData() {
		for(Page page : this.pages) {
			for(Palier palier : page.getFreePalier()) {
				if(this.challenger.getData().getPalierUnlock().contains(palier)) {
					palier.setUnlock(true);
				}
			}
			for(Palier palier : page.getPremiumPalier()) {
				if(this.challenger.getData().getPalierUnlock().contains(palier)) {
					palier.setUnlock(true);
				}
			}
		}
	}

	public ChallengeShop(String name, Challenger challenger) {
		this(name, new ArrayList<Page>(), challenger);
	}
	
	public void addPalier(Palier palier) {
		if(this.pages.isEmpty()) {
			Page p = new Page();
			p.addPalier(palier);
			addPage(p);
		}
		else if(!getLastPage().addPalier(palier)) {
			Page p = new Page();
			p.addPalier(palier);
			addPage(p);
		}
	}

	public boolean addPage(Page page) {
		return this.pages.add(page);
	}

	public boolean removePage(Page page) {
		return this.pages.remove(page);
	}

	public boolean contains(Page page) {
		return this.pages.contains(page);
	}

	public List<Page> getPages() {
		return pages;
	}
	
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public boolean isEmpty() {
		return this.pages.isEmpty();
	}

	public Page getFirstPage() {
		if (!isEmpty())
			return this.pages.get(0);
		return null;
	}

	public Page getLastPage() {
		if (!isEmpty())
			return this.pages.get(this.pages.size() - 1);
		return null;
	}

	public Page getPage(int n) {
		if (n >= 0 && n < this.pages.size())
			return this.pages.get(n);
		return null;
	}

	public Palier getPalier(TypePalier type, int numPalier) {
		if (type == TypePalier.FREE) {
			for (Page p : this.pages) {
				for (Palier palier : p.getFreePalier()) {
					if (palier.getNumeroPalier() == numPalier)
						return palier;
				}
			}
		} else if (type == TypePalier.PREMIUM) {
			for (Page p : this.pages) {
				for (Palier palier : p.getPremiumPalier()) {
					if (palier.getNumeroPalier() == numPalier)
						return palier;
				}
			}
		}

		return null;
	}

	public boolean containsPalier(TypePalier type, int numPalier) {
		if (type == TypePalier.FREE)
			return getPalier(TypePalier.FREE, numPalier) != null;
		else if (type == TypePalier.PREMIUM)
			return getPalier(TypePalier.PREMIUM, numPalier) != null;
		else
			return false;
	}

	public Challenger getChallenger() {
		return challenger;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void openShop() {
		Player player = Bukkit.getPlayer(this.challenger.getChallengerName());
		if (player == null)
			return;

		Inventory inv = Page.PageToInventory(getFirstPage(), getName(), challenger.isPremium());
		player.openInventory(inv);
	}

	public void nextPage() {
		Player player = Bukkit.getPlayer(this.challenger.getChallengerName());
		if (player == null)
			return;

		Page p = getPage(this.currentPage + 1);

		if (p == null)
			return;

		this.currentPage++;
		updateData();
		Inventory inv = Page.PageToInventory(p, getName(), challenger.isPremium());
		player.openInventory(inv);
	}

	public void previousPage() {
		Player player = Bukkit.getPlayer(this.challenger.getChallengerName());
		if (player == null)
			return;

		Page p = getPage(this.currentPage - 1);

		if (p == null)
			return;

		this.currentPage--;
		updateData();
		Inventory inv = Page.PageToInventory(p, getName(), challenger.isPremium());
		player.openInventory(inv);
	}

	public void goToPage(int n) {
		Player player = Bukkit.getPlayer(this.challenger.getChallengerName());
		if (player == null)
			return;

		Page p = getPage(n);

		if (p == null)
			return;

		this.currentPage = n;
		updateData();
		Inventory inv = Page.PageToInventory(p, getName(), challenger.isPremium());
		player.openInventory(inv);
	}

	public void closeShop() {
		Player player = Bukkit.getPlayer(this.challenger.getChallengerName());
		if (player == null)
			return;

		player.closeInventory();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((challenger == null) ? 0 : challenger.hashCode());
		result = prime * result + currentPage;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pages == null) ? 0 : pages.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChallengeShop other = (ChallengeShop) obj;
		if (challenger == null) {
			if (other.challenger != null)
				return false;
		} else if (!challenger.equals(other.challenger))
			return false;
		if (currentPage != other.currentPage)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pages == null) {
			if (other.pages != null)
				return false;
		} else if (!pages.equals(other.pages))
			return false;
		return true;
	}
	
	
}
