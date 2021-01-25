package com.github.beastyboo.lockeditems;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class DropManager {

    private final LockedItems core;
    private final Map<UUID, ItemsManager> cache;
    private final DropFile file;

    public DropManager(LockedItems core) {
        this.core = core;
        cache = new HashMap<>();
        file = new DropFile(core);
    }

    public void load(){
        FileConfiguration config = file.getConfig();

        if(config.getConfigurationSection("users") != null) {
            for(String uuid : config.getConfigurationSection("users").getKeys(false)) {
                List<String> output = config.getStringList("users." + uuid);

                Set<Material> items = new HashSet<>();
                for(String s : output) {
                    items.add(Material.valueOf(s));
                }

                cache.put(UUID.fromString(uuid), new ItemsManager(UUID.fromString(uuid), items));
            }
        }
    }

    public void close() {
        FileConfiguration config = file.getConfig();

        if(!file.getFile().exists()) {
            file.createFile();
        }

        for(ItemsManager item : cache.values()) {

            List<String> list = new ArrayList<>();
            for(Material material : item.getItems()) {
                list.add(material.name());
            }

            config.set("users." + item.getUuid().toString(), list);
            file.save();

        }
    }

    public Map<UUID, ItemsManager> getCache() {
        return cache;
    }
}
