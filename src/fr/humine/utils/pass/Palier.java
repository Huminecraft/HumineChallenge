package fr.humine.utils.pass;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Palier implements Serializable {

	private static final long serialVersionUID = 7556577054897105339L;
	private int numeroPalier;
	private Material itemRepresentation;
	private String cosmetique;
	private boolean unlock;
	private int tokenPass;
	private int humis;
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
	public Palier(int numPalier, ItemStack itemRepresentation, int humis, int pixel, int tokenPass,
			Cosmetique cosmetique, boolean unlock, boolean premium) {
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
	public void setHumis(int humis) {
		this.humis = humis;
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

		if ((palier.getType() == TypePalier.PREMIUM && challenger.hasPremium()
				&& challenger.getToken().getAmount() >= palier.getTokenPass())
				|| (palier.getType() == TypePalier.FREE
						&& challenger.getToken().getAmount() >= palier.getTokenPass())) {
			palier.setUnlock(true);
		}

		if (palier.isUnlock())
			color = ChatColor.GREEN;
		else if (palier.getType() == TypePalier.PREMIUM && !challenger.hasPremium())
			color = ChatColor.RED;
		else
			color = ChatColor.YELLOW;

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(color + "Palier " + palier.getNumeroPalier());

		List<String> lores = new ArrayList<>();

		lores.add(color + "Token necessaire: " + palier.getTokenPass());
		lores.add(" ");

		if (palier.getCosmetique() != null) {
			lores.add(color + "Cosmetique: " + palier.getCosmetique().getName());
		}

		if (palier.getType() == TypePalier.PREMIUM && !challenger.hasPremium()) {
			lores.add(" ");
			lores.add(color + "Blocked");
		}

		meta.setLore(lores);
		item.setItemMeta(meta);

		if (palier.isUnlock() == false)
			item.setAmount(palier.getNumeroPalier());
		else
			item.setAmount(1);

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
		sender.sendMessage("Prix Humis : " + ChatColor.AQUA + palier.getHumis());
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
	public int getHumis() {
		return humis;
	}

	/**
	 * @return le pix du Palier en Pixel
	 */
	public int getPixel() {
		return pixel;
	}

	@Override
	public String toString() {
		return "Palier [numeroPalier=" + numeroPalier + ", itemRepresentation=" + itemRepresentation + ", cosmetique="
				+ cosmetique + ", unlock=" + unlock + ", tokenPass=" + tokenPass + ", humis=" + humis + ", pixel="
				+ pixel + ", type=" + type + "]";
	}

	/**
	 * Permet de cloner le Palier tout en conservant les meme donnees
	 * 
	 * @return une nouvelle instance du Palier
	 */
	public Palier clonage() {
		Cosmetique c = ChallengeMain.getInstance().getBankCosmetique().getCosmetique(cosmetique);
		boolean premium = (type == TypePalier.FREE) ? false : true;
		Palier palier = new Palier(numeroPalier, new ItemStack(itemRepresentation), humis, pixel, tokenPass, c, unlock,
				premium);
		return palier;
	}

}
