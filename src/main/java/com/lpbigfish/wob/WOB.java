package com.lpbigfish.wob;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class WOB extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("WOB is enabled!");

        getServer().getPluginManager().registerEvents(new WOBListener(this), this);
        saveConfig();
        try {
            getConfig().load("config.yml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
