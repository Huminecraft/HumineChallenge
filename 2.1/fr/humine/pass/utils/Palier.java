package fr.humine.pass.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.humine.utils.Challenger;
import fr.humine.utils.shop.TypePalier;
import humine.utils.cosmetiques.Cosmetique;

public class Palier
{

	private int					numeroPalier;
	private ItemStack			itemRepresentation;
	private Cosmetique			cosmetique;
	private boolean				unlock;
	private int					tokenPass;
	private int					humis;
	private int					pixel;
	private TypePalier			type;

	public Palier(int numPalier, ItemStack itemRepresentation, int humis, int pixel, int tokenPass, Cosmetique cosmetique,
			boolean unlock, boolean premium)
	{
		this.numeroPalier = numPalier;
		this.itemRepresentation = itemRepresentation;
		this.cosmetique = cosmetique;
		this.unlock = unlock;

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
		item.setAmount(palier.getNumeroPalier());
		ChatColor color;
		
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
		item.setAmount(palier.getNumeroPalier());

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
		return itemRepresentation;
	}

	public void setItemRepresentation(ItemStack itemRepresentation) {
		this.itemRepresentation = itemRepresentation;
	}

	public Cosmetique getCosmetique() {
		return cosmetique;
	}

	public void setCosmetique(Cosmetique cosmetique) {
		this.cosmetique = cosmetique;
	}

	public int getHumis() {
		return humis;
	}

	public int getPixel() {
		return pixel;
	}
	
}
