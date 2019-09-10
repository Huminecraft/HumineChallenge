package fr.humine.pass.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.humine.utils.Challenger;

public class ChallengeShop {

	public static final String SHOPNAME = "ChallengeShop";
	
	private String name;
	private List<Page> pages;

	private Challenger challenger;
	private int currentPage;
	
	public ChallengeShop(Challenger challenger) {
		this.challenger = challenger;
		this.name = SHOPNAME + " " + challenger.getName();
		this.pages = new ArrayList<Page>();
		this.currentPage = -1;
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

	public Challenger getChallenger() {
		return challenger;
	}

	public void setChallenger(Challenger challenger) {
		this.challenger = challenger;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getName() {
		return name;
	}
	
	public void openShop() {
		Player player = challenger.getPlayer();
		if (player == null)
			return;
		
		if(getPages().isEmpty())
			return;
		
		Inventory inv = Page.PageToInventory(getFirstPage(), getName(), challenger);
		currentPage = 0;
		player.openInventory(inv);
	}

	public void nextPage() {
		Player player = challenger.getPlayer();
		if (player == null)
			return;

		Page p = getPage(this.currentPage + 1);

		if (p == null)
			return;

		this.currentPage++;

		Inventory inv = Page.PageToInventory(p, getName(), challenger);
		player.openInventory(inv);
	}

	public void previousPage() {
		Player player = challenger.getPlayer();
		if (player == null)
			return;

		Page p = getPage(this.currentPage - 1);

		if (p == null)
			return;

		this.currentPage--;
		
		Inventory inv = Page.PageToInventory(p, getName(), challenger);
		player.openInventory(inv);
	}

	public void goToPage(int n) {
		Player player = challenger.getPlayer();
		if (player == null)
			return;

		Page p = getPage(n);

		if (p == null)
			return;

		this.currentPage = n;
		
		Inventory inv = Page.PageToInventory(p, getName(), challenger);
		player.openInventory(inv);
	}

	public void closeShop() {
		Player player = challenger.getPlayer();
		if (player == null)
			return;

		currentPage = -1;
		player.closeInventory();
	}
	
	
}
