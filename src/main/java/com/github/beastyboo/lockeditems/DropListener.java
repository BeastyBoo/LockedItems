package com.github.beastyboo.lockeditems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {

    private final LockedItems core;

    public DropListener(LockedItems core) {
        this.core = core;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if(!core.getCache().containsKey(player.getUniqueId())) {
            return;
        }

        ItemsManager manager = core.getCache().get(player.getUniqueId());
        Material hand = event.getItemDrop().getItemStack().getType();

        if(manager.getItems().contains(hand)) {
            event.setCancelled(true);
        }

    }

}
