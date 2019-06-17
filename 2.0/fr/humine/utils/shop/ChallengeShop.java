package fr.humine.utils.shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.humine.ChallengeMain;
import fr.humine.utils.exceptions.SaveFileException;
import fr.humine.utils.exceptions.SettingMissingException;
import fr.humine.utils.players.Challenger;

public class ChallengeShop implements Savable {

	private String name;
	private List<Page> pages;

	private String challenger;
	private int currentPage;

	public ChallengeShop(String name, List<Page> pages, Challenger challenger) {
		this.name = name;
		this.pages = pages;
		this.challenger = challenger.getChallengerName();
		this.currentPage = 0;
		
		if(challenger != null) {
			updateData();
		}
	}
	
	public ChallengeShop(String name, Challenger challenger) {
		this(name, new ArrayList<Page>(), challenger);
	}
	
	private void updateData() {
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(this.challenger);
		for(Page page : this.pages) {
			for(Palier palier : page.getFreePalier()) {
				if(challenger.getData().getPalierUnlock().contains(palier)) {
					palier.setUnlock(true);
				}
			}
			for(Palier palier : page.getPremiumPalier()) {
				if(challenger.getData().getPalierUnlock().contains(palier)) {
					palier.setUnlock(true);
				}
			}
		}
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
		return ChallengeMain.getInstance().getBankChallenger().getChallenger(this.challenger);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void openShop() {
		Player player = Bukkit.getPlayer(this.challenger);
		if (player == null)
			return;
		
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(this.challenger);
		Inventory inv = Page.PageToInventory(getFirstPage(), getName(), challenger.isPremium());
		player.openInventory(inv);
	}

	public void nextPage() {
		Player player = Bukkit.getPlayer(this.challenger);
		if (player == null)
			return;

		Page p = getPage(this.currentPage + 1);

		if (p == null)
			return;

		this.currentPage++;
		updateData();
		
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(this.challenger);
		Inventory inv = Page.PageToInventory(p, getName(), challenger.isPremium());
		player.openInventory(inv);
	}

	public void previousPage() {
		Player player = Bukkit.getPlayer(this.challenger);
		if (player == null)
			return;

		Page p = getPage(this.currentPage - 1);

		if (p == null)
			return;

		this.currentPage--;
		updateData();
		
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(this.challenger);
		Inventory inv = Page.PageToInventory(p, getName(), challenger.isPremium());
		player.openInventory(inv);
	}

	public void goToPage(int n) {
		Player player = Bukkit.getPlayer(this.challenger);
		if (player == null)
			return;

		Page p = getPage(n);

		if (p == null)
			return;

		this.currentPage = n;
		updateData();
		
		Challenger challenger = ChallengeMain.getInstance().getBankChallenger().getChallenger(this.challenger);
		Inventory inv = Page.PageToInventory(p, getName(), challenger.isPremium());
		player.openInventory(inv);
	}

	public void closeShop() {
		Player player = Bukkit.getPlayer(this.challenger);
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

	@Override
	public void save(File folder) throws SaveFileException, IOException {
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		File index = new File(folder, "index.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(index);
		config.set("ChallengeShop.Challenger", this.challenger);
		config.set("ChallengeShop.Name", this.name);
		config.save(index);
		
		int i = 0;
		for(Page page : this.pages) {
			File file = new File(folder, ""+i);
			page.save(file);
		}
	}

	@Override
	public void load(File folder) throws FileNotFoundException, SettingMissingException {
		if(!folder.exists())
			throw new FileNotFoundException();
		
		File index = new File(folder, "index.yml");
		if(index.exists())
			throw new FileNotFoundException("index.yml non existant");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(index);
		if(!config.contains("ChallengeShop.Challenger") || !config.contains("ChallengeShop.Name"))
			throw new SettingMissingException();
		
		this.challenger = config.getString("ChallengeShop.Challenger");
		this.name = config.getString("ChallengeShop.Name");
		
		for(File file : folder.listFiles()) {
			if(file.getName().startsWith("index"))
				continue;
			
			Page page = new Page();
			page.load(file);
			addPage(page);
		}
	}
	
	
}
