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
import de.superlandnetwork.bedwars.utils.Methods;

public class LobbyScedular {

	public int CD;
	public int countdown = 60;

	public void StartScedular() {

		CD = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			@Override
			public void run() {

				if (countdown == 60 || countdown == 30 || countdown == 20 || countdown == 10 || countdown == 5
						|| countdown == 4 || countdown == 3 || countdown == 2 || countdown == 1) {
					if (countdown != 1)
						API.getInstance().sendMessageToAllPlayers(
								Main.getInstance().prefix + "§7Das Spiel beginnt in §e" + countdown + " §7Sekunden.");
					else
						API.getInstance().sendMessageToAllPlayers(
								Main.getInstance().prefix + "§7Das Spiel beginnt in §e" + countdown + " §7Sekunde");
				}
				if (countdown == 0) {
					GameStatus.setStatus(GameStatus.INGAME);
					for (Player all : Bukkit.getOnlinePlayers()) {
						all.getInventory().clear();
						all.getInventory().setArmorContents(null);
					}
					Methods.teleportMap();
					Main.getInstance().GameScedular.StartScedular();
					Bukkit.getScheduler().cancelTask(CD);
				}
				countdown--;
			}

		}, 0L, 20L);

	}

}
