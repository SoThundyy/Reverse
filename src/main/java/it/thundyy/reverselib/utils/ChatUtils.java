package it.thundyy.reverselib.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.util.List;

@UtilityClass
public class ChatUtils {
    
    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public List<String> color(List<String> message) {
        message.replaceAll(ChatUtils::color);
        return message;
    }
    
}
