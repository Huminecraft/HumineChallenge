package fr.humine.utils.pass;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import fr.humine.utils.Challenger;
import fr.humine.utils.ItemShop;

/**
 * Classe contenant 2 {@link Line}: une Gratuite et une Premium
 * 
 * @author Miza
 */
public class Page implements Serializable {
	private static final long serialVersionUID = -8974287729367150905L;
	private Line freeLine;
	private Line premiumLine;

	/**
	 * Constructeur de Page
	 */
	public Page() {
		this.freeLine = new Line();
		this.premiumLine = new Line();
	}

	/**
	 * @return la {@link Line} Gratuite
	 */
	public Line getFreeLine() {
		return freeLine;
	}

	/**
	 * Definir une nouvelle {@link Line} Gratuite
	 * 
	 * @param freeLine
	 */
	public void setFreeLine(Line freeLine) {
		this.freeLine = freeLine;
	}

	/**
	 * @return la {@link Line} Premium
	 */
	public Line getPremiumLine() {
		return premiumLine;
	}

	/**
	 * Definir une nouvelle {@link Line} Premium
	 * 
	 * @param premiumLine
	 */
	public void setPremiumLine(Line premiumLine) {
		this.premiumLine = premiumLine;
	}

	/**
	 * Verifie si la page est vide (donc les 2 {@link Line} vides)
	 * 
	 * @return true si la page est vide, sinon false
	 */
	public boolean isEmpty() {
		return this.freeLine.isEmpty() && this.premiumLine.isEmpty();
	}

	/**
	 * Verifie si la page est pleine (donc les 2 {@link Line} pleines)
	 * 
	 * @return true si la page est pleine, sinon false
	 */
	public boolean isFull() {
		return this.freeLine.isFull() && this.premiumLine.isFull();
	}

	/**
	 * Permet de convertir une Page en un {@link Inventory} directement utilisable
	 * <br />
	 * s'adapte selon le {@link Challenger}
	 * 
	 * @param page       la Page a convertir
	 * @param name       le titre de la Page
	 * @param challenger le challenger pour lequel afficher
	 * @return un {@link Inventory} utilisable
	 */
	public static Inventory PageToInventory(Page page, String name, Challenger challenger) {
		Inventory inv = Bukkit.createInventory(null, (9 * 5), name);

		inv.setItem(4, ItemShop.freeApple());
		inv.setItem((2 * 9) + 4, ItemShop.premiumApple());

		Arrays.sort(page.getFreeLine().getPaliers(), new Comparator<Palier>() {

			@Override
			public int compare(Palier arg0, Palier arg1) {
				if (arg0 != null && arg1 != null) {
					if (arg0.getNumeroPalier() > arg1.getNumeroPalier())
						return 1;
					else
						return -1;
				}
				return 0;
			}
		});

		Arrays.sort(page.getPremiumLine().getPaliers(), new Comparator<Palier>() {

			@Override
			public int compare(Palier arg0, Palier arg1) {
				if (arg0 != null && arg1 != null) {
					if (arg0.getNumeroPalier() > arg1.getNumeroPalier())
						return 1;
					else
						return -1;
				}
				return 0;
			}
		});

		for (int i = 0; i < page.getFreeLine().getPaliers().length; i++) {
			if (page.getFreeLine().getPaliers()[i] != null) {
				inv.setItem((9 + i), Palier.PalierToItemStack(page.getFreeLine().getPaliers()[i], challenger));
			}
		}

		for (int i = 0; i < page.getPremiumLine().getPaliers().length; i++) {
			if (page.getPremiumLine().getPaliers()[i] != null) {
				inv.setItem((9 * 3 + i), Palier.PalierToItemStack(page.getPremiumLine().getPaliers()[i], challenger));
			}
		}

		inv.setItem(inv.getSize() - 9, ItemShop.itemQuit());
		inv.setItem(inv.getSize() - 1, ItemShop.itemQuit());

		inv.setItem(inv.getSize() - 6, ItemShop.itemPreviousArrow());
		inv.setItem(inv.getSize() - 4, ItemShop.itemNextArrow());

		return inv;
	}

	@Override
	public String toString() {
		String str = "[Page: \n";
		str += "==>" + this.freeLine.toString() + "\n";
		str += "==>" + this.premiumLine.toString() + "\n";
		return str + "]";
	}

	/**
	 * Permet de cloner la Page tout en conservant les meme donnees
	 * 
	 * @return une nouvelle instance de la Page
	 */
	public Page clonage() {
		Page page = new Page();
		page.setFreeLine(freeLine.clonage());
		page.setPremiumLine(premiumLine.clonage());
		return page;
	}
}
