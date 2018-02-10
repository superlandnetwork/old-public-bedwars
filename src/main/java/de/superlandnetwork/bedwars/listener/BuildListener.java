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

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import de.superlandnetwork.bedwars.GameStatus;

public class BuildListener implements Listener {

	@EventHandler
	public void onBuild(BlockPlaceEvent e) {
		if (!GameStatus.isStatus(GameStatus.INGAME)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDestroy(BlockBreakEvent e) {
		if (!GameStatus.isStatus(GameStatus.INGAME)) {
			e.setCancelled(true);
		}

		if (e.getBlock().getType() != Material.RED_SANDSTONE && e.getBlock().getType() != Material.ENDER_STONE
				&& e.getBlock().getType() != Material.IRON_BLOCK && e.getBlock().getType() != Material.GLASS
				&& e.getBlock().getType() != Material.GLOWSTONE) {
			e.setCancelled(true);
		}
	}

}