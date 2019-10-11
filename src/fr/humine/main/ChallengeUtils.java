package fr.humine.main;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import humine.utils.Prestige;

public abstract class ChallengeUtils
{

	public static Material getItem(String str) {
		for(Material m : Material.values()) {
			if(m.name().equalsIgnoreCase(str))
				return m;
		}
		return null;
	}
	
	public static Prestige getPrestige(String str) {
		for(Prestige p : Prestige.values()) {
			if(p.name().equalsIgnoreCase(str))
				return p;
		}
		return null;
	}

	public static boolean isNumber(String str) {
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) < '0' || str.charAt(i) > '9')
				return false;
		}
		return true;
	}
	
	public static Particle getParticle(String str) {
		for(Particle p : Particle.values()) {
			if(p.name().equalsIgnoreCase(str))
				return p;
		}
		return null;
	}
	
	public static Biome getBiome(String str) {
		for(Biome b : Biome.values()) {
			if(b.name().equalsIgnoreCase(str))
				return b;
		}
		return null;
	}
	
	public static EntityType getEntity(String str) {
		for(EntityType e : EntityType.values()) {
			if(e.name().equalsIgnoreCase(str))
				return e;
		}
		return null;
	}
}
