package fr.humine.utils.shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.humine.ChallengeMain;
import humine.utils.cosmetiques.Cosmetique;

public class Palier implements Achievement, Comparable<Palier>, Comparator<Palier>, Serializable
{

	private static final long	serialVersionUID	= -7069299696895711227L;
	private int					numeroPalier;
	private String				itemRepresentationID;
	private String				cosmetiqueID;

	private boolean				unlock;

	private int					tokenPass;

	private int					humis;
	private int					pixel;

	private TypePalier			type;

	public Palier(int numPalier, String itemRepresentationID, int humis, int pixel, int tokenPass, String cosmetiqueID,
			boolean unlock, boolean premium)
	{
		this.numeroPalier = numPalier;
		this.itemRepresentationID = itemRepresentationID;
		this.cosmetiqueID = cosmetiqueID;
		this.unlock = unlock;

		if (premium)
			this.type = TypePalier.PREMIUM;
		else
			this.type = TypePalier.FREE;

		this.humis = humis;
		this.pixel = pixel;

	}

	public Palier(int numPalier, String itemRepresentationID, int humis, int pixel, int tokenPass, String cosmetiqueID,
			boolean unlock)
	{
		this(numPalier, itemRepresentationID, humis, pixel, tokenPass, cosmetiqueID, unlock, false);
	}

	public Palier(int numPalier, String itemRepresentationID, int humis, int pixel, int tokenPass, String cosmetiqueID)
	{
		this(numPalier, itemRepresentationID, humis, pixel, tokenPass, cosmetiqueID, false, false);
	}

	public Palier(int numPalier, String itemRepresentationID, int humis, int pixel, int tokenPass)
	{
		this(numPalier, itemRepresentationID, humis, pixel, tokenPass, null, false, false);
	}

	@Override
	public void giveRecompense(Player player)
	{
		// TODO donner les recompenses
	}

	public ItemStack getItemRepresentation()
	{
		if(!this.itemRepresentationID.equals("") && this.itemRepresentationID != null)
			return ChallengeMain.getInstance().getBankItemStack().getItemStack(Integer.parseInt(this.itemRepresentationID));
		else
			return null;
	}
	
	public String getItemRepresentationID()
	{
		return this.itemRepresentationID;
	}

	public void setItemRepresentationID(String itemRepresentationID)
	{
		this.itemRepresentationID = itemRepresentationID;
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

	public void setCosmetique(String cosmetiqueID)
	{
		this.cosmetiqueID = cosmetiqueID;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cosmetiqueID == null) ? 0 : cosmetiqueID.hashCode());
		result = prime * result + humis;
		result = prime * result + ((itemRepresentationID == null) ? 0 : itemRepresentationID.hashCode());
		result = prime * result + numeroPalier;
		result = prime * result + pixel;
		result = prime * result + tokenPass;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + (unlock ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Palier other = (Palier) obj;
		if (cosmetiqueID == null)
		{
			if (other.cosmetiqueID != null)
				return false;
		}
		else if (!cosmetiqueID.equals(other.cosmetiqueID))
			return false;
		if (humis != other.humis)
			return false;
		if (itemRepresentationID == null)
		{
			if (other.itemRepresentationID != null)
				return false;
		}
		else if (!itemRepresentationID.equals(other.itemRepresentationID))
			return false;
		if (numeroPalier != other.numeroPalier)
			return false;
		if (pixel != other.pixel)
			return false;
		if (tokenPass != other.tokenPass)
			return false;
		if (type != other.type)
			return false;
		if (unlock != other.unlock)
			return false;
		return true;
	}

	public static ItemStack PalierToItemStack(Palier palier, boolean isPremium)
	{
		ItemStack item = palier.getItemRepresentation();
		ChatColor color;

		if (palier.isUnlock())
			color = ChatColor.GREEN;
		else
			color = ChatColor.YELLOW;
		
		if(palier.getType() == TypePalier.PREMIUM && !isPremium)
			color = ChatColor.RED;

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(color + "Palier " + palier.getNumeroPalier());

		List<String> lores = new ArrayList<>();

		lores.add(color + "Nombre de token pour le debloquer: " + palier.getTokenPass());
		lores.add(" ");

		if (palier.getCosmetique() != null)
		{
			lores.add(color + "Cosmetique: " + palier.getCosmetique().getName());
		}

		if(palier.getType() == TypePalier.PREMIUM && !isPremium) {
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
		sender.sendMessage("Cosmetique : " + ChatColor.AQUA + palier.getCosmetiqueID());
		
		sender.sendMessage(ChatColor.GOLD + "===Description Palier===");
	}

	@Override
	public int compareTo(Palier palier)
	{
		if (this.numeroPalier < palier.getNumeroPalier())
			return 1;
		else if (this.numeroPalier > palier.getNumeroPalier())
			return -1;
		else
			return 0;
	}

	@Override
	public int compare(Palier p1, Palier p2)
	{
		return p1.compareTo(p2);
	}

	@Override
	public Cosmetique getCosmetique()
	{
		if(!this.cosmetiqueID.equals("") && this.cosmetiqueID != null)
			return ChallengeMain.getInstance().getBankCosmetique().getCosmetique(this.cosmetiqueID);
		else
			return null;
	}
	
	public String getCosmetiqueID() {
		return cosmetiqueID;
	}

	public int getHumis() {
		return humis;
	}

	public int getPixel() {
		return pixel;
	}
	
}
