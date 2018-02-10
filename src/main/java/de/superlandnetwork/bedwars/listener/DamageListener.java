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

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import de.superlandnetwork.bedwars.GameStatus;

public class DamageListener implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!GameStatus.isStatus(GameStatus.INGAME)) {
			e.setCancelled(true);
		}
		if (e.getEntityType() == EntityType.VILLAGER) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDamageByBlock(EntityDamageByBlockEvent e) {
		if (!GameStatus.isStatus(GameStatus.INGAME)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDamageByEntity(EntityDamageByEntityEvent e) {
		if (!GameStatus.isStatus(GameStatus.INGAME)) {
			e.setCancelled(true);
		}
		if (GameStatus.isStatus(GameStatus.LOBBY)) {
			e.setCancelled(true);
		}
	}

}
