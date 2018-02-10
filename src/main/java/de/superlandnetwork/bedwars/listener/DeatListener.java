//  ___            _  __      __                  
// | _ )  ___   __| | \ \    / /  __ _   _ _   ___
// | _ \ / -_) / _` |  \ \/\/ /  / _` | | '_| (_-<
// |___/ \___| \__,_|   \_/\_/   \__,_| |_|   /__/
//
// Copyright (C) Filli-IT (Einzelunternehmen) & Ursin Filli - All Rights Reserverd
// Unauthorized copying of the this file, via any medium is strictly prohibited
// Proprietary and confidential
// Written by Ursin Filli <ursin.filli@Filli-IT.ch>

package de.superlandnetwork.bedwars.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.superlandnetwork.API.API;
import de.superlandnetwork.API.PlayerAPI.PlayerAPI;
import de.superlandnetwork.API.StatsAPI.StatsAPI;
import de.superlandnetwork.API.StatsAPI.StatsEnum;
import de.superlandnetwork.bedwars.GameStatus;
import de.superlandnetwork.bedwars.Main;
import de.superlandnetwork.bedwars.utils.MapLocation;
import de.superlandnetwork.bedwars.utils.Methods;

public class DeatListener implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		p.spigot().respawn();
		if (Main.getInstance().PlayerLive.contains(e.getEntity())) {
			e.setDeathMessage(null);
			if (e.getEntity().getKiller() == null)
				API.getInstance().sendMessageToAllPlayers(
						Main.getInstance().prefix + "§6" + e.getEntity().getName() + "§c ist Gestorben!");
			else
				API.getInstance().sendMessageToAllPlayers(Main.getInstance().prefix + "§6" + e.getEntity().getName()
						+ "§c wurde von §6" + e.getEntity().getKiller().getName() + " §cGetötet!");
			Main.getInstance().PlayerLive.remove(e.getEntity());
			if (Main.getInstance().PlayerLive.size() == 1) {
				Bukkit.getScheduler().cancelAllTasks();
				Main.getInstance().RestartScedular.StartScedular();
			}
			if (Main.getInstance().PlayerLive.size() == 0) {
				Bukkit.getScheduler().cancelAllTasks();
				Bukkit.spigot().restart();
			}
			e.getEntity().setGameMode(GameMode.ADVENTURE);
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (Main.getInstance().PlayerLive.contains(all)) {
					all.hidePlayer(p);
				} else {
					p.showPlayer(all);
				}
			}
			Methods.specMode(p);
			if (e.getEntity().getKiller() != null) {
				PlayerAPI api = new PlayerAPI(e.getEntity().getKiller().getUniqueId());
				api.addCoins(20);
				StatsAPI sapi2 = new StatsAPI(e.getEntity().getKiller().getUniqueId());
				sapi2.setStates(StatsEnum.KFFA_KILLS, Main.Kills.get(e.getEntity().getKiller()) + 1);
				Main.Kills.replace(e.getEntity().getKiller(), Main.Kills.get(e.getEntity().getKiller()) + 1);
			}
			StatsAPI sapi = new StatsAPI(e.getEntity().getUniqueId());
			sapi.setStates(StatsEnum.KFFA_DEATHS, Main.Deaths.get(e.getEntity()) + 1);
			Main.Deaths.replace(e.getEntity(), Main.Deaths.get(e.getEntity()) + 1);
		} else {
			e.setDeathMessage(null);
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		if (GameStatus.isStatus(GameStatus.INGAME)) {
			Methods.teleport(e.getPlayer(), MapLocation.SPEC_SPAWN);
		} else {
			Methods.teleport(e.getPlayer(), MapLocation.LOBBY);
		}
	}
}
