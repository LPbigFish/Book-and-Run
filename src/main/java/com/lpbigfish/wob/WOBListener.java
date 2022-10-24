package com.lpbigfish.wob;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WOBListener implements Listener {
    private final WOB plugin;

    private static ItemStack TheItem;

    public WOBListener(WOB plugin) {
        this.plugin = plugin;
        TheItem = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Material")));
        ItemMeta meta = TheItem.getItemMeta();
        meta.setDisplayName(plugin.getConfig().getString("DisplayName"));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(plugin.getConfig().getStringList("Lore").subList(0, 3));
        TheItem.setItemMeta(meta);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().getInventory().setItem(plugin.getConfig().getInt("Slot"), TheItem);
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().isSimilar(TheItem)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        if (event.getItem().isSimilar(TheItem)) {
            event.getPlayer().performCommand(plugin.getConfig().getStringList("CommandOnClick").get(0));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        event.getPlayer().getInventory().remove(TheItem);
    }

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent event) {
        if (event.getCurrentItem().isSimilar(TheItem)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent event) {
        if (event.getPlayer().getInventory().contains(TheItem)) {
            event.getPlayer().getInventory().setItem(plugin.getConfig().getInt("Slot"), TheItem);
        }
    }
}
