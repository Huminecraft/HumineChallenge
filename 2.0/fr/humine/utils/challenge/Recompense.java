package fr.humine.utils.challenge;

import java.io.Serializable;

import org.bukkit.entity.Player;

import fr.humine.ChallengeMain;
import fr.humine.utils.shop.ChallengeAchievement;
import humine.utils.cosmetiques.Cosmetique;

public class Recompense implements ChallengeAchievement, Serializable{

	private static final long serialVersionUID = 1423553186939777658L;
	private int humis;
	private int pixel;
	private int exp;
	private String cosmetiqueID;
	
	public Recompense(int humis, int pixel, int exp, String cosmetiqueID) {
		this.humis = humis;
		this.pixel = pixel;
		this.exp = exp;
		this.cosmetiqueID = cosmetiqueID;
	}
	
	public Recompense(int humis, int pixel, int exp) {
		this(humis, pixel, exp, "");
	}
	
	public Recompense()
	{
		this(0, 0, 0, "");
	}
	
	@Override
	public void giveRecompense(Player player) {
		//TODO changer le parametre
	}

	@Override
	public Cosmetique getCosmetique() {
		return ChallengeMain.getInstance().getBankCosmetique().getCosmetique(this.cosmetiqueID);
	}

	@Override
	public int getHumis() {
		return this.humis;
	}

	@Override
	public int getPixel() {
		return this.pixel;
	}

	@Override
	public int getExperience() {
		return this.exp;
	}

	public void setHumis(int humis) {
		this.humis = humis;
	}

	public void setPixel(int pixel) {
		this.pixel = pixel;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setCosmetiqueID(String cosmetiqueID) {
		this.cosmetiqueID = cosmetiqueID;
	}
	
	public String getCosmetiqueID()
	{
		return cosmetiqueID;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cosmetiqueID == null) ? 0 : cosmetiqueID.hashCode());
		result = prime * result + exp;
		result = prime * result + humis;
		result = prime * result + pixel;
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
		Recompense other = (Recompense) obj;
		if (cosmetiqueID == null)
		{
			if (other.cosmetiqueID != null)
				return false;
		}
		else if (!cosmetiqueID.equals(other.cosmetiqueID))
			return false;
		if (exp != other.exp)
			return false;
		if (humis != other.humis)
			return false;
		if (pixel != other.pixel)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Recompense [humis=" + humis + ", pixel=" + pixel + ", exp=" + exp + ", cosmetiqueID=" + cosmetiqueID
				+ "]";
	}
	
	

}
