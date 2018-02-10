package de.superlandnetwork.bedwars.utils;

/**
 * 
 * Spigot - BedWars
 * 
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public enum MapLocation {

	LOBBY("BW_Lobby", 12D, 4D, 130D), SPEC_SPAWN("BW_Lobby", 12D, 4D, 130D);

	String world;
	double x;
	double y;
	double z;

	private MapLocation(String world, double x, double y, double z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String getWorld() {
		return world;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

}
