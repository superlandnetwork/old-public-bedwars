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

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.superlandnetwork.API.PlayerAPI.PermEnum;
import de.superlandnetwork.API.PlayerAPI.PlayerAPI;
import de.superlandnetwork.bedwars.GameStatus;
import de.superlandnetwork.bedwars.Main;

public class ChatListener implements Listener {

	@EventHandler
	public void onChatDeath(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		if (GameStatus.isStatus(GameStatus.RESTART_LOBBY) || GameStatus.isStatus(GameStatus.RESTART)) {
			return;
		}

		if (Main.getInstance().PlayerLive.contains(p))
			return;
		else {
			if (Main.getInstance().CanChat.contains(p))
				return;
			else {
				e.setCancelled(true);
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (Main.getInstance().PlayerLive.contains(all))
						continue;
					all.sendMessage("§8*Death* " + p.getName() + " : §7" + e.getMessage());
				}
			}
		}
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		UUID UUID = p.getUniqueId();
		e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
		PlayerAPI api = new PlayerAPI(UUID);
		if (Main.getInstance().NickedPlayers.contains(UUID))
			e.setFormat(PermEnum.SPIELER.getPrefix() + p.getName() + "§f" + " §7: §f" + e.getMessage());
		else
			e.setFormat(api.getChatPrefix() + " §7: §f" + e.getMessage());
	}
}
