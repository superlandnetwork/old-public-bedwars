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
import org.bukkit.entity.Player;

import de.superlandnetwork.API.API;
import de.superlandnetwork.bedwars.GameStatus;
import de.superlandnetwork.bedwars.Main;
import de.superlandnetwork.bedwars.utils.MapLocation;
import de.superlandnetwork.bedwars.utils.Methods;

public class GameScedular {

	public int CD;
	public int countdown = 45;

	public void StartScedular() {

		CD = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			@Override
			public void run() {

				for (Player all : Bukkit.getOnlinePlayers()) {
					Methods.SetScorbord(all, countdown);
				}

				if (countdown == 45 || countdown == 40 || countdown == 35 || countdown == 30 || countdown == 25
						|| countdown == 20 || countdown == 15 || countdown == 10 || countdown == 5 || countdown == 4
						|| countdown == 3 || countdown == 2 || countdown == 1) {
					if (countdown != 1)
						API.getInstance().sendMessageToAllPlayers(
								Main.getInstance().prefix + "§7Das Spiel Endet in §6" + countdown + " §7Minuten.");
					else
						API.getInstance().sendMessageToAllPlayers(
								Main.getInstance().prefix + "§7Das Spiel Endet in §6" + countdown + " §7Minute.");
				}
				if (countdown == 0) {
					Methods.teleport(MapLocation.LOBBY);
					GameStatus.setStatus(GameStatus.RESTART_LOBBY);
					Main.getInstance().RestartScedular.StartScedular();
					Bukkit.getScheduler().cancelTask(CD);
				}
				countdown--;
			}

		}, 0L, 1200L);

	}

}
