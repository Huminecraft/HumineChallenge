package fr.humine.utils.bankid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import fr.humine.utils.exceptions.SaveFileException;
import fr.humine.utils.exceptions.SettingMissingException;
import fr.humine.utils.shop.Savable;

public class BankItemStack implements Savable{

	private Map<Integer, ItemStack> itemStacks;
	
	public BankItemStack() {
		this.itemStacks = new HashMap<Integer, ItemStack>();
	}
	
	public ItemStack addItemStack(int id, ItemStack item) {
		return this.itemStacks.put(id, item);
	}
	
	public ItemStack removeItemStack(int id) {
		return this.itemStacks.remove(id);
	}
	
	public ItemStack getItemStack(int id) {
		return this.itemStacks.get(id);
	}
	
	public boolean contains(int id) {
		return this.itemStacks.containsKey(id);
	}
	
	public boolean contains(ItemStack item) {
		return this.itemStacks.containsValue(item);
	}
	
	public int getFreeID() {
		for(int i = 0; i < Integer.MAX_VALUE; i++) {
			if(!contains(i))
				return i;
		}
		return -1;
	}
	
	@Override
	public void save(File file) throws SaveFileException {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		for(Entry<Integer, ItemStack> entry : this.itemStacks.entrySet()) {
			config.set("Items." + entry.getKey(), entry.getValue());
		}
		
		try {
			config.save(file);
		} catch (IOException e) {
			throw new SaveFileException();
		}
	}

	@Override
	public void load(File file) throws FileNotFoundException, SettingMissingException {
		if(!file.exists())
			throw new FileNotFoundException();
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		if(!config.contains("Items"))
			throw new SettingMissingException("Aucun itemStack");
		
		for(String key : config.getConfigurationSection("Items").getKeys(false)) {
			ItemStack item = config.getItemStack("Items." + key);
			addItemStack(Integer.parseInt(key), item);
		}
	}

}
