/********************************************
            DIAMONDNOTIFIER PLUGIN:

   This plugin notifies users of permission
     "DiamondNotifier.isNotified" when a
    diamond ore block is broken in a new
               chunk below Y17.

     Copyright (C) 2020 - David DeCorso
  This plugin is free to use and distribute.
 *******************************************/

package me.davidfire1332.diamondnotifier;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class DiamondNotifier extends JavaPlugin implements Listener {

    private HashMap<Chunk, String> diamondVeins = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("§3[DiamondNotifier] Loaded plugin.");
    }

    @EventHandler
    public void onDiamondMined(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        Material material = block.getType();
        Chunk chunk = block.getChunk();

        if (material.equals(Material.DIAMOND_ORE) && block.getY() < 17 && !diamondVeins.containsKey(chunk)) {
            Bukkit.broadcast("§3[DiamondNotifier] §f" + player.getName() + " just mined diamonds.", "DiamondNotifier.isNotified");
            diamondVeins.put(chunk, player.getName());
        }
    }
}
