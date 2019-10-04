package fr.humine.utils.pass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import humine.utils.cosmetiques.Cosmetique;

public class Palier implements Serializable
{

	private static final long serialVersionUID = 7556577054897105339L;
	private int					numeroPalier;
	private Material			itemRepresentation;
	private String				cosmetique;
	private boolean				unlock;
	private int					tokenPass;
	private int					humis;
	private int					pixel;
	private TypePalier			type;

	public Palier(int numPalier, ItemStack itemRepresentation, int humis, int pixel, int tokenPass, Cosmetique cosmetique,
			boolean unlock, boolean premium)
	{
		this.numeroPalier = numPalier;
		this.itemRepresentation = itemRepresentation.getType();
		this.cosmetique = (cosmetique != null) ? cosmetique.getId() : "";
		this.unlock = unlock;
		this.tokenPass = tokenPass;

		if (premium)
			this.type = TypePalier.PREMIUM;
		else
			this.type = TypePalier.FREE;

		this.humis = humis;
		this.pixel = pixel;
	}
	
	public boolean isUnlock()
	{
		return unlock;
	}

	public void setUnlock(boolean unlock)
	{
		this.unlock = unlock;
	}

	public int getTokenPass()
	{
		return tokenPass;
	}

	public void setTokenPass(int tokenPass)
	{
		this.tokenPass = tokenPass;
	}

	public int getNumeroPalier()
	{
		return numeroPalier;
	}

	public void setHumis(int humis)
	{
		this.humis = humis;
	}

	public void setPixel(int pixel)
	{
		this.pixel = pixel;
	}

	public TypePalier getType()
	{
		return type;
	}

	public void setType(TypePalier type)
	{
		this.type = type;
	}


	public static ItemStack PalierToItemStack(Palier palier, Challenger challenger)
	{
		ItemStack item = palier.getItemRepresentation();
		ChatColor color;
		
		if((palier.getType() == TypePalier.PREMIUM && challenger.hasPremium() && challenger.getToken().getAmount() >= palier.getTokenPass()) || (palier.getType() == TypePalier.FREE && challenger.getToken().getAmount() >= palier.getTokenPass())) {
			palier.setUnlock(true);
		}
		
		if(palier.isUnlock())
			color = ChatColor.GREEN;
		else if(palier.getType() == TypePalier.PREMIUM && !challenger.hasPremium())
			color = ChatColor.RED;
		else
			color = ChatColor.YELLOW;
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(color + "Palier " + palier.getNumeroPalier());

		List<String> lores = new ArrayList<>();

		lores.add(color + "Token necessaire: " + palier.getTokenPass());
		lores.add(" ");

		if (palier.getCosmetique() != null)
		{
			lores.add(color + "Cosmetique: " + palier.getCosmetique().getName());
		}

		if(palier.getType() == TypePalier.PREMIUM && !challenger.hasPremium()) {
			lores.add(" ");
			lores.add(color + "Blocked");
		}
		
		meta.setLore(lores);
		item.setItemMeta(meta);

		if(palier.isUnlock() == false)
			item.setAmount(palier.getNumeroPalier());
		else
			item.setAmount(1);
		
		return item;
	}
	
	public static void descriptionPalier(CommandSender sender, Palier palier) {
		sender.sendMessage(ChatColor.GOLD + "===Description Palier===");
		
		sender.sendMessage("Numero Palier: " + ChatColor.AQUA + palier.getNumeroPalier());
		sender.sendMessage("Type de palier: " + ChatColor.AQUA + palier.getType());
		sender.sendMessage("Item Representation: " + ChatColor.AQUA + palier.getItemRepresentation().getType());
		sender.sendMessage("Prix Humis : " + ChatColor.AQUA + palier.getHumis());
		sender.sendMessage("Prix Pixel : " + ChatColor.AQUA + palier.getPixel());
		sender.sendMessage("Nombre de token necessaire : " + ChatColor.AQUA + palier.getTokenPass());
		sender.sendMessage("Cosmetique : " + ChatColor.AQUA + ((palier.getCosmetique() != null) ? palier.getCosmetique().getId() : "null"));
		
		sender.sendMessage(ChatColor.GOLD + "===Description Palier===");
	}

	public ItemStack getItemRepresentation() {
		return new ItemStack(itemRepresentation);
	}

	public void setItemRepresentation(ItemStack itemRepresentation) {
		this.itemRepresentation = itemRepresentation.getType();
	}

	public Cosmetique getCosmetique() {
		return ChallengeMain.getInstance().getBankCosmetique().getCosmetique(cosmetique);
	}

	public void setCosmetique(Cosmetique cosmetique) {
		this.cosmetique = cosmetique.getId();
	}

	public int getHumis() {
		return humis;
	}

	public int getPixel() {
		return pixel;
	}

	@Override
	public String toString()
	{
		return "Palier [numeroPalier=" + numeroPalier + ", itemRepresentation=" + itemRepresentation + ", cosmetique="
				+ cosmetique + ", unlock=" + unlock + ", tokenPass=" + tokenPass + ", humis=" + humis + ", pixel="
				+ pixel + ", type=" + type + "]";
	}
	
	public Palier clonage() {
		Cosmetique c = ChallengeMain.getInstance().getBankCosmetique().getCosmetique(cosmetique);
		boolean premium = (type == TypePalier.FREE) ? false : true;
		Palier palier = new Palier(numeroPalier, new ItemStack(itemRepresentation), humis, pixel, tokenPass, c, unlock, premium);
		return palier;
	}
	
}
