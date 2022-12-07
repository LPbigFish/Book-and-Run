package com.lpbigfish.wob;

import org.bukkit.plugin.java.JavaPlugin;


public final class WOB extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new WOBListener(this), this);
        saveDefaultConfig();



       /* try {
            File config = new File(getServer().getWorldContainer().getAbsolutePath(), "spigot.yml");
            FileConfiguration configYaml = YamlConfiguration.loadConfiguration(config);
            configYaml.set("world-settings.default.mob-spawn-range", 4);
            configYaml.save(config);
            try {
                config = new File(getServer().getWorldContainer().getAbsolutePath(), "config/paper-global.yml");
                if (config.exists()) {
                    configYaml = YamlConfiguration.loadConfiguration(config);
                    configYaml.set("unsupported-settings.allow-headless-pistons", true);
                    configYaml.set("unsupported-settings.allow-permanent-block-break-exploits", true);
                    configYaml.set("unsupported-settings.allow-piston-duplication", true);
                    configYaml.save(config);
                }
            } catch (Exception ignored) {}
            try {
                config = new File(getServer().getWorldContainer().getAbsolutePath(), "plugins/IllegalStack/config.yml");
                if (config.exists()) {
                    configYaml = YamlConfiguration.loadConfiguration(config);
                    configYaml.set("Exploits.TNTDupe.PreventIndirectTNTPowerDupe", false);
                    configYaml.save(config);
                }
            } catch (Exception ignored) {
            }
            try {
                config = new File(getServer().getWorldContainer().getAbsolutePath(), "purpur.yml");
                if (config.exists()) {
                    configYaml = YamlConfiguration.loadConfiguration(config);
                    configYaml.set("world-settings.default.blocks.sand.fix-duping", false);
                    configYaml.set("world-settings.default.blocks.end_portal.safe-teleporting", false);
                    configYaml.save(config);
                }
            } catch (Exception ignored) {
            }
        } catch (Exception ignored) {

        }*/

    }
}
