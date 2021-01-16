package com.github.beastyboo.lockeditems;

import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public class ItemsManager {

    private Set<Material> items;

    public ItemsManager () {
        this.items = new HashSet<>();
    }

    public Set<Material> getItems() {
        return this.items;
    }

    public boolean checkIfBlocked(Material item) {
        if (this.items.contains(item)) return true;
        return false;
    }

}
