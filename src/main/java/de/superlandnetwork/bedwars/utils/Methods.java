package de.superlandnetwork.bedwars.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.superlandnetwork.bedwars.Main;

/**
 * 
 * Spigot - BedWars
 * 
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class Methods {

	public static void SetScorbord(Player p, int value) {
		Scoreboard bord = Bukkit.getScoreboardManager().getMainScoreboard();
		if (bord.getObjective("aaa") == null) {
			bord.registerNewObjective("aaa", "dumy");
		} else {
			bord.getObjective("aaa").unregister();
			bord.registerNewObjective("aaa", "dumy");
		}
		Objective score = bord.getObjective("aaa");
		score.setDisplayName("§eSuperLandNetwork§7.§bde");
		score.setDisplaySlot(DisplaySlot.SIDEBAR);
		// score.getScore("§3Verbleibende
		// Zeit").setScore(Main.getInstance().getTeamManager().Teams.size()+3);
		// if(value == 1)
		// score.getScore("§6"+value + "
		// Minute").setScore(Main.getInstance().getTeamManager().Teams.size()+2);
		// else
		// score.getScore("§6"+value + "
		// Minuten").setScore(Main.getInstance().getTeamManager().Teams.size()+2);
		// score.getScore("
		// ").setScore(Main.getInstance().getTeamManager().Teams.size()+1);
		// for(int i=0; i<Main.getInstance().getTeamManager().Teams.size(); i++ ) {
		// int j = 0;
		// for(Player all : Main.getInstance().PlayerLive) {
		// if(Main.getInstance().getTeamManager().Teams.get(i).Players.contains(all)) {
		// j = j+1;
		// }
		// }
		// score.getScore(Main.getInstance().getTeamManager().Teams.get(i).getName() + "
		// " + j).setScore(i);
		// }
	}

	public static void SetLobbyScorbord(Player p) {
		Scoreboard bord = Bukkit.getScoreboardManager().getMainScoreboard();
		if (bord.getObjective("aab") == null) {
			bord.registerNewObjective("aab", "dumy");
		} else {
			bord.getObjective("aab").unregister();
			bord.registerNewObjective("aab", "dumy");
		}
		int players = 0;
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (Main.getInstance().PlayerLive.contains(all))
				players = players + 1;
		}
		Objective score = bord.getObjective("aab");
		score.setDisplayName("§eSuperLandNetwork§7.§bde");
		score.setDisplaySlot(DisplaySlot.SIDEBAR);
		score.getScore("   ").setScore(5);
		score.getScore("Map: " + Main.getInstance().getMap().getName()).setScore(4);
		score.getScore("  ").setScore(3);
		score.getScore("Spieler: " + players + " / " + Main.getInstance().maxPlayers).setScore(2);
		score.getScore(" ").setScore(1);
	}

	public static void sendHub(Player all) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF("Lobby1");

		all.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
	}

	public static void teleport(MapLocation Mloc) {
		Location Loc = new Location(Bukkit.getWorld(Mloc.getWorld()), Mloc.getX(), Mloc.getY(), Mloc.getZ());
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.teleport(Loc);
		}
	}

	public static void teleport(Player p, MapLocation Mloc) {
		Location Loc = new Location(Bukkit.getWorld(Mloc.getWorld()), Mloc.getX(), Mloc.getY(), Mloc.getZ());
		p.teleport(Loc);
	}

	public static void teleportMap() {
		for (Player p : Main.getInstance().PlayerLive) {
			Team t = Main.getInstance().PlayerTeam.get(p);
			if (!p.teleport(t.getSpawn())) {
				p.sendMessage("Error Code 500 MapTP");
				System.out.println("Error Code 500 MapTP");
			}
		}
	}

	/**
	 * @param p
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static void specMode(Player p) {
		p.setAllowFlight(true);
		p.setGameMode(GameMode.ADVENTURE);
		Scoreboard bord = Bukkit.getScoreboardManager().getMainScoreboard();
		if (bord.getTeam("spec") == null) {
			bord.registerNewTeam("spec");
			bord.getTeam("spec").setCanSeeFriendlyInvisibles(true);
		}
		bord.getTeam("spec").addPlayer(p);
		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
		p.getInventory().setItem(0, newItemPri(Material.COMPASS, 1));
		ItemStack hub = new ItemStack(Material.SLIME_BALL, 1);
		ItemMeta meta2 = hub.getItemMeta();
		meta2.setDisplayName("§oZurück zur §r§eLobby");
		hub.setItemMeta(meta2);
		p.getInventory().setItem(8, hub);
	}

	private static ItemStack newItemPri(Material mat, int ammount) {
		ItemStack i = new ItemStack(mat, ammount);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName("§aTeleporter");
		i.setItemMeta(meta);
		return i;
	}

	public static void spawnVillager(Location Loc) {
		Villager villager = (Villager) Loc.getWorld().spawnEntity(Loc, EntityType.VILLAGER);
		villager.setCustomName("§6Shop");
		villager.setCustomNameVisible(true);
		PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, Integer.MAX_VALUE);
		villager.addPotionEffect(effect);
	}

}
