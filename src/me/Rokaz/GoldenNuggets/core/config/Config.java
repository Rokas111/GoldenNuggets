package me.Rokaz.GoldenNuggets.core.config;

import me.Rokaz.GoldenNuggets.core.GoldenNuggets;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Config implements IConfig {
    private String configname;
    private YamlConfiguration yaml;
    public Config(String name) {
        this.configname = name;
        this.yaml = YamlConfiguration.loadConfiguration(getFile());
    }

    public String getConfig() {
        return this.configname;
    }
    public File getFile() {
        return new File("plugins//" + GoldenNuggets.PLUGIN_FOLDER+"//" + configname+".yml");
    }
    public boolean setup() {
        if (!getFile().exists()) {
            try {
                getFile().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
    public YamlConfiguration getYaml() {
        return this.yaml;
    }
    public void save() {
        try {
            getYaml().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}