package fr.humine.utils;

import java.util.ArrayList;
import java.util.List;

import humine.utils.cosmetiques.Cosmetique;

public class BankCosmetique {

	private List<Cosmetique> cosmetiques;

	public BankCosmetique(List<Cosmetique> cosmetiques) {
		this.cosmetiques = cosmetiques;
	}
	
	public BankCosmetique() {
		this(new ArrayList<Cosmetique>());
	}
	
	public boolean addCosmetique(Cosmetique cosmetiques) {
		return this.cosmetiques.add(cosmetiques);
	}
	
	public boolean removeCosmetique(Cosmetique cosmetiques) {
		return this.cosmetiques.remove(cosmetiques);
	}
	
	public boolean contains(String id) {
		for(Cosmetique c : this.cosmetiques) {
			if(c.getId().equals(id))
				return true;
		}
		return false;
	}
	
	public Cosmetique getCosmetique(String id) {
		for(Cosmetique c : this.cosmetiques) {
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}
	
	public List<Cosmetique> getCosmetiques() {
		return cosmetiques;
	}
	
	public void setCosmetiques(List<Cosmetique> cosmetiques) {
		this.cosmetiques = cosmetiques;
	}
}
