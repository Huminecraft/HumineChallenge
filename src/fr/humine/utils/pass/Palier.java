package fr.humine.utils.pass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.Challenger;
import fr.humine.utils.Token;
import humine.utils.cosmetiques.Cosmetique;

/**
 * Classe representant les paliers du jeu se trouvant dans les
 * {@link ChallengePass} <br />
 * Les Paliers sont {@link Serializable}
 * 
 * @author Miza
 */
public class Palier implements Serializable, Comparable<Palier>, Comparator<Palier> {

	private static final long serialVersionUID = 7556577054897105339L;
	private int numeroPalier;
	private Material itemRepresentation;
	private String cosmetique;
	private boolean unlock;
	private int tokenPass;
	private int priceHumis;
	private int awardHumis;
	private int awardExp;
	private int awardPixel;
	private int pixel;
	private TypePalier type;

	/**
	 * Constructeur de Palier
	 * 
	 * @param numPalier          le Numero du palier
	 * @param itemRepresentation l'item de representation dans l'inventaire
	 * @param humis              Le cout en Humis du Palier
	 * @param pixel              Le cout en Pixel du Palier
	 * @param tokenPass          Le nombre de {@link Token} pour le debloquer
	 * @param cosmetique         Le cosmetique lie au palier et qui servira de
	 *                           recompense au joueur
	 * @param unlock             Le Palier est debloquer ou non
	 * @param premium            Le Palier est premium ou non
	 */
	public Palier(int numPalier, ItemStack itemRepresentation, int priceHumis, int awardHumis, int awardPixel, int awardExp, int tokenPass, Cosmetique cosmetique, boolean unlock, boolean premium) {
		this.numeroPalier = numPalier;
		this.itemRepresentation = itemRepresentation.getType();
		this.cosmetique = (cosmetique != null) ? cosmetique.getId() : "";
		this.unlock = unlock;
		this.tokenPass = tokenPass;

		if (premium)
			this.type = TypePalier.PREMIUM;
		else
			this.type = TypePalier.FREE;

		this.priceHumis = priceHumis;
		this.awardHumis = awardHumis;
		this.awardExp = awardExp;
		this.awardPixel = awardPixel;
	}

	/**
	 * @return true si il est debloquer, sinon false
	 */
	public boolean isUnlock() {
		return unlock;
	}

	/**
	 * Definir le debloquage
	 * 
	 * @param unlock true si il est debloquer, sinon false
	 */
	public void setUnlock(boolean unlock) {
		this.unlock = unlock;
	}

	/**
	 * @return Le nombre de {@link Token} pour debloquer le Palier
	 */
	public int getTokenPass() {
		return tokenPass;
	}

	/**
	 * Definir le nombre de {@link Token} pour le debloquer
	 * 
	 * @param tokenPass le montant
	 */
	public void setTokenPass(int tokenPass) {
		this.tokenPass = tokenPass;
	}

	/**
	 * @return Le numero du Palier
	 */
	public int getNumeroPalier() {
		return numeroPalier;
	}

	/**
	 * Definir le cout du Palier en Humis
	 * 
	 * @param humis le montant
	 */
	public void setPriceHumis(int humis) {
		this.priceHumis = humis;
	}

	/**
	 * Definir le cout du Palier en Pixel
	 * 
	 * @param pixel le montant
	 */
	public void setPixel(int pixel) {
		this.pixel = pixel;
	}

	/**
	 * @return Le type de Palier (FREE ou PREMIUM)
	 */
	public TypePalier getType() {
		return type;
	}

	/**
	 * Definir le type du Palier
	 * 
	 * @param type le type
	 */
	public void setType(TypePalier type) {
		this.type = type;
	}

	/**
	 * Permet de convertir un palier en un {@link ItemStack} utilisable dans un
	 * {@link Inventory} <br />
	 * Le Palier varie selon le {@link Challenger}
	 * 
	 * @param palier     Le Palier a convertir
	 * @param challenger Le challenger a qui afficher
	 * @return un {@link ItemStack} representant le Palier
	 */
	public static ItemStack PalierToItemStack(Palier palier, Challenger challenger) {
		ItemStack item = palier.getItemRepresentation();
		ChatColor color;

		if (palier.isUnlock()) {
			color = ChatColor.GREEN;
			item.setAmount(1);
		}
		else {
			if(palier.getType() == TypePalier.PREMIUM && !challenger.hasPremium())
				color = ChatColor.RED;
			else
				color = ChatColor.YELLOW;
			
			item.setAmount(palier.getNumeroPalier());
		}

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(color + "Palier " + palier.getNumeroPalier());

		List<String> lores = new ArrayList<>();
		lores.add(color + "Token necessaire: " + palier.getTokenPass());
		lores.add(" ");

		if (palier.getCosmetique() != null) {
			lores.add(color + "Cosmetique: " + palier.getCosmetique().getName());
		}

		meta.setLore(lores);
		item.setItemMeta(meta);

		return item;
	}

