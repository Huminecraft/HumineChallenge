package fr.humine.utils.shop;

import humine.utils.cosmetiques.Cosmetique;

public class Page extends humine.utils.shop.Page{

	private static final int lineLimit = 9;
	
	public Page(String name, int size) {
		super(name, size);
		Cosmetique[] cosmetiques = new Cosmetique[lineLimit * 2];
		setCosmetiques(cosmetiques);
	}

}
