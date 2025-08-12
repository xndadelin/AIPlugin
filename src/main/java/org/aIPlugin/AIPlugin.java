package org.aIPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class AIPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("AI Plugin is enabled! yey!");
        this.getCommand("ask").setExecutor(new AICommand());

    }

    @Override
    public void onDisable() {
        getLogger().info("AI Plugin is disabled! nooo!");
    }
}
