//  ___            _  __      __                  
// | _ )  ___   __| | \ \    / /  __ _   _ _   ___
// | _ \ / -_) / _` |  \ \/\/ /  / _` | | '_| (_-<
// |___/ \___| \__,_|   \_/\_/   \__,_| |_|   /__/
//
// Copyright (C) Filli-IT (Einzelunternehmen) & Ursin Filli - All Rights Reserverd
// Unauthorized copying of the this file, via any medium is strictly prohibited
// Proprietary and confidential
// Written by Ursin Filli <ursin.filli@Filli-IT.ch>

package de.superlandnetwork.bedwars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import com.google.common.reflect.ClassPath;

import de.superlandnetwork.API.Utils.Nick;
import de.superlandnetwork.API.WorldAPI.WorldAPI;
import de.superlandnetwork.bedwars.scedular.GameScedular;
import de.superlandnetwork.bedwars.scedular.LobbyScedular;
import de.superlandnetwork.bedwars.scedular.RestartScedular;
import de.superlandnetwork.bedwars.utils.ColorEnum;
import de.superlandnetwork.bedwars.utils.Map;
import de.superlandnetwork.bedwars.utils.MapEnum;
import de.superlandnetwork.bedwars.utils.Team;

public class Main extends JavaPlugin {

	/**
	 * -797 32 103 - Iron -797 32 44 - Iron -797 27 76 - Gold -797 27 72 - Gold -799
	 * 27 74 - Gold -795 27 74 - Gold
	 */

	public List<UUID> NickedPlayers = new ArrayList<>();
	public HashMap<UUID, Nick> NickInstances = new HashMap<>();

	public static HashMap<Player, Integer> Kills = new HashMap<>();
	public static HashMap<Player, Integer> Deaths = new HashMap<>();

	public ArrayList<Player> PlayerLive = new ArrayList<>();
	public ArrayList<Player> CanChat = new ArrayList<>();
	public HashMap<Player, Team> PlayerTeam = new HashMap<>();
	public HashMap<Location, Team> BedTeam = new HashMap<>();

	public LobbyScedular LobbyScedular = new LobbyScedular();
	public GameScedular GameScedular = new GameScedular();
	public RestartScedular RestartScedular = new RestartScedular();
	public RestartScedular SpawnScedular = new RestartScedular();

	public String prefix = "§7[§aBedWars§7]§f ";

	public static Main instance;

	public int minPlayers;
	public int maxPlayers;

	public Map map;

	@Override
	public void onEnable() {
		instance = this;
		GameStatus.setStatus(GameStatus.LOBBY);
		registerListener();
		addMaps(MapEnum.BW_MelonWerk_2x4);
		WorldAPI wAPI = new WorldAPI("BW_Lobby");
		if (Bukkit.getWorld("BW_Lobby") != null) {
			Bukkit.unloadWorld("BW_Lobby", false);
		}
		wAPI.createCleanWorld();
		Bukkit.getWorld("BW_Lobby").setAutoSave(false);
		Bukkit.getWorld("BW_Lobby").setThundering(false);
		Bukkit.getWorld("BW_Lobby").setStorm(false);
		Bukkit.getWorld("BW_Lobby").setTime(0L);
		Bukkit.getWorld("BW_Lobby").setGameRuleValue("doDaylightCycle", "false");
		Bukkit.getWorld("BW_Lobby").setGameRuleValue("announceAdvancements", "false");
		Bukkit.getWorld("BW_Lobby").setGameRuleValue("doFireTick", "false");//
		Bukkit.getWorld("BW_Lobby").setGameRuleValue("disableElytraMovementCheck", "true");
		Bukkit.getWorld("BW_Lobby").setGameRuleValue("doMobSpawning", "false");
	}

	private void addMaps(MapEnum LoadMap) {
		ArrayList<Team> list = new ArrayList<>();

		if (MapEnum.BW_MelonWerk_2x4 == LoadMap) {
			minPlayers = 2;
			maxPlayers = 8;

			Location LocYellow = new Location(Bukkit.getWorld("BW_MelonWerk_2x4"), -797D, 32D, 14D);
			LocYellow.setPitch(0F);
			LocYellow.setYaw(0F);
			Team TeamYellow = new Team("Gelb", true, ColorEnum.YELLOW, LocYellow);
			list.add(TeamYellow);
			Location BedLocYellow = new Location(Bukkit.getWorld("BW_MelonWerk_2x4"), 0D, 0D, 0D);
			BedTeam.put(BedLocYellow, TeamYellow);

			Location LocBlue = new Location(Bukkit.getWorld("BW_MelonWerk_2x4"), -797D, 32D, 133D);
			LocBlue.setPitch(0F);
			LocBlue.setYaw(0F);
			Team TeamBlue = new Team("Blau", true, ColorEnum.BLUE, LocBlue);
			list.add(TeamBlue);
			Location BedLocBlue = new Location(Bukkit.getWorld("BW_MelonWerk_2x4"), 0D, 0D, 0D);
			BedTeam.put(BedLocBlue, TeamBlue);

			map = new Map("BW_MelonWerk_2x4", list);
			WorldAPI wAPI = new WorldAPI("BW_MelonWerk_2x4");
			if (Bukkit.getWorld("BW_MelonWerk_2x4") != null) {
				Bukkit.unloadWorld("BW_MelonWerk_2x4", false);
			}
			wAPI.createCleanWorld();
			Bukkit.getWorld("BW_MelonWerk_2x4").setAutoSave(false);
			Bukkit.getWorld("BW_MelonWerk_2x4").setThundering(false);
		}
	}

	@Override
	public void onDisable() {
		Scoreboard bord = Bukkit.getScoreboardManager().getMainScoreboard();
		for (org.bukkit.scoreboard.Team team : bord.getTeams()) {
			team.unregister();
		}
		if (bord.getObjective("aab") != null) {
			bord.getObjective("aab").unregister();
		}
	}

	public static Main getInstance() {
		return instance;
	}

	private void registerListener() {
		PluginManager pluginManager = getServer().getPluginManager();
		try {
			for (ClassPath.ClassInfo classInfo : ClassPath.from(getClassLoader())
					.getTopLevelClasses("net.CrazyCraftLand.BedWars.Listener")) {
				@SuppressWarnings("rawtypes")
				Class clazz = Class.forName(classInfo.getName());
				if (Listener.class.isAssignableFrom(clazz)) {
					pluginManager.registerEvents((Listener) clazz.newInstance(), this);
				}
			}
		} catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

}
