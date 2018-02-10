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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import de.superlandnetwork.API.PlayerAPI.PlayerAPI;
import de.superlandnetwork.API.StatsAPI.StatsAPI;
import de.superlandnetwork.API.StatsAPI.StatsEnum;
import de.superlandnetwork.API.Utils.Nick;
import de.superlandnetwork.API.Utils.Tablist;
import de.superlandnetwork.bedwars.GameStatus;
import de.superlandnetwork.bedwars.Main;
import de.superlandnetwork.bedwars.utils.MapLocation;
import de.superlandnetwork.bedwars.utils.Methods;

public class JoinListener implements Listener {

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		if (GameStatus.isStatus(GameStatus.RESTART)) {
			e.disallow(Result.KICK_WHITELIST, "§cDer Server Restartet zurzeit noch!");
		}
		if (GameStatus.isStatus(GameStatus.RESTART_LOBBY)) {
			e.disallow(Result.KICK_WHITELIST, "§cDer Server ist im Restart Modus!");
		}
		if (GameStatus.isStatus(GameStatus.LOBBY)) {
			if (e.getResult() == Result.KICK_FULL) {
				if (e.getPlayer().hasPermission("ccl.bedwars.join")) {
					if (kickRandomPlayer()) {
						e.allow();
					} else {
						e.setKickMessage("Server Full!");
					}
				}
			}
			PlayerAPI api = new PlayerAPI(e.getPlayer().getUniqueId());
			if (api.AutoNick()) {
				Nick nick = new Nick(e.getPlayer());
				if (nick.nick()) {
					// OK
					Main.getInstance().NickedPlayers.add(e.getPlayer().getUniqueId());
					Main.getInstance().NickInstances.put(e.getPlayer().getUniqueId(), nick);
				} else {
					// Nick Fail 1/3
					if (nick.nick()) {
						// OK
						Main.getInstance().NickedPlayers.add(e.getPlayer().getUniqueId());
						Main.getInstance().NickInstances.put(e.getPlayer().getUniqueId(), nick);
					} else {
						// Nick Fail 2/3
						if (nick.nick()) {
							// OK
							Main.getInstance().NickedPlayers.add(e.getPlayer().getUniqueId());
							Main.getInstance().NickInstances.put(e.getPlayer().getUniqueId(), nick);
						} else {
							// Nick Fail 3/3
						}
					}
				}
			}
		}
	}

	public boolean kickRandomPlayer() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (new PlayerAPI(all.getUniqueId()).IsPlayerInGroup(1)) {
				Methods.sendHub(all);
				return true;
			} else
				continue;
		}
		return false;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		for (PotionEffect e1 : p.getActivePotionEffects()) {
			p.removePotionEffect(e1.getType());
		}
		if (GameStatus.isStatus(GameStatus.LOBBY)) {
			p.setGameMode(GameMode.SURVIVAL);
			Main.getInstance().PlayerLive.add(p);
			if (Main.getInstance().PlayerLive.size() == Main.getInstance().minPlayers) {
				Main.getInstance().LobbyScedular.StartScedular();
			}
			if (Main.getInstance().NickedPlayers.contains(p.getUniqueId())) {
				p.sendMessage("§7[§5NICK§7] §4Du spielst als §e"
						+ Main.getInstance().NickInstances.get(p.getUniqueId()).getNick());
				p.sendMessage(" ");
			}
			e.setJoinMessage("§a>> §7" + p.getName() + " hat den Server betreten");
			StatsAPI sapi = new StatsAPI(p.getUniqueId());
			Main.Deaths.put(p, sapi.getStates(StatsEnum.BedWars_DEATHS));
			Main.Kills.put(p, sapi.getStates(StatsEnum.BedWars_KILLS));
			Methods.teleport(p, MapLocation.LOBBY);
			ItemStack hub = new ItemStack(Material.SLIME_BALL, 1);
			ItemMeta meta2 = hub.getItemMeta();
			meta2.setDisplayName("§oZurück zur §r§eLobby");
			hub.setItemMeta(meta2);
			p.getInventory().setItem(8, hub);
		} else if (GameStatus.isStatus(GameStatus.INGAME)) {
			e.setJoinMessage(null);
			p.setGameMode(GameMode.ADVENTURE);
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (Main.getInstance().PlayerLive.contains(all)) {
					all.hidePlayer(p);
				}
			}
			Methods.teleport(p, MapLocation.SPEC_SPAWN);
			Methods.specMode(p);
		} else {
			e.setJoinMessage(null);
			p.setGameMode(GameMode.ADVENTURE);
			if (!GameStatus.isStatus(GameStatus.RESTART_LOBBY)) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (Main.getInstance().PlayerLive.contains(all)) {
						all.hidePlayer(p);
					}
				}
			}
			Methods.teleport(p, MapLocation.LOBBY);
		}
		p.setFoodLevel(20);
		p.setHealth(20.0D);
		new Tablist(p, "§eSuperLandNetwork.de Netzwerk §7- §aBedWars",
				"§7Teamspeak: §eTs.SuperLandNetwork.de \n §7Shop: §eShop.SuperLandNetwork.de");
		if (GameStatus.isStatus(GameStatus.LOBBY))
			Methods.SetLobbyScorbord(p);
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (GameStatus.isStatus(GameStatus.LOBBY)) {
			e.setQuitMessage("§c<< §7" + p.getName() + " hat das Spiel verlassen");
			Main.getInstance().PlayerLive.remove(p);
			int m = Main.getInstance().minPlayers - 1;
			if (Main.getInstance().PlayerLive.size() == m) {
				Bukkit.getScheduler().cancelTask(Main.getInstance().LobbyScedular.CD);
				Main.getInstance().LobbyScedular.countdown = 60;
			}
		} else {
			if (Main.getInstance().PlayerLive.contains(p)) {
				e.setQuitMessage("§c<< §7" + p.getName() + " hat das Spiel verlassen");
				Main.getInstance().PlayerLive.remove(p);
				if (GameStatus.isStatus(GameStatus.INGAME)) {
					for (ItemStack item : p.getInventory().getContents()) {
						p.getWorld().dropItem(p.getLocation(), item);
					}
					for (ItemStack item : p.getInventory().getArmorContents()) {
						p.getWorld().dropItem(p.getLocation(), item);
					}
				}
			} else
				e.setQuitMessage(null);
			if (Main.getInstance().PlayerLive.size() == 1) {
				Bukkit.getScheduler().cancelAllTasks();
				Main.getInstance().RestartScedular.StartScedular();
			}

			if (Main.getInstance().PlayerLive.size() == 0) {
				Bukkit.getScheduler().cancelAllTasks();
				Bukkit.spigot().restart();
			}

		}

		if (Main.getInstance().NickedPlayers.contains(p.getUniqueId())) {
			Main.getInstance().NickedPlayers.remove(p.getUniqueId());
			Main.getInstance().NickInstances.remove(p.getUniqueId());
		}

		if (Main.Deaths.containsKey(p))
			Main.Deaths.remove(p);
		if (Main.Kills.containsKey(p))
			Main.Kills.remove(p);
	}

}
