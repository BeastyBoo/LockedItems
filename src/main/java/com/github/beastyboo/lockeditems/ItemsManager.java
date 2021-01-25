package com.github.beastyboo.lockeditems;

import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ItemsManager {

    private final UUID uuid;
    private final Set<Material> items;

    public ItemsManager(UUID uuid, Set<Material> items) {
        this.uuid = uuid;
        this.items = items;
    }

    public Set<Material> getItems() {
        return this.items;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean checkIfBlocked(Material item) {
        if (this.items.contains(item)) return true;
        return false;
    }

}
