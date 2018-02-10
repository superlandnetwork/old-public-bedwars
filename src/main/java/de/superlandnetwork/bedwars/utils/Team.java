package de.superlandnetwork.bedwars.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * 
 * Spigot - BedWars
 * 
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class Team {

	String name;
	boolean Live;
	ColorEnum color;
	Location Spawn;
	Inventory Enderinv;

	public Team(String name, boolean Live, ColorEnum color, Location Spawn) {
		this.name = name;
		this.Live = Live;
		this.color = color;
		this.Spawn = Spawn;
		Enderinv = Bukkit.createInventory(null, InventoryType.ENDER_CHEST, "§6Team EnderChest");
	}

	/**
	 * @return the enderinv
	 */
	public Inventory getEnderinv() {
		return Enderinv;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the color
	 */
	public ColorEnum getColor() {
		return color;
	}

	/**
	 * @return the live
	 */
	public boolean isLive() {
		return Live;
	}

	/**
	 * @param live
	 *            the live to set
	 */
	public void setLive(boolean live) {
		Live = live;
	}

	/**
	 * @return the spawn
	 */
	public Location getSpawn() {
		return Spawn;
	}

}
