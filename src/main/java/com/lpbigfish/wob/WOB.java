package com.lpbigfish.wob;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public final class WOB extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new WOBListener(this), this);
        saveConfig();


        try {
            File config = new File(getServer().getWorldContainer().getAbsolutePath(), "config/paper-global.yml");
            FileConfiguration configYaml = YamlConfiguration.loadConfiguration(config);
            configYaml.set("unsupported-settings.allow-headless-pistons", true);
            configYaml.set("unsuported-settings.allow-permanent-block-break-exploits", true);
            configYaml.set("unsupported-settings.allow-piston-duplication", true);
            configYaml.save(config);

            config = new File(getServer().getWorldContainer().getAbsolutePath(), "spigot.yml");
            configYaml = YamlConfiguration.loadConfiguration(config);
            configYaml.set("world-settings.default.mob-spawn-range", 4);
            configYaml.save(config);

            config = new File(getServer().getWorldContainer().getAbsolutePath(), "plugins/IllegalStack/config.yml");
            configYaml = YamlConfiguration.loadConfiguration(config);
            configYaml.set("Exploits.TNTDupe.PreventIndirectTNTPowerDupe", false);
            configYaml.save(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Exploits.TNTDupe.PreventIndirectTNTPowerDupe, false

    }
}
