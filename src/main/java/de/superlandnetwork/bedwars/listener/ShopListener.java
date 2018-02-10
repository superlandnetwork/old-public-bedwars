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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.superlandnetwork.bedwars.Main;
import de.superlandnetwork.bedwars.utils.ColorEnum;

public class ShopListener implements Listener {

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if (e.getRightClicked().getType() == EntityType.VILLAGER) {
			e.setCancelled(true);
			Player p = e.getPlayer();
			Inventory inv = getShopInv();
			p.openInventory(inv);
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory().getHolder() != null)
			return;
		if (e.getCurrentItem().getType() == Material.AIR)
			return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bBlöcke")) {
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().openInventory(getBlockInv());
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bRüstung")) {
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().openInventory(getRustungInv((Player) e.getWhoClicked()));
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bSpitzhacken")) {
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().openInventory(getPickaxInv());
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bSchwerter")) {
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().openInventory(getSwordInv());
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bBögen")) {
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().openInventory(getBowInv());
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bNahrung")) {
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().openInventory(getFoodInv());
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bKisten")) {
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().openInventory(getChestInv());
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bTränke")) {
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().openInventory(getPotitonInv());
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bSpezial")) {
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().openInventory(getSpecialInv());
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Sandstein")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 1)) {
				GiveItem(e.getWhoClicked().getInventory(), 1, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Endstein")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 6)) {
				GiveItem(e.getWhoClicked().getInventory(), 2, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Eisenblock")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 3)) {
				GiveItem(e.getWhoClicked().getInventory(), 3, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Glas")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 3)) {
				GiveItem(e.getWhoClicked().getInventory(), 4, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Glowstone")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 14)) {
				GiveItem(e.getWhoClicked().getInventory(), 5, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Lederhelm")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 1)) {
				GiveItem(e.getWhoClicked().getInventory(), 6, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Lederhose")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 1)) {
				GiveItem(e.getWhoClicked().getInventory(), 7, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Lederschuhe")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 1)) {
				GiveItem(e.getWhoClicked().getInventory(), 8, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Brustplatte")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 1)) {
				GiveItem(e.getWhoClicked().getInventory(), 9, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Brustplatte I")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 3)) {
				GiveItem(e.getWhoClicked().getInventory(), 10, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Brustplatte II")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 7)) {
				GiveItem(e.getWhoClicked().getInventory(), 11, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Brustplatte III")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 11)) {
				GiveItem(e.getWhoClicked().getInventory(), 12, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Sprengweste")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 6)) {
				GiveItem(e.getWhoClicked().getInventory(), 13, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpitzhacke I")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 7)) {
				GiveItem(e.getWhoClicked().getInventory(), 14, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpitzhacke II")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 2)) {
				GiveItem(e.getWhoClicked().getInventory(), 15, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpitzhacke III")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 1)) {
				GiveItem(e.getWhoClicked().getInventory(), 16, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cKnüppel")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 10)) {
				GiveItem(e.getWhoClicked().getInventory(), 17, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cHolzschwert")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 1)) {
				GiveItem(e.getWhoClicked().getInventory(), 18, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cHolzschwert I")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 4)) {
				GiveItem(e.getWhoClicked().getInventory(), 19, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cHolzschwert II")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 6)) {
				GiveItem(e.getWhoClicked().getInventory(), 20, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);

		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Eisenschwert")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 6)) {
				GiveItem(e.getWhoClicked().getInventory(), 21, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Bogen I")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 3)) {
				GiveItem(e.getWhoClicked().getInventory(), 22, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Bogen II")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 7)) {
				GiveItem(e.getWhoClicked().getInventory(), 23, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Bogen III")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 11)) {
				GiveItem(e.getWhoClicked().getInventory(), 24, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§dPfeil")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 1)) {
				GiveItem(e.getWhoClicked().getInventory(), 25, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
			//
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§2Apfel")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 2)) {
				GiveItem(e.getWhoClicked().getInventory(), 26, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§2Fleisch")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 4)) {
				GiveItem(e.getWhoClicked().getInventory(), 27, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§2Kuchen")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 1)) {
				GiveItem(e.getWhoClicked().getInventory(), 28, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§2Goldapfel")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 2)) {
				GiveItem(e.getWhoClicked().getInventory(), 29, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aKiste")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 2)) {
				GiveItem(e.getWhoClicked().getInventory(), 30, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aTeamkiste")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 2)) {
				GiveItem(e.getWhoClicked().getInventory(), 31, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Heilung I")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 5)) {
				GiveItem(e.getWhoClicked().getInventory(), 32, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Heilung II")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 8)) {
				GiveItem(e.getWhoClicked().getInventory(), 33, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Stärke")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 10)) {
				GiveItem(e.getWhoClicked().getInventory(), 34, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Hast")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 5)) {
				GiveItem(e.getWhoClicked().getInventory(), 35, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Sprungkraft")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 3)) {
				GiveItem(e.getWhoClicked().getInventory(), 36, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Leiter")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.CLAY_BRICK, 2)) {
				GiveItem(e.getWhoClicked().getInventory(), 37, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Teleporter")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 10)) {
				GiveItem(e.getWhoClicked().getInventory(), 38, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Mobiler Shop")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.IRON_INGOT, 12)) {
				GiveItem(e.getWhoClicked().getInventory(), 39, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6TNT")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 3)) {
				GiveItem(e.getWhoClicked().getInventory(), 40, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Fallschirm")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 2)) {
				GiveItem(e.getWhoClicked().getInventory(), 41, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Rettungsplattform")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 2)) {
				GiveItem(e.getWhoClicked().getInventory(), 42, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Enderperle")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 4)) {
				GiveItem(e.getWhoClicked().getInventory(), 43, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Spinnenweben")) {
			if (GenugMoney(e.getWhoClicked().getInventory(), Material.GOLD_INGOT, 4)) {
				GiveItem(e.getWhoClicked().getInventory(), 44, (Player) e.getWhoClicked());
			}
			e.setCancelled(true);
		}
		return;
	}

	/**
	 * @param inventory
	 * @param i
	 */
	private void GiveItem(Inventory inventory, int id, Player p) {
		if (getItem(id, p) == null)
			return;
		inventory.addItem(getItem(id, p));
		return;
	}

	/**
	 * @param i
	 * @return
	 */
	private ItemStack getItem(int i, Player player) {
		ItemStack item = null;
		if (i == 1) {
			item = new ItemStack(Material.RED_SANDSTONE, 4);
		} else if (i == 2) {
			item = new ItemStack(Material.ENDER_STONE, 1);
		} else if (i == 3) {
			item = new ItemStack(Material.IRON_BLOCK, 1);
		} else if (i == 4) {
			item = new ItemStack(Material.GLASS, 1);
		} else if (i == 5) {
			item = new ItemStack(Material.GLOWSTONE, 4);
		} else if (i == 6) {
			item = new ItemStack(Material.LEATHER_HELMET, 1);
			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setDisplayName("§3Lederhelm");
			meta.setColor(getTeamColor(player));
			item.setItemMeta(meta);
		} else if (i == 7) {
			item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setDisplayName("§3Lederhose");
			meta.setColor(getTeamColor(player));
			item.setItemMeta(meta);
		} else if (i == 8) {
			item = new ItemStack(Material.LEATHER_BOOTS, 1);
			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setDisplayName("§3Lederschuhe");
			meta.setColor(getTeamColor(player));
			item.setItemMeta(meta);
		} else if (i == 9) {
			item = new ItemStack(Material.IRON_CHESTPLATE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§3Brustplatte");
			item.setItemMeta(meta);
		} else if (i == 10) {
			item = new ItemStack(Material.IRON_CHESTPLATE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§3Brustplatte I");
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
			item.setItemMeta(meta);
		} else if (i == 11) {
			item = new ItemStack(Material.IRON_CHESTPLATE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§3Brustplatte II");
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
			item.setItemMeta(meta);
		} else if (i == 12) {
			item = new ItemStack(Material.IRON_CHESTPLATE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§3Brustplatte III");
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
			meta.addEnchant(Enchantment.THORNS, 1, true);
			item.setItemMeta(meta);
		} else if (i == 13) {
			item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setDisplayName("§3Sprengweste");
			meta.setColor(Color.BLACK);
			item.setItemMeta(meta);
		} else if (i == 14) {
			item = new ItemStack(Material.WOOD_PICKAXE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§eSpitzhacke I");
			item.setItemMeta(meta);
		} else if (i == 15) {
			item = new ItemStack(Material.STONE_PICKAXE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§eSpitzhacke II");
			item.setItemMeta(meta);
		} else if (i == 16) {
			item = new ItemStack(Material.IRON_PICKAXE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§eSpitzhacke III");
			item.setItemMeta(meta);
		} else if (i == 17) {
			item = new ItemStack(Material.STICK, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§cKnüppel");
			meta.addEnchant(Enchantment.KNOCKBACK, 1, true);
			item.setItemMeta(meta);
		} else if (i == 18) {
			item = new ItemStack(Material.WOOD_SWORD, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§cHolzschwert");
			item.setItemMeta(meta);
		} else if (i == 19) {
			item = new ItemStack(Material.WOOD_SWORD, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§cHolzschwert I");
			meta.addEnchant(Enchantment.DURABILITY, 1, true);
			meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			item.setItemMeta(meta);
		} else if (i == 20) {
			item = new ItemStack(Material.WOOD_SWORD, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§cHolzschwert II");
			meta.addEnchant(Enchantment.DURABILITY, 1, true);
			meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
			item.setItemMeta(meta);
		} else if (i == 21) {
			item = new ItemStack(Material.IRON_SWORD, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§6Eisenschwert");
			meta.addEnchant(Enchantment.KNOCKBACK, 1, true);
			meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
			item.setItemMeta(meta);
		} else if (i == 22) {
			item = new ItemStack(Material.BOW, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§5Bogen I");
			meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			item.setItemMeta(meta);
		} else if (i == 23) {
			item = new ItemStack(Material.BOW, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§5Bogen II");
			meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
			item.setItemMeta(meta);
		} else if (i == 24) {
			item = new ItemStack(Material.BOW, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§5Bogen III");
			meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
			item.setItemMeta(meta);
		} else if (i == 25) {
			item = new ItemStack(Material.ARROW, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§dPfeil");
			item.setItemMeta(meta);
		} else if (i == 26) {
			item = new ItemStack(Material.APPLE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§2Apfel");
			item.setItemMeta(meta);
		} else if (i == 27) {
			item = new ItemStack(Material.COOKED_BEEF, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§2Fleisch");
			item.setItemMeta(meta);
		} else if (i == 28) {
			item = new ItemStack(Material.CAKE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§2Kuchen");
			item.setItemMeta(meta);
		} else if (i == 29) {
			item = new ItemStack(Material.GOLDEN_APPLE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§2Goldapfel");
			item.setItemMeta(meta);
		} else if (i == 30) {
			item = new ItemStack(Material.CHEST, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§aKiste");
			item.setItemMeta(meta);
		} else if (i == 31) {
			item = new ItemStack(Material.ENDER_CHEST, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§aTeamkiste");
			item.setItemMeta(meta);
		} else if (i == 32) {
			item = new ItemStack(Material.POTION, 1, (short) 8197);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§3Heilung I");
			item.setItemMeta(meta);
		} else if (i == 33) {
			item = new ItemStack(Material.POTION, 1, (short) 8229);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§3Heilung II");
			item.setItemMeta(meta);
		} else if (i == 34) {
			item = new ItemStack(Material.POTION, 1, (short) 8201);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§3Stärke");
			item.setItemMeta(meta);
		} else if (i == 35) {
			item = new ItemStack(Material.POTION, 1);// XXX: Hast Effect | BedWars - SLN
			PotionMeta meta = (PotionMeta) item.getItemMeta();
			meta.setDisplayName("§3Hast");
			meta.addCustomEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 300, 1, true, true), true);
			item.setItemMeta(meta);
		} else if (i == 36) {
			item = new ItemStack(Material.POTION, 1, (short) 8235);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§3Sprungkraft");
			item.setItemMeta(meta);
		} else if (i == 37) {
			item = new ItemStack(Material.LADDER, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§6Leiter");
			item.setItemMeta(meta);
		} else if (i == 38) {
			item = new ItemStack(Material.FIREWORK, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§6Teleporter");
			List<String> list = new ArrayList<>();
			list.add("§eTeleportiert dich bei Aktivierung");
			list.add("§ezurück in deine Base");
			meta.setLore(list);
			item.setItemMeta(meta);
		} else if (i == 39) {
			item = new ItemStack(Material.ARMOR_STAND, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§6Mobiler Shop");
			item.setItemMeta(meta);
		} else if (i == 40) {
			item = new ItemStack(Material.TNT, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§6TNT");
			item.setItemMeta(meta);
		} else if (i == 41) {
			item = new ItemStack(Material.MONSTER_EGG, 1, (short) 93);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§6Fallschirm");
			item.setItemMeta(meta);
		} else if (i == 42) {
			item = new ItemStack(Material.NETHER_STAR);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§6Rettungsplattform");
			item.setItemMeta(meta);
		} else if (i == 43) {
			item = new ItemStack(Material.ENDER_PEARL);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§6Enderperle");
			item.setItemMeta(meta);
		} else if (i == 44) {
			item = new ItemStack(Material.WEB);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§6Spinnenweben");
			item.setItemMeta(meta);
		}
		return item;
	}

	/**
	 * @param inventory
	 * @param mat
	 * @param amount
	 * @return true or false
	 */
	@SuppressWarnings("deprecation")
	private boolean GenugMoney(Inventory inventory, Material mat, int amount) {
		if (!inventory.contains(mat)) {
			return false;
		}
		for (int i = 0; i < inventory.getSize(); i++) {
			if (inventory.getItem(i).getType() == mat) {
				if (inventory.getItem(i).getAmount() == amount) {
					inventory.remove(i);
					return true;
				} else if (inventory.getItem(i).getAmount() > amount) {
					inventory.getItem(i).setAmount(inventory.getItem(i).getAmount() - amount);
					return true;
				} else
					return false;
			} else
				continue;
		}
		return false;
	}

	/**
	 * @return inv
	 */
	private Inventory getSpecialInv() {
		Inventory inv = getShopInv();
		/* - Spezial Catogerie - */
		ItemStack spezial = new ItemStack(Material.EMERALD, 1);
		ItemMeta spezialMeta = spezial.getItemMeta();
		spezialMeta.setDisplayName("§bSpezial");
		spezialMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		spezialMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		spezial.setItemMeta(spezialMeta);

		/* - Leiter Item - */
		ItemStack ladder = new ItemStack(Material.LADDER, 1);
		ItemMeta ladderMeta = ladder.getItemMeta();
		ladderMeta.setDisplayName("§6Leiter");
		List<String> ladderlist = new ArrayList<>();
		ladderlist.add(" ");
		ladderlist.add(" §c§l2 Bronze");
		ladderMeta.setLore(ladderlist);
		ladder.setItemMeta(ladderMeta);

		/* - Teleporter Item - */
		ItemStack firework = new ItemStack(Material.FIREWORK, 1);
		ItemMeta fireworkMeta = firework.getItemMeta();
		fireworkMeta.setDisplayName("§6Teleporter");
		List<String> fireworklist = new ArrayList<>();
		fireworklist.add("§eTeleportiert dich bei Aktivierung");
		fireworklist.add("§ezurück in deine Base");
		fireworklist.add(" ");
		fireworklist.add(" §7§l10 Eisen");
		fireworkMeta.setLore(fireworklist);
		firework.setItemMeta(fireworkMeta);

		/* - Mobiler Shop Item - */
		ItemStack shop = new ItemStack(Material.ARMOR_STAND, 1);
		ItemMeta shopMeta = shop.getItemMeta();
		shopMeta.setDisplayName("§6Mobiler Shop");
		List<String> shoplist = new ArrayList<>();
		shoplist.add(" ");
		shoplist.add(" §7§l12 Eisen");
		shopMeta.setLore(shoplist);
		shop.setItemMeta(shopMeta);

		/* - TNT Block - */
		ItemStack TNT = new ItemStack(Material.TNT, 1);
		ItemMeta TNTMeta = TNT.getItemMeta();
		TNTMeta.setDisplayName("§6TNT");
		List<String> TNTList = new ArrayList<>();
		TNTList.add(" ");
		TNTList.add(" §6§l3 Gold");
		TNTMeta.setLore(TNTList);
		TNT.setItemMeta(TNTMeta);

		/* - Fallschirm Item - */
		ItemStack chicken = new ItemStack(Material.MONSTER_EGG, 1, (short) 93);
		ItemMeta chickenMeta = chicken.getItemMeta();
		shopMeta.setDisplayName("§6Fallschirm");
		List<String> chickenlist = new ArrayList<>();
		chickenlist.add(" ");
		chickenlist.add(" §6§l2 Gold");
		chickenMeta.setLore(chickenlist);
		chicken.setItemMeta(chickenMeta);

		/* - Rettungsplattform Item - */
		ItemStack plat = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta platMeta = plat.getItemMeta();
		platMeta.setDisplayName("§6Rettungsplattform");
		List<String> platlist = new ArrayList<>();
		platlist.add(" ");
		platlist.add(" §6§l2 Gold");
		platMeta.setLore(platlist);
		plat.setItemMeta(platMeta);

		/* - Enderperle Item - */
		ItemStack perl = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta perlMeta = perl.getItemMeta();
		perlMeta.setDisplayName("§6Enderperle");
		List<String> perllist = new ArrayList<>();
		perllist.add(" ");
		perllist.add(" §6§l4 Gold");
		perlMeta.setLore(perllist);
		perl.setItemMeta(perlMeta);

		/* - Spinnenweben Item - */
		ItemStack web = new ItemStack(Material.WEB, 1);
		ItemMeta webMeta = web.getItemMeta();
		webMeta.setDisplayName("§6Spinnenweben");
		List<String> weblist = new ArrayList<>();
		weblist.add(" ");
		weblist.add(" §6§l4 Gold");
		webMeta.setLore(weblist);
		web.setItemMeta(webMeta);

		inv.setItem(8, spezial);
		inv.setItem(9, ladder);
		inv.setItem(10, firework);
		inv.setItem(11, shop);
		inv.setItem(12, TNT);
		inv.setItem(14, chicken);
		inv.setItem(15, plat);
		inv.setItem(16, perl);
		inv.setItem(17, web);
		return inv;

	}

	/**
	 * @return inv
	 */
	private Inventory getPotitonInv() {
		Inventory inv = getShopInv();
		/* - Tränke Catogerie - */
		ItemStack potiton = new ItemStack(Material.POTION, 1);
		ItemMeta potitonMeta = potiton.getItemMeta();
		potitonMeta.setDisplayName("§bTränke");
		potitonMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		potitonMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		potiton.setItemMeta(potitonMeta);

		/* - Heilung 1 Item - */
		ItemStack poition1 = new ItemStack(Material.POTION, 1, (short) 8197);
		PotionMeta poition1Meta = (PotionMeta) poition1.getItemMeta();
		poition1Meta.setDisplayName("§3Heilung I");
		List<String> poition1list = new ArrayList<>();
		poition1list.add(" ");
		poition1list.add(" §7§l5 Eisen");
		poition1Meta.setLore(poition1list);
		poition1.setItemMeta(poition1Meta);

		/* - Heilung 2 Item - */
		ItemStack poition2 = new ItemStack(Material.POTION, 1, (short) 8229);
		PotionMeta poition2Meta = (PotionMeta) poition2.getItemMeta();
		poition2Meta.setDisplayName("§3Heilung II");
		List<String> poition2list = new ArrayList<>();
		poition2list.add(" ");
		poition2list.add(" §7§l8 Eisen");
		poition2Meta.setLore(poition2list);
		poition2.setItemMeta(poition2Meta);

		/* - Stärke Item - */
		ItemStack poition3 = new ItemStack(Material.POTION, 1, (short) 8201);
		PotionMeta poition3Meta = (PotionMeta) poition3.getItemMeta();
		poition3Meta.setDisplayName("§3Stärke");
		List<String> poition3list = new ArrayList<>();
		poition3list.add(" ");
		poition3list.add(" §6§l10 Gold");
		poition3Meta.setLore(poition3list);
		poition3.setItemMeta(poition3Meta);

		/* - Haste Item - */
		ItemStack poition4 = new ItemStack(Material.POTION, 1);// XXX: Hast Effect | BedWars - SLN
		PotionMeta poition4Meta = (PotionMeta) poition4.getItemMeta();
		poition4Meta.setDisplayName("§3Hast");
		poition4Meta.addCustomEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 300, 1, true, true), true);
		List<String> poition4list = new ArrayList<>();
		poition4list.add(" ");
		poition4list.add(" §6§l5 Gold");
		poition4Meta.setLore(poition4list);
		poition4.setItemMeta(poition4Meta);

		/* - Sprungkraft Item - */
		ItemStack poition5 = new ItemStack(Material.POTION, 1, (short) 8235);
		PotionMeta poition5Meta = (PotionMeta) poition5.getItemMeta();
		poition5Meta.setDisplayName("§3Sprungkraft");
		List<String> poition5list = new ArrayList<>();
		poition5list.add(" ");
		poition5list.add(" §6§l3 Gold");
		poition5Meta.setLore(poition5list);
		poition5.setItemMeta(poition5Meta);

		inv.setItem(7, potiton);
		inv.setItem(9, poition1);
		inv.setItem(11, poition2);
		inv.setItem(13, poition3);
		inv.setItem(15, poition4);
		inv.setItem(17, poition5);
		return inv;
	}

	/**
	 * @return inv
	 */
	private Inventory getChestInv() {
		Inventory inv = getShopInv();
		/* - Kisten Catogerie - */
		ItemStack chest = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta chestMeta = chest.getItemMeta();
		chestMeta.setDisplayName("§bKisten");
		chestMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		chestMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		chest.setItemMeta(chestMeta);

		/* - Kiste Item - */
		ItemStack chest1 = new ItemStack(Material.CHEST, 1);
		ItemMeta chestMeta1 = chest1.getItemMeta();
		chestMeta1.setDisplayName("§aKiste");
		List<String> chestlist1 = new ArrayList<>();
		chestlist1.add(" ");
		chestlist1.add(" §7§l2 Eisen");
		chestMeta1.setLore(chestlist1);
		chest1.setItemMeta(chestMeta1);

		/* - Teamkiste Item - */
		ItemStack Teamchest = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta TeamchestMeta = Teamchest.getItemMeta();
		TeamchestMeta.setDisplayName("§aTeamkiste");
		List<String> Teamchestlist = new ArrayList<>();
		Teamchestlist.add(" ");
		Teamchestlist.add(" §6§l2 Gold");
		TeamchestMeta.setLore(Teamchestlist);
		Teamchest.setItemMeta(TeamchestMeta);

		inv.setItem(6, chest);
		inv.setItem(12, chest1);
		inv.setItem(14, Teamchest);
		return inv;
	}

	/**
	 * @return inv
	 */
	private Inventory getFoodInv() {
		Inventory inv = getShopInv();
		/* - Nahrung Catogerie - */
		ItemStack food = new ItemStack(Material.CAKE, 1);
		ItemMeta foodMeta = food.getItemMeta();
		foodMeta.setDisplayName("§bNahrung");
		foodMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		foodMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		food.setItemMeta(foodMeta);

		/* - Apfel Item - */
		ItemStack appel = new ItemStack(Material.APPLE, 1);
		ItemMeta appelMeta = appel.getItemMeta();
		appelMeta.setDisplayName("§2Apfel");
		List<String> appelist = new ArrayList<>();
		appelist.add(" ");
		appelist.add(" §c§l2 Bronze");
		appelMeta.setLore(appelist);
		appel.setItemMeta(appelMeta);

		/* - Fleisch Item - */
		ItemStack beef = new ItemStack(Material.COOKED_BEEF, 1);
		ItemMeta beefMeta = beef.getItemMeta();
		beefMeta.setDisplayName("§2Fleisch");
		List<String> beeflist = new ArrayList<>();
		beeflist.add(" ");
		beeflist.add(" §c§l4 Bronze");
		beefMeta.setLore(beeflist);
		beef.setItemMeta(beefMeta);

		/* - Kuchen Item - */
		ItemStack CAKE = new ItemStack(Material.CAKE, 1);
		ItemMeta CAKEMeta = CAKE.getItemMeta();
		CAKEMeta.setDisplayName("§2Kuchen");
		List<String> CAKElist = new ArrayList<>();
		CAKElist.add(" ");
		CAKElist.add(" §7§l1 Eisen");
		CAKEMeta.setLore(CAKElist);
		CAKE.setItemMeta(CAKEMeta);

		/* - Goldapfel Item - */
		ItemStack gold = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemMeta goldMeta = gold.getItemMeta();
		goldMeta.setDisplayName("§2Goldapfel");
		List<String> goldlist = new ArrayList<>();
		goldlist.add(" ");
		goldlist.add(" §6§l2 Gold");
		goldMeta.setLore(goldlist);
		gold.setItemMeta(goldMeta);

		inv.setItem(5, food);
		inv.setItem(11, appel);
		inv.setItem(12, beef);
		inv.setItem(13, CAKE);
		inv.setItem(15, gold);
		return inv;
	}

	/**
	 * @return inv
	 */
	private Inventory getBowInv() {
		Inventory inv = getShopInv();
		/* - Bögen Catogerie - */
		ItemStack bow = new ItemStack(Material.BOW, 1);
		ItemMeta bowMeta = bow.getItemMeta();
		bowMeta.setDisplayName("§bBögen");
		bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		bowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		bow.setItemMeta(bowMeta);

		/* - Bogen1 Item - */
		ItemStack bow1 = new ItemStack(Material.BOW, 1);
		ItemMeta bow1Meta = bow1.getItemMeta();
		bow1Meta.setDisplayName("§5Bogen I");
		bow1Meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		List<String> bow1list = new ArrayList<>();
		bow1list.add(" ");
		bow1list.add(" §6§l3 Gold");
		bow1Meta.setLore(bow1list);
		bow1.setItemMeta(bow1Meta);

		/* - Bogen2 Item - */
		ItemStack bow2 = new ItemStack(Material.BOW, 1);
		ItemMeta bow2Meta = bow2.getItemMeta();
		bow2Meta.setDisplayName("§5Bogen II");
		bow2Meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		bow2Meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		List<String> bow2list = new ArrayList<>();
		bow2list.add(" ");
		bow2list.add(" §6§l7 Gold");
		bow2Meta.setLore(bow2list);
		bow2.setItemMeta(bow2Meta);

		/* - Bogen3 Item - */
		ItemStack bow3 = new ItemStack(Material.BOW, 1);
		ItemMeta bow3Meta = bow3.getItemMeta();
		bow3Meta.setDisplayName("§5Bogen III");
		bow3Meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		bow3Meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
		List<String> bow3list = new ArrayList<>();
		bow3list.add(" ");
		bow3list.add(" §6§l11 Gold");
		bow3Meta.setLore(bow3list);
		bow3.setItemMeta(bow3Meta);

		/* - Pfeil Item - */
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowMeta = arrow.getItemMeta();
		arrowMeta.setDisplayName("§dPfeil");
		List<String> arrowlist = new ArrayList<>();
		arrowlist.add(" ");
		arrowlist.add(" §6§l1 Gold");
		arrowMeta.setLore(arrowlist);
		arrow.setItemMeta(arrowMeta);

		inv.setItem(4, bow);
		inv.setItem(11, bow1);
		inv.setItem(12, bow2);
		inv.setItem(13, bow3);
		inv.setItem(15, arrow);
		return inv;
	}

	/**
	 * @return inv
	 */
	private Inventory getSwordInv() {
		Inventory inv = getShopInv();
		/* - Schwerter Catogerie - */
		ItemStack sword = new ItemStack(Material.WOOD_SWORD, 1);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		swordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		swordMeta.setDisplayName("§bSchwerter");
		sword.setItemMeta(swordMeta);

		/* - Stick Item - */
		ItemStack stick = new ItemStack(Material.STICK, 1);
		ItemMeta stickMeta = stick.getItemMeta();
		stickMeta.setDisplayName("§cKnüppel");
		stickMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
		List<String> sticklist = new ArrayList<>();
		sticklist.add(" ");
		sticklist.add(" §c§l10 Bronze");
		stickMeta.setLore(sticklist);
		stick.setItemMeta(stickMeta);

		/* - Wood Item - */
		ItemStack wood = new ItemStack(Material.WOOD_SWORD, 1);
		ItemMeta woodMeta = wood.getItemMeta();
		woodMeta.setDisplayName("§cHolzschwert");
		List<String> woodlist = new ArrayList<>();
		woodlist.add(" ");
		woodlist.add(" §7§l1 Eisen");
		woodMeta.setLore(woodlist);
		wood.setItemMeta(woodMeta);

		/* - Wood1 Item - */
		ItemStack wood1 = new ItemStack(Material.WOOD_SWORD, 1);
		ItemMeta wood1Meta = wood1.getItemMeta();
		wood1Meta.setDisplayName("§cHolzschwert I");
		wood1Meta.addEnchant(Enchantment.DURABILITY, 1, true);
		wood1Meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		List<String> wood1list = new ArrayList<>();
		wood1list.add(" ");
		wood1list.add(" §7§l4 Eisen");
		wood1Meta.setLore(wood1list);
		wood1.setItemMeta(wood1Meta);

		/* - Wood2 Item - */
		ItemStack wood2 = new ItemStack(Material.WOOD_SWORD, 1);
		ItemMeta wood2Meta = wood2.getItemMeta();
		wood2Meta.setDisplayName("§cHolzschwert II");
		wood2Meta.addEnchant(Enchantment.DURABILITY, 1, true);
		wood2Meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		List<String> wood2list = new ArrayList<>();
		wood2list.add(" ");
		wood2list.add(" §7§l6 Eisen");
		wood2Meta.setLore(wood2list);
		wood2.setItemMeta(wood2Meta);

		/* - Iron Item - */
		ItemStack iron = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta ironMeta = iron.getItemMeta();
		ironMeta.setDisplayName("§6Eisenschwert");
		ironMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
		ironMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		List<String> ironlist = new ArrayList<>();
		ironlist.add(" ");
		ironlist.add(" §7§l6 Gold");
		ironMeta.setLore(ironlist);
		iron.setItemMeta(ironMeta);

		inv.setItem(3, sword);
		inv.setItem(11, stick);
		inv.setItem(12, wood);
		inv.setItem(13, wood1);
		inv.setItem(14, wood2);
		inv.setItem(15, iron);
		return inv;
	}

	/**
	 * @return inv
	 */
	private Inventory getPickaxInv() {
		Inventory inv = getShopInv();
		/* - Spitzhacken Catogerie - */
		ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE, 1);
		ItemMeta pickaxeMeta = pickaxe.getItemMeta();
		pickaxeMeta.setDisplayName("§bSpitzhacken");
		pickaxeMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		pickaxeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		pickaxe.setItemMeta(pickaxeMeta);

		/* - Spitzhacke1 Item - */
		ItemStack spitzhacke1 = new ItemStack(Material.WOOD_PICKAXE, 1);
		ItemMeta spitzhacke1Meta = spitzhacke1.getItemMeta();
		spitzhacke1Meta.setDisplayName("§eSpitzhacke I");
		List<String> spitzhacke1list = new ArrayList<>();
		spitzhacke1list.add(" ");
		spitzhacke1list.add(" §c§l7 Bronze");
		spitzhacke1Meta.setLore(spitzhacke1list);
		spitzhacke1.setItemMeta(spitzhacke1Meta);

		/* - Spitzhacke2 Item - */
		ItemStack spitzhacke2 = new ItemStack(Material.STONE_PICKAXE, 1);
		ItemMeta spitzhacke2Meta = spitzhacke2.getItemMeta();
		spitzhacke2Meta.setDisplayName("§eSpitzhacke II");
		List<String> spitzhacke2list = new ArrayList<>();
		spitzhacke2list.add(" ");
		spitzhacke2list.add(" §7§l2 Eisen");
		spitzhacke2Meta.setLore(spitzhacke2list);
		spitzhacke2.setItemMeta(spitzhacke2Meta);

		/* - Spitzhacke3 Item - */
		ItemStack spitzhacke3 = new ItemStack(Material.IRON_PICKAXE, 1);
		ItemMeta spitzhacke3Meta = spitzhacke3.getItemMeta();
		spitzhacke3Meta.setDisplayName("§eSpitzhacke III");
		List<String> spitzhacke3list = new ArrayList<>();
		spitzhacke3list.add(" ");
		spitzhacke3list.add(" §6§l1 Gold");
		spitzhacke3Meta.setLore(spitzhacke3list);
		spitzhacke3.setItemMeta(spitzhacke3Meta);

		inv.setItem(2, pickaxe);
		inv.setItem(12, spitzhacke1);
		inv.setItem(13, spitzhacke2);
		inv.setItem(14, spitzhacke3);
		return inv;
	}

	/**
	 * @param player
	 * @return inv
	 */
	private Inventory getRustungInv(Player player) {
		Inventory inv = getShopInv();
		/* - Rüstung Catogerie - */
		ItemStack armor = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
		ItemMeta armorMeta = armor.getItemMeta();
		armorMeta.setDisplayName("§bRüstung");
		armorMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		armorMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		armor.setItemMeta(armorMeta);

		/* - Lederhelm Item - */
		ItemStack Lederhelm = new ItemStack(Material.LEATHER_HELMET, 1);
		LeatherArmorMeta LederhelmMeta = (LeatherArmorMeta) Lederhelm.getItemMeta();
		LederhelmMeta.setDisplayName("§3Lederhelm");
		LederhelmMeta.setColor(getTeamColor(player));
		LederhelmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		LederhelmMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		List<String> Lederhelmlist = new ArrayList<>();
		Lederhelmlist.add(" ");
		Lederhelmlist.add(" §c§l1 Bronze");
		LederhelmMeta.setLore(Lederhelmlist);
		Lederhelm.setItemMeta(LederhelmMeta);

		/* - Lederhose Item - */
		ItemStack Lederhose = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta LederhoseMeta = (LeatherArmorMeta) Lederhose.getItemMeta();
		LederhoseMeta.setDisplayName("§3Lederhose");
		LederhoseMeta.setColor(getTeamColor(player));
		LederhoseMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		LederhoseMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		List<String> LederhoseList = new ArrayList<>();
		LederhoseList.add(" ");
		LederhoseList.add(" §c§l1 Bronze");
		LederhoseMeta.setLore(LederhoseList);
		Lederhose.setItemMeta(LederhoseMeta);

		/* - Lederschuhe Item - */
		ItemStack LederShoe = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta LederShoeMeta = (LeatherArmorMeta) LederShoe.getItemMeta();
		LederShoeMeta.setDisplayName("§3Lederschuhe");
		LederShoeMeta.setColor(getTeamColor(player));
		LederShoeMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		LederShoeMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		List<String> LederShoelist = new ArrayList<>();
		LederShoelist.add(" ");
		LederShoelist.add(" §c§l1 Bronze");
		LederShoeMeta.setLore(LederShoelist);
		LederShoe.setItemMeta(LederShoeMeta);

		/* - Brustplatte Item - */
		ItemStack Brustplatte1 = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
		ItemMeta Brustplatte1Meta = Brustplatte1.getItemMeta();
		Brustplatte1Meta.setDisplayName("§3Brustplatte");
		List<String> Brustplatte1list = new ArrayList<>();
		Brustplatte1list.add(" ");
		Brustplatte1list.add(" §7§l1 Eisen");
		Brustplatte1Meta.setLore(Brustplatte1list);
		Brustplatte1.setItemMeta(Brustplatte1Meta);

		/* - Brustplatte1 Item - */
		ItemStack Brustplatte2 = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
		ItemMeta Brustplatte2Meta = Brustplatte2.getItemMeta();
		Brustplatte2Meta.setDisplayName("§3Brustplatte I");
		Brustplatte2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		List<String> Brustplatte2list = new ArrayList<>();
		Brustplatte2list.add(" ");
		Brustplatte2list.add(" §7§l3 Eisen");
		Brustplatte2Meta.setLore(Brustplatte2list);
		Brustplatte2.setItemMeta(Brustplatte2Meta);

		/* - Brustplatte2 Item - */
		ItemStack Brustplatte3 = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
		ItemMeta Brustplatte3Meta = Brustplatte3.getItemMeta();
		Brustplatte3Meta.setDisplayName("§3Brustplatte II");
		Brustplatte3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		List<String> Brustplatte3list = new ArrayList<>();
		Brustplatte3list.add(" ");
		Brustplatte3list.add(" §7§l7 Eisen");
		Brustplatte3Meta.setLore(Brustplatte3list);
		Brustplatte3.setItemMeta(Brustplatte3Meta);

		/* - Brustplatte3 Item - */
		ItemStack Brustplatte4 = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
		ItemMeta Brustplatte4Meta = Brustplatte4.getItemMeta();
		Brustplatte4Meta.setDisplayName("§3Brustplatte III");
		Brustplatte4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		Brustplatte4Meta.addEnchant(Enchantment.THORNS, 1, true);
		List<String> Brustplatte4list = new ArrayList<>();
		Brustplatte4list.add(" ");
		Brustplatte4list.add(" §7§l11 Eisen");
		Brustplatte4Meta.setLore(Brustplatte4list);
		Brustplatte4.setItemMeta(Brustplatte4Meta);

		/* - Sprengweste Item - */
		ItemStack Sprengweste = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta SprengwesteMeta = (LeatherArmorMeta) Sprengweste.getItemMeta();
		SprengwesteMeta.setDisplayName("§3Sprengweste");
		SprengwesteMeta.setColor(Color.BLACK);
		List<String> Sprengwestelist = new ArrayList<>();
		Sprengwestelist.add(" ");
		Sprengwestelist.add(" §6§l6 Gold");
		SprengwesteMeta.setLore(Sprengwestelist);
		Sprengweste.setItemMeta(SprengwesteMeta);

		inv.setItem(1, armor);
		inv.setItem(9, Lederhelm);
		inv.setItem(10, Lederhose);
		inv.setItem(11, LederShoe);
		inv.setItem(13, Brustplatte1);
		inv.setItem(14, Brustplatte2);
		inv.setItem(15, Brustplatte3);
		inv.setItem(16, Brustplatte4);
		inv.setItem(17, Sprengweste);
		return inv;
	}

	/**
	 * @param player
	 * @return Color
	 */
	private Color getTeamColor(Player player) {
		if (Main.getInstance().PlayerLive.contains(player)) {
			if (Main.getInstance().PlayerTeam.containsKey(player)) {
				if (Main.getInstance().PlayerTeam.get(player).getColor() == ColorEnum.YELLOW)
					return Color.YELLOW;
				if (Main.getInstance().PlayerTeam.get(player).getColor() == ColorEnum.BLUE)
					return Color.BLUE;
				if (Main.getInstance().PlayerTeam.get(player).getColor() == ColorEnum.BLACK)
					return Color.BLACK;
				if (Main.getInstance().PlayerTeam.get(player).getColor() == ColorEnum.GREEN)
					return Color.GREEN;
				if (Main.getInstance().PlayerTeam.get(player).getColor() == ColorEnum.RED)
					return Color.RED;
			}
		}
		return Color.NAVY;
	}

	/**
	 * @return inv
	 */
	private Inventory getBlockInv() {
		Inventory inv = getShopInv();
		/* - Blöcke Catogerie - */
		ItemStack blocks = new ItemStack(Material.RED_SANDSTONE, 1);
		ItemMeta blocksMeta = blocks.getItemMeta();
		blocksMeta.setDisplayName("§bBlöcke");
		blocksMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		blocksMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		blocks.setItemMeta(blocksMeta);

		/* - Sandstein Block - */
		ItemStack sandstone = new ItemStack(Material.RED_SANDSTONE, 4);
		ItemMeta sandstoneMeta = sandstone.getItemMeta();
		sandstoneMeta.setDisplayName("§7Sandstein");
		List<String> sandstonelist = new ArrayList<>();
		sandstonelist.add(" ");
		sandstonelist.add(" §c§l1 Bronze");
		sandstoneMeta.setLore(sandstonelist);
		sandstone.setItemMeta(sandstoneMeta);

		/* - Endstein Block - */
		ItemStack endstone = new ItemStack(Material.ENDER_STONE, 1);
		ItemMeta endstoneMeta = endstone.getItemMeta();
		endstoneMeta.setDisplayName("§7Endstein");
		List<String> endstonelist = new ArrayList<>();
		endstonelist.add(" ");
		endstonelist.add(" §c§l6 Bronze");
		endstoneMeta.setLore(endstonelist);
		endstone.setItemMeta(endstoneMeta);

		/* - Eisenblock Block - */
		ItemStack ironblock = new ItemStack(Material.IRON_BLOCK, 1);
		ItemMeta ironblockMeta = ironblock.getItemMeta();
		ironblockMeta.setDisplayName("§7Eisenblock");
		List<String> ironblocklist = new ArrayList<>();
		ironblocklist.add(" ");
		ironblocklist.add(" §7§l3 Eisen");
		ironblockMeta.setLore(ironblocklist);
		ironblock.setItemMeta(ironblockMeta);

		/* - Glas Block - */
		ItemStack glass = new ItemStack(Material.GLASS, 1);
		ItemMeta glassMeta = glass.getItemMeta();
		glassMeta.setDisplayName("§7Glas");
		List<String> glasslist = new ArrayList<>();
		glasslist.add(" ");
		glasslist.add(" §c§l3 Bronze");
		glassMeta.setLore(glasslist);
		glass.setItemMeta(glassMeta);

		/* - Glowstone Block - */
		ItemStack glowstone = new ItemStack(Material.GLOWSTONE, 4);
		ItemMeta glowstoneMeta = glowstone.getItemMeta();
		glowstoneMeta.setDisplayName("§7Glowstone");
		List<String> glowstonelist = new ArrayList<>();
		glowstonelist.add(" ");
		glowstonelist.add(" §c§l14 Bronze");
		glowstoneMeta.setLore(glowstonelist);
		glowstone.setItemMeta(glowstoneMeta);

		inv.setItem(0, blocks);
		inv.setItem(9, sandstone);
		inv.setItem(10, endstone);
		inv.setItem(11, ironblock);
		inv.setItem(12, glass);
		inv.setItem(13, glowstone);
		return inv;
	}

	/**
	 * @return inv
	 */
	private Inventory getShopInv() {
		Inventory inv = Bukkit.createInventory(null, 18, "§3BedWars Shop");
		/* - Blöcke Catogerie - */
		ItemStack blocks = new ItemStack(Material.RED_SANDSTONE, 1);
		ItemMeta blocksMeta = blocks.getItemMeta();
		blocksMeta.setDisplayName("§bBlöcke");
		blocks.setItemMeta(blocksMeta);
		/* - Rüstung Catogerie - */
		ItemStack armor = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
		ItemMeta armorMeta = armor.getItemMeta();
		armorMeta.setDisplayName("§bRüstung");
		armor.setItemMeta(armorMeta);
		/* - Spitzhacken Catogerie - */
		ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE, 1);
		ItemMeta pickaxeMeta = pickaxe.getItemMeta();
		pickaxeMeta.setDisplayName("§bSpitzhacken");
		pickaxe.setItemMeta(pickaxeMeta);
		/* - Schwerter Catogerie - */
		ItemStack sword = new ItemStack(Material.WOOD_SWORD, 1);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName("§bSchwerter");
		sword.setItemMeta(swordMeta);
		/* - Bögen Catogerie - */
		ItemStack bow = new ItemStack(Material.BOW, 1);
		ItemMeta bowMeta = bow.getItemMeta();
		bowMeta.setDisplayName("§bBögen");
		bow.setItemMeta(bowMeta);
		/* - Nahrung Catogerie - */
		ItemStack food = new ItemStack(Material.CAKE, 1);
		ItemMeta foodMeta = food.getItemMeta();
		foodMeta.setDisplayName("§bNahrung");
		food.setItemMeta(foodMeta);
		/* - Kisten Catogerie - */
		ItemStack chest = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta chestMeta = chest.getItemMeta();
		chestMeta.setDisplayName("§bKisten");
		chest.setItemMeta(chestMeta);
		/* - Tränke Catogerie - */
		ItemStack potiton = new ItemStack(Material.POTION, 1);
		ItemMeta potitonMeta = potiton.getItemMeta();
		potitonMeta.setDisplayName("§bTränke");
		potiton.setItemMeta(potitonMeta);
		/* - Spezial Catogerie - */
		ItemStack spezial = new ItemStack(Material.EMERALD, 1);
		ItemMeta spezialMeta = spezial.getItemMeta();
		spezialMeta.setDisplayName("§bSpezial");
		spezial.setItemMeta(spezialMeta);

		inv.setItem(0, blocks);
		inv.setItem(1, armor);
		inv.setItem(2, pickaxe);
		inv.setItem(3, sword);
		inv.setItem(4, bow);
		inv.setItem(5, food);
		inv.setItem(6, chest);
		inv.setItem(7, potiton);
		inv.setItem(8, spezial);
		return inv;
	}

}
