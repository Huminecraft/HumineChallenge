package fr.humine.pass.utils;

import java.util.Arrays;
import java.util.Comparator;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import fr.humine.utils.Challenger;
import fr.humine.utils.ItemShop;

public class Page
{
	private Line freeLine;
	private Line premiumLine;

	public Page() {
		this.freeLine = new Line();
		this.premiumLine = new Line();
	}
	
	public Line getFreeLine() {
		return freeLine;
	}
	
	public void setFreeLine(Line freeLine) {
		this.freeLine = freeLine;
	}
	
	public Line getPremiumLine() {
		return premiumLine;
	}
	
	public void setPremiumLine(Line premiumLine) {
		this.premiumLine = premiumLine;
	}

	public boolean isEmpty() {
		return this.freeLine.isEmpty() && this.premiumLine.isEmpty();
	}
	
	public boolean isFull() {
		return this.freeLine.isFull() && this.premiumLine.isFull();
	}
	
	public static Inventory PageToInventory(Page page, String name, Challenger challenger)
	{
		Inventory inv = Bukkit.createInventory(null, (9 * 5), name);

		inv.setItem(5, ItemShop.freeApple());
		inv.setItem((2 * 8) + 5, ItemShop.premiumApple());

		Arrays.sort(page.getFreeLine().getPaliers(), new Comparator<Palier>() {

			@Override
			public int compare(Palier arg0, Palier arg1) {
				if(arg0.getNumeroPalier() > arg1.getNumeroPalier())
					return 1;
				else
					return -1;
			}
		});
		
		Arrays.sort(page.getPremiumLine().getPaliers(), new Comparator<Palier>() {

			@Override
			public int compare(Palier arg0, Palier arg1) {
				if(arg0.getNumeroPalier() > arg1.getNumeroPalier())
					return 1;
				else
					return -1;
			}
		});
		
		for (int i = 0; i < page.getFreeLine().getPaliers().length; i++)
		{
			if (page.getFreeLine().getPaliers()[i] != null)
			{
				inv.setItem((9 + i), Palier.PalierToItemStack(page.getFreeLine().getPaliers()[i], challenger));
			}
		}
		
		for (int i = 0; i < page.getPremiumLine().getPaliers().length; i++)
		{
			if (page.getPremiumLine().getPaliers()[i] != null)
			{
				inv.setItem((9*3 + i), Palier.PalierToItemStack(page.getPremiumLine().getPaliers()[i], challenger));
			}
		}

		inv.setItem(inv.getSize() - 9, ItemShop.itemQuit());
		inv.setItem(inv.getSize() - 1, ItemShop.itemQuit());

		inv.setItem(inv.getSize() - 6, ItemShop.itemPreviousArrow());
		inv.setItem(inv.getSize() - 4, ItemShop.itemNextArrow());

		return inv;
	}
}
