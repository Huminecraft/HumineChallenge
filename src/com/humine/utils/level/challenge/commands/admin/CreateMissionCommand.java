package com.humine.utils.level.challenge.commands.admin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.humine.main.ChallengeMain;
import com.humine.utils.level.challenge.missions.Mission;
import com.humine.utils.level.challenge.missions.MissionDeplacement;
import com.humine.utils.level.challenge.missions.MissionDiscover;
import com.humine.utils.level.challenge.missions.MissionType;

public class CreateMissionCommand implements CommandExecutor
{

	// /createmission <name> <type> ...

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			if (args.length >= 2)
			{
				Mission mission = null;

				if (MissionType.getType(args[1]) == MissionType.ADOPTION)
					mission = createMission((Player) sender, args, MissionType.ADOPTION, true, false);
				else if (MissionType.getType(args[1]) == MissionType.CRAFT)
					mission = createMission((Player) sender, args, MissionType.CRAFT, false, true);
				else if (MissionType.getType(args[1]) == MissionType.EAT)
					mission = createMission((Player) sender, args, MissionType.EAT, false, true);
				else if (MissionType.getType(args[1]) == MissionType.KILL)
					mission = createMission((Player) sender, args, MissionType.KILL, true, false);
				else if (MissionType.getType(args[1]) == MissionType.POSE)
					mission = createMission((Player) sender, args, MissionType.POSE, false, true);
				else if (MissionType.getType(args[1]) == MissionType.RECUPERATION)
					mission = createMission((Player) sender, args, MissionType.RECUPERATION, false, true);
				else if (MissionType.getType(args[1]) == MissionType.DISCOVER)
					mission = discoverMission(args);
				else if (MissionType.getType(args[1]) == MissionType.DEPLACEMENT)
					mission = deplacementMission(args);

				if (mission != null)
				{
					ChallengeMain.getInstance().getMissionWaiting().add(mission);
					ChallengeMain.sendMessage(sender, ChatColor.GREEN + "Mission créée !");
					return true;
				}
				else
					ChallengeMain.sendMessage(sender, ChatColor.RED + "Mission non créée !");
			}
		}

		return false;
	}

	EntityType getEntity(String name)
	{
		for(EntityType entity : EntityType.values()) {
			if (entity.toString().equalsIgnoreCase(name))
				return entity;
		}

		return null;

	}

	ItemStack getItem(String name)
	{
		for (Material material : Material.values())
		{
			if (material.toString().equalsIgnoreCase(name))
				return new ItemStack(material);
		}

		return null;
	}

	Biome getBiome(String mot)
	{
		for (Biome bio : Biome.values())
		{
			if (bio.toString().equalsIgnoreCase(mot))
				return bio;
		}

		return null;
	}

	boolean isNumber(String n)
	{
		boolean valid = true;
		int i = 0;

		while (i < n.length() && valid)
		{
			if (n.charAt(i) < '0' || n.charAt(i) > '9')
				valid = false;

			i++;
		}

		return valid;
	}

	Mission createMission(Player player, String[] args, MissionType type, boolean boolEntity, boolean boolItem)
	{
		Mission mission = null;
		EntityType entity = null;
		ItemStack item = null;

		if (args.length >= 3)
		{
			if (boolEntity)
				entity = getEntity(args[2]);

			if (boolItem)
				item = (!isNumber(args[2])) ? getItem(args[2]) : null;

			if (boolItem == true && item != null)
			{
				mission = configurationMission(args, type);
				mission.setItem(item);
			}
			else if(boolItem == true && item == null)
				player.sendMessage("Item introuvable");

			if (boolEntity == true && entity != null)
			{
				mission = configurationMission(args, type);
				mission.setEntity(entity);
			}
			else if(boolEntity == true && entity == null)
				player.sendMessage("Entity introuvable");
		}

		return mission;

	}

	private Mission configurationMission(String[] args, MissionType type)
	{
		Mission mission = new Mission();
		mission.setMissionName(args[0]);

		if (args.length >= 4)
			mission.setNumber((isNumber(args[3])) ? Integer.parseInt(args[3]) : 1);

		if (args.length >= 5)
			mission.setMissionPremium((args[4].equalsIgnoreCase("true")) ? true : false);

		if (args.length >= 6)
		{
			for (int i = 5; i < args.length; i++)
			{
				mission.setDescription(mission.getDescription() + args[i] + " ");
			}
		}

		mission.setMissionType(type);

		return mission;
	}

	Mission deplacementMission(String[] args)
	{
		MissionDeplacement deplacement = null;
		int number = 1;
		boolean premium = false;
		String description = "";

		if (args.length >= 3)
			number = (isNumber(args[2])) ? Integer.parseInt(args[2]) : 1;

		if (args.length >= 4)
			premium = (args[3].equalsIgnoreCase("true")) ? true : false;

		if (args.length >= 5)
		{
			for (int i = 4; i < args.length; i++)
			{
				description += args[i] + " ";
			}
		}

		deplacement = new MissionDeplacement(args[0], description, number, premium);

		return deplacement;
	}

	Mission discoverMission(String[] args)
	{
		MissionDiscover discover = null;
		boolean premium = false;
		String description = "";
		Biome biome = null;

		if (args.length >= 3)
		{

			biome = getBiome(args[2]);

			if (biome != null)
			{
				if (args.length >= 4)
					premium = (args[3].equalsIgnoreCase("true")) ? true : false;

				if (args.length >= 5)
				{
					for (int i = 4; i < args.length; i++)
					{
						description += args[i] + " ";
					}
				}

				discover = new MissionDiscover(args[0], description, biome, premium);
			}

		}

		return discover;
	}
}
