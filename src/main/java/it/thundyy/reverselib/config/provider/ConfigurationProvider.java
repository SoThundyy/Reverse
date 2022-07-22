package it.thundyy.reverselib.config.provider;

import com.google.common.collect.Maps;
import it.thundyy.reverselib.config.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class ConfigurationProvider {
    private final Map<String, Configuration> configurationCache = Maps.newHashMap();
    
    public Configuration getConfiguration(JavaPlugin plugin, String fileName) {
        return configurationCache.computeIfAbsent(fileName, newFileName ->
                new Configuration(plugin, fileName));
    }
    
    public void addConfiguration(Configuration configuration) {
        configurationCache.put(configuration.getFileName(), configuration);
    }
    
    public void saveConfiguration(Configuration configuration) {
        configuration.save();
    }
    
    public void saveAll() {
        configurationCache.values().forEach(Configuration::save);
    }
    
    public void reloadAll() {
        configurationCache.values().forEach(Configuration::reload);
    }
    
}
