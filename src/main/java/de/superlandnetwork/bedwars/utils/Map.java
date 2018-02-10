package de.superlandnetwork.bedwars.utils;

import java.util.ArrayList;

/**
 * 
 * Spigot - BedWars
 * 
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class Map {

	public String name;
	public int Teams;
	public ArrayList<Team> TeamListe;

	public Map(String name, ArrayList<Team> list) {
		this.name = name;
		this.Teams = list.size();
		this.TeamListe = list;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the teams
	 */
	public int getTeams() {
		return Teams;
	}

	/**
	 * @return the teamListe
	 */
	public ArrayList<Team> getTeamListe() {
		return TeamListe;
	}

}
