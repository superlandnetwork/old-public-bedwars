//  ___            _  __      __                  
// | _ )  ___   __| | \ \    / /  __ _   _ _   ___
// | _ \ / -_) / _` |  \ \/\/ /  / _` | | '_| (_-<
// |___/ \___| \__,_|   \_/\_/   \__,_| |_|   /__/
//
// Copyright (C) Filli-IT (Einzelunternehmen) & Ursin Filli - All Rights Reserverd
// Unauthorized copying of the this file, via any medium is strictly prohibited
// Proprietary and confidential
// Written by Ursin Filli <ursin.filli@Filli-IT.ch>

package de.superlandnetwork.bedwars.scedular;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;

import de.superlandnetwork.API.API;
import de.superlandnetwork.bedwars.GameStatus;
import de.superlandnetwork.bedwars.Main;
import de.superlandnetwork.bedwars.utils.MapLocation;
import de.superlandnetwork.bedwars.utils.Methods;

/**
 * 
 * Spigot - BedWars
 * 
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class RestartScedular {

	public int CD;
	public int countdown = 15;

	public void StartScedular() {

		CD = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			@Override
			public void run() {

				if (countdown == 15) {
					reset();
					GameStatus.setStatus(GameStatus.RESTART_LOBBY);
				}

				int countdownb = countdown - 5;

				if (countdown == 15 || countdown == 14 || countdown == 13 || countdown == 12 || countdown == 11
						|| countdown == 10 || countdown == 9 || countdown == 8 || countdown == 7 || countdown == 6) {
					if (countdown != 6)
						API.getInstance().sendMessageToAllPlayers(Main.getInstance().prefix
								+ "§7Der Server startet in §e" + countdownb + " §7Sekunden neu.");
					else
						API.getInstance().sendMessageToAllPlayers(Main.getInstance().prefix
								+ "§7Der Server startet in §e" + countdownb + " §7Sekunde neu.");
				}
				if (countdown == 5) {
					for (Player all : Bukkit.getOnlinePlayers()) {
						Methods.sendHub(all);
					}

					GameStatus.setStatus(GameStatus.RESTART);
				}

				if (countdown == 0) {
					Bukkit.spigot().restart();
					Bukkit.getScheduler().cancelAllTasks();
				}
				countdown--;
			}

		}, 0L, 20L);

	}

	private void reset() {
		Scoreboard b = Bukkit.getScoreboardManager().getMainScoreboard();
		for (Player all : Bukkit.getOnlinePlayers()) {
			for (Player all2 : Bukkit.getOnlinePlayers()) {
				all.showPlayer(all2);
			}
			Methods.teleport(all, MapLocation.LOBBY);
			all.setGameMode(GameMode.ADVENTURE);
			if (all.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
				all.removePotionEffect(PotionEffectType.INVISIBILITY);
			}
			all.getInventory().clear();
			all.getInventory().setArmorContents(null);
			if (b.getTeam("spec") != null) {
				b.getTeam("spec").unregister();
			}
			if (Main.getInstance().NickedPlayers.contains(all.getUniqueId())) {
				// unnick(all);
			}
		}
	}

	@SuppressWarnings("unused")
	private void unnick(Player all) {
		Main.getInstance().NickInstances.get(all.getUniqueId()).setName((CraftPlayer) all, all.getDisplayName());
		Main.getInstance().NickInstances.get(all.getUniqueId()).setSkin((CraftPlayer) all, all.getUniqueId());

		Main.getInstance().NickedPlayers.remove(all.getUniqueId());

		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {

			@Override
			public void run() {
				for (Player all2 : Bukkit.getOnlinePlayers()) {
					all2.hidePlayer(all);
				}
			}
		}, 1L);

		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {

			@Override
			public void run() {
				for (Player all2 : Bukkit.getOnlinePlayers()) {
					all2.showPlayer(all);
				}
			}
		}, 5L);

	}

}
