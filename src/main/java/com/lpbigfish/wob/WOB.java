package com.lpbigfish.wob;

import org.bukkit.plugin.java.JavaPlugin;

public final class WOB extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("WOB is enabled!");

        getServer().getPluginManager().registerEvents(new WOBListener(this), this);
        saveConfig();
    }
}
