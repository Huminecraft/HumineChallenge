package com.humine.main;

import java.io.IOException;

import org.bukkit.entity.EntityType;

public class Debug
{

	public static void main(String[] args) throws IOException
	{
		for(EntityType entity : EntityType.values()) {
			System.out.println(entity.toString().toLowerCase());
		}
	}

}