	/**
	 * Permet d'envoyer une description, utilite semblable a celle de toString()
	 * 
	 * @param sender a qui envoyer la description
	 * @param palier le palier a decrire
	 */
	public static void descriptionPalier(CommandSender sender, Palier palier) {
		sender.sendMessage(ChatColor.GOLD + "===Description Palier===");

		sender.sendMessage("Numero Palier: " + ChatColor.AQUA + palier.getNumeroPalier());
		sender.sendMessage("Type de palier: " + ChatColor.AQUA + palier.getType());
		sender.sendMessage("Item Representation: " + ChatColor.AQUA + palier.getItemRepresentation().getType());
		sender.sendMessage("Prix Humis : " + ChatColor.AQUA + palier.getPriceHumis());
		sender.sendMessage("Recompense Humis : " + ChatColor.AQUA + palier.getAwardHumis());
		sender.sendMessage("Recompense Exp: " + ChatColor.AQUA + palier.getAwardExp());
		sender.sendMessage("Prix Pixel : " + ChatColor.AQUA + palier.getPixel());
		sender.sendMessage("Nombre de token necessaire : " + ChatColor.AQUA + palier.getTokenPass());
		sender.sendMessage("Cosmetique : " + ChatColor.AQUA
				+ ((palier.getCosmetique() != null) ? palier.getCosmetique().getId() : "null"));

		sender.sendMessage(ChatColor.GOLD + "===Description Palier===");
	}

	/**
	 * @return l'item representant le palier dans l'inventaire
	 */
	public ItemStack getItemRepresentation() {
		return new ItemStack(itemRepresentation);
	}

	/**
	 * Definir l'item representant le palier dans l'inventaire
	 * 
	 * @param itemRepresentation l'item de presentation
	 */
	public void setItemRepresentation(ItemStack itemRepresentation) {
		this.itemRepresentation = itemRepresentation.getType();
	}

	/**
	 * @return le cosmetique lie au palier, null si inexistant
	 */
	public Cosmetique getCosmetique() {
		return ChallengeMain.getInstance().getBankCosmetique().getCosmetique(cosmetique);
	}

	/**
	 * Definir un cosmetique lie au palier
	 * 
	 * @param cosmetique le cosmetique a lier
	 */
	public void setCosmetique(Cosmetique cosmetique) {
		this.cosmetique = cosmetique.getId();
	}

	/**
	 * @return le prix du Palier en Humis
	 */
	public int getPriceHumis() {
		return priceHumis;
	}

	/**
	 * @return le pix du Palier en Pixel
	 */
	public int getPixel() {
		return pixel;
	}

	public int getAwardHumis()
	{
		return awardHumis;
	}

	public void setAwardHumis(int awardHumis)
	{
		this.awardHumis = awardHumis;
	}

	public int getAwardExp()
	{
		return awardExp;
	}

	public void setAwardExp(int awardExp)
	{
		this.awardExp = awardExp;
	}

	public int getAwardPixel() {
		return awardPixel;
	}
	
	public void setAwardPixel(int awardPixel) {
		this.awardPixel = awardPixel;
	}

	@Override
	public String toString()
	{
		return "Palier [numeroPalier=" + numeroPalier + ", itemRepresentation=" + itemRepresentation + ", cosmetique="
				+ cosmetique + ", unlock=" + unlock + ", tokenPass=" + tokenPass + ", priceHumis=" + priceHumis
				+ ", awardHumis=" + awardHumis + ", awardExp=" + awardExp + ", pixel=" + pixel + ", type=" + type + "]";
	}

	/**
	 * Permet de cloner le Palier tout en conservant les meme donnees
	 * 
	 * @return une nouvelle instance du Palier
	 */
	public Palier clonage() {
		Cosmetique c = ChallengeMain.getInstance().getBankCosmetique().getCosmetique(cosmetique);
		boolean premium = (type == TypePalier.FREE) ? false : true;
		Palier palier = new Palier(numeroPalier, new ItemStack(itemRepresentation), priceHumis, awardHumis, awardPixel, awardExp, tokenPass, c, false, premium);
		return palier;
	}

	@Override
	public int compareTo(Palier palier) {
		if(palier != null) {
			if(this.tokenPass < palier.tokenPass)
				return -1;
			else if(this.tokenPass > palier.tokenPass)
				return 1;
		}
		return 0;
	}

	@Override
	public int compare(Palier p1, Palier p2) {
		if(p1 != null && p2 != null) {
			if(p1.tokenPass < p2.tokenPass)
				return -1;
			else if(p1.tokenPass > p2.tokenPass)
				return 1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + awardExp;
		result = prime * result + awardHumis;
		result = prime * result + awardPixel;
		result = prime * result + numeroPalier;
		result = prime * result + pixel;
		result = prime * result + priceHumis;
		result = prime * result + tokenPass;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + (unlock ? 1231 : 1237);
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
		Palier other = (Palier) obj;
		if (awardExp != other.awardExp)
			return false;
		if (awardHumis != other.awardHumis)
			return false;
		if (awardPixel != other.awardPixel)
			return false;
		if (numeroPalier != other.numeroPalier)
			return false;
		if (pixel != other.pixel)
			return false;
		if (priceHumis != other.priceHumis)
			return false;
		if (tokenPass != other.tokenPass)
			return false;
		if (type != other.type)
			return false;
		if (unlock != other.unlock)
			return false;
		return true;
	}

	

}
