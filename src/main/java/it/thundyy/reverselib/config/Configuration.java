package it.thundyy.reverselib.config;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public class Configuration extends YamlConfiguration {
    private final String fileName;
    private final File file;
    
    public Configuration(JavaPlugin plugin, String fileName) {
        this.fileName = fileName;
        
        file = new File(fileName + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                plugin.saveResource(fileName + ".yml", false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            reload();
        }
    }
    
    @SneakyThrows
    public void save() {
        save(file);
    }
    
    public void reload() {
        loadConfiguration(file);
    }
}
