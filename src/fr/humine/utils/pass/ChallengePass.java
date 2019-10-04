package fr.humine.utils.pass;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;

public class ChallengePass {

	public static final String SHOPNAME = "SurvivalPass";
	
	private String name;
	private List<Page> pages;

	private Challenger challenger;
	private int currentPage;
	
	public ChallengePass(Challenger challenger) {
		this.challenger = challenger;
		this.name = SHOPNAME + " " + challenger.getName();
		this.pages = new ArrayList<Page>();
		this.currentPage = -1;
	}
	
	public ChallengePass()
	{
		this.challenger = null;
		this.name = SHOPNAME;
		this.pages = new ArrayList<Page>();
		this.currentPage = -1;
	}
	
	public boolean addPalier(Palier palier) {
		if(palier.getType() == TypePalier.FREE) {
			int i = 0;
			while(i < getPages().size()){
				if(getPage(i).getFreeLine().addPalier(palier)) {
					return true;
				}
				i++;
			}
			addPage(new Page());
			return getLastPage().getFreeLine().addPalier(palier);
			
		}
		else if(palier.getType() == TypePalier.PREMIUM) {
			int i = 0;
			while(i < getPages().size()){
				if(getPage(i).getPremiumLine().addPalier(palier)) {
					return true;
				}
				i++;
			}
			addPage(new Page());
			return getLastPage().getPremiumLine().addPalier(palier);
		}
		else
			return false;
	}
	
	
	public void update(ChallengePass pass) {
		List<Page> pages = new ArrayList<>();
		for(Page p : pass.getPages())
			pages.add(p.clonage());
		setPages(pages);
		
		for(Page p : this.pages) {
			for(Palier palier : p.getFreeLine().getPaliers()) {
				if(palier != null)
					palier.setUnlock((challenger.getToken().getAmount() >= palier.getTokenPass()));
			}
		}
		
		if(challenger.hasPremium()) {
			for(Page p : this.pages) {
				for(Palier palier : p.getPremiumLine().getPaliers()) {
					if(palier != null)
						palier.setUnlock((challenger.getToken().getAmount() >= palier.getTokenPass()));
				}
			}
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
		
		Inventory inv;
		
		if(getPages().isEmpty()) {
			ChallengeMain.sendMessage(challenger.getPlayer(), "ChallengePass vide");
			return;
		}
		
		inv = Page.PageToInventory(getFirstPage(), getName(), challenger);
		
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
	
	@Override
	public String toString()
	{
		String str = "[ChallengePass: \n";
		for(Page p : getPages())
			str += "==>" + p.toString() + "\n";
		return str + "]";
	}
}
