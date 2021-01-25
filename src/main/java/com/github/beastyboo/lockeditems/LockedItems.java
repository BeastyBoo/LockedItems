package com.github.beastyboo.lockeditems;

import org.bukkit.plugin.java.JavaPlugin;

public final class LockedItems extends JavaPlugin {

    private DropManager dropManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        dropManager = new DropManager(this);

        getCommand("lockitem").setExecutor(new DropCommand(this));
        getServer().getPluginManager().registerEvents(new DropListener(this), this);

        dropManager.load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        dropManager.close();
        dropManager = null;
    }

    public DropManager getDropManager() {
        return dropManager;
    }
}
