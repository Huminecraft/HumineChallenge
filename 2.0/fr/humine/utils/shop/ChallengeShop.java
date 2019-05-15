package fr.humine.utils.shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.humine.utils.exceptions.SaveFileException;
import fr.humine.utils.exceptions.SettingMissingException;

public class ChallengeShop implements Savable{

	private String name;
	private List<Page> pages;
	private Map<Player, Integer> playersInShop;
	
	public ChallengeShop(String name, List<Page> pages) {
		this.name = name;
		this.pages = pages;
		
		this.playersInShop = new HashMap<Player, Integer>();
	}
	
	public ChallengeShop(String name) {
		this(name, new ArrayList<Page>());
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
	
	public boolean isEmpty() {
		return this.pages.isEmpty();
	}
	
	public Page getFirstPage() {
		if(!isEmpty())
			return this.pages.get(0);
		return null;
	}
	
	public Page getLastPage() {
		if(!isEmpty())
			return this.pages.get(this.pages.size() - 1);
		return null;
	}
	
	public Page getPage(int n) {
		if(n >= 0 && n < this.pages.size())
			return this.pages.get(n);
		return null;
	}
	
	public Palier getPalier(int numPalier) {
		for(Page p : this.pages) {
			for(Palier palier : p.getFreePalier()) {
				if(palier.getNumeroPalier() == numPalier)
					return palier;
			}
			for(Palier palier : p.getPremiumPalier()) {
				if(palier.getNumeroPalier() == numPalier)
					return palier;
			}
		}
		return null;
	}
	
	public boolean containsPalier(int numPalier) {
		return getPalier(numPalier) != null;
	}
	
	public Map<Player, Integer> getPlayersInShop() {
		return playersInShop;
	}
	
	public void addPlayerInShop(Player player) {
		this.playersInShop.put(player, 0);
	}
	
	public void removePlayerInShop(Player player) {
		this.playersInShop.remove(player);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void openShop(Player player) {
		Inventory inv = Page.PageToInventory(getFirstPage(), getName());
		addPlayerInShop(player);
		player.openInventory(inv);
	}
	
	public void nextPage(Player player) {
		if(!this.playersInShop.containsKey(player))
			return;
		
		int n = this.playersInShop.get(player) + 1;
		Page p = getPage(n);
		
		if(p == null)
			return;
		
		this.playersInShop.replace(player, n);
		
		Inventory inv = Page.PageToInventory(p, getName());
		player.openInventory(inv);
	}
	
	public void previousPage(Player player) {
		if(!this.playersInShop.containsKey(player))
			return;
		
		int n = this.playersInShop.get(player) - 1;
		Page p = getPage(n);
		
		if(p == null)
			return;
		
		this.playersInShop.replace(player, n);
		
		Inventory inv = Page.PageToInventory(p, getName());
		player.openInventory(inv);
	}
	
	public void goToPage(Player player, int n) {
		if(!this.playersInShop.containsKey(player))
			return;
		
		Page p = getPage(n);
		
		if(p == null)
			return;
		
		this.playersInShop.replace(player, n);
		
		Inventory inv = Page.PageToInventory(p, getName());
		player.openInventory(inv);
	}
	
	public void closeShop(Player player) {
		this.playersInShop.remove(player);
		player.closeInventory();
	}

	@Override
	public void save(File folder) throws SaveFileException {
		if(!folder.exists())
			folder.mkdirs();
		
		File index = new File(folder, "index.yml");
		
		try {
			index.createNewFile();
			
			FileConfiguration config = YamlConfiguration.loadConfiguration(index);
			config.set("Name", this.name);
			config.save(index);
			
		} catch (IOException e) {
			throw new SaveFileException();
		}
		
		for(int i = 0; i < this.pages.size(); i++) {
			File f = new File(folder, "Page " + i);
			this.pages.get(i).save(f);
		}
	}

	@Override
	public void load(File folder) throws FileNotFoundException, SettingMissingException {
		if(!folder.exists())
			throw new FileNotFoundException("Dossier du ChallengeShop introuvable");
		
		File index = new File(folder, "index.yml");
		if(!index.exists())
			throw new FileNotFoundException("index.yml du ChallengeShop introuvable");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(index);
		if(!config.contains("Name"))
			throw new SettingMissingException();
		
		this.name = config.getString("Name");
		
		for(File file : folder.listFiles()) {
			if(file.getName().startsWith("index"))
				continue;
			
			Page page = new Page();
			page.load(file);
			
			this.pages.add(page);
		}
	}
}
