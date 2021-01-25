package com.github.beastyboo.lockeditems;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public class DropFile {

    private final LockedItems core;
    private final File file;
    private final FileConfiguration config;

    public DropFile(LockedItems core) {
        this.core = core;
        file = new File(core.getDataFolder(), "locked-items.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public void createFile(){
        if(!core.getDataFolder().exists()) {
            core.getDataFolder().mkdirs();
        }

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
    }

}
