package com.lpbigfish.wob;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class WOBListener implements Listener {
    private final WOB plugin;

    private static ItemStack TheItem;

    public boolean isWhiteListed(String player) {
        try {
            if (plugin.getConfig().getList("Exclude").contains(player)) {
                return false;
            }
        }
        catch (Exception ignored) {}
        return true;
    }

    public WOBListener(WOB plugin) {
        this.plugin = plugin;
        TheItem = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(plugin.getConfig().getString("Material")))));
        ItemMeta meta = TheItem.getItemMeta();
        assert meta != null;
        meta.setDisplayName(plugin.getConfig().getString("DisplayName"));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(plugin.getConfig().getStringList("Lore"));
        TheItem.setItemMeta(meta);
        for (Player player : plugin.getServer().getOnlinePlayers()) {
              {
                if (isWhiteListed(player.getName())) {
                    player.getInventory().remove(TheItem);
                    player.getInventory().setItem(plugin.getConfig().getInt("Slot"), TheItem);
                }
              }

        }
    }

    private void bugFix(Player player) {
        if (isWhiteListed(player.getName())) {
            player.getInventory().remove(TheItem);
            player.getInventory().setItem(plugin.getConfig().getInt("Slot"), TheItem);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (isWhiteListed(event.getPlayer().getName()) && event.getItemInHand().isSimilar(TheItem)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (isWhiteListed(event.getPlayer().getName())) {
            event.getPlayer().getInventory().setItem(plugin.getConfig().getInt("Slot"), TheItem);
        }
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        if (isWhiteListed(event.getPlayer().getName()) && event.getItemDrop().getItemStack().isSimilar(TheItem)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        try {
            if (isWhiteListed(event.getPlayer().getName()) && event.getItem() != null && event.getItem().isSimilar(TheItem)) {
                event.setCancelled(true);
                for (int i = 0; i < plugin.getConfig().getStringList("CommandOnClick").size(); i++) {
                    event.getPlayer().performCommand(plugin.getConfig().getStringList("CommandOnClick").get(i));
                }
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf(plugin.getConfig().getString("Sound")), 1, 1);
            }
        } catch (Exception ignored) {
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        event.getPlayer().getInventory().remove(TheItem);
    }

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent event) {

        try {
            if (isWhiteListed(event.getWhoClicked().getName()) && Objects.requireNonNull(event.getCurrentItem()).isSimilar(TheItem)) {
                event.setCancelled(true);
            }
        } catch (Exception ignored) {
        }


    }

    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent event) {
        if (isWhiteListed(event.getPlayer().getName()) && event.getPlayer().getInventory().contains(TheItem)) {
            event.getPlayer().getInventory().setItem(plugin.getConfig().getInt("Slot"), TheItem);
            if (event.getInventory().getType().isCreatable())
                bugFix((Player) event.getPlayer());
        }
    }

    @EventHandler
    public void onInventoryMove(InventoryMoveItemEvent event) {
        try {
            if (event.getItem().isSimilar(TheItem)) {
                event.setCancelled(true);
            }
        } catch (Exception ignored) {
        }
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        if (event.getEntity().getInventory().contains(TheItem)) {
            event.getEntity().getInventory().remove(TheItem);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        if (isWhiteListed(event.getPlayer().getName()) && event.getPlayer().getInventory().getItemInMainHand().isSimilar(TheItem)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractAt(PlayerInteractAtEntityEvent event) {
        if (isWhiteListed(event.getPlayer().getName()) && event.getPlayer().getInventory().getItemInMainHand().isSimilar(TheItem)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public  void onRespawn(PlayerRespawnEvent event) {
        if (!event.getPlayer().getInventory().contains(TheItem)) {
            event.getPlayer().getInventory().setItem(plugin.getConfig().getInt("Slot"), TheItem);
        }
    }

    @EventHandler
    public void onPlayerPickup(EntityPickupItemEvent event) {
        try {
            if (event.getEntity() instanceof Player && isWhiteListed(((Player) event.getEntity()).getPlayer().getName()) && event.getItem().getItemStack().isSimilar(TheItem)) {
                event.setCancelled(true);
            }
        } catch (Exception ignored) {
        }

    }

    //call when an item is found on a ground
    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        if (event.getEntity().getItemStack().isSimilar(TheItem)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getInventory().getType().isCreatable() && event.getInventory().contains(TheItem)) {

            bugFix((Player) event.getPlayer());
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getInventory().getType().isCreatable() && event.getInventory().contains(TheItem)) {
            event.getInventory().remove(TheItem);
        }
    }
}
