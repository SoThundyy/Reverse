package it.thundyy.reverselib.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@UtilityClass
public class ItemUtils {
    
    @SuppressWarnings("ConstantConditions")
    public boolean hasPersistentData(JavaPlugin plugin, ItemStack itemStack, String key) {
        return itemStack.hasItemMeta() &&
                itemStack.getItemMeta().getPersistentDataContainer()
                        .has(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }
    
    @SuppressWarnings("ConstantConditions")
    public void setPersistentData(JavaPlugin plugin, ItemStack itemStack, String key, String value) {
        if (itemStack.hasItemMeta()) {
            itemStack.getItemMeta().getPersistentDataContainer()
                    .set(new NamespacedKey(plugin, key), PersistentDataType.STRING, value);
        }
    }
    
    @SuppressWarnings("ConstantConditions")
    public void removePersistentData(JavaPlugin plugin, ItemStack itemStack, String key) {
        if (itemStack.hasItemMeta()) {
            itemStack.getItemMeta().getPersistentDataContainer()
                    .remove(new NamespacedKey(plugin, key));
        }
    }
    
    public void setLore(ItemStack itemStack, String... lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return;
        
        itemMeta.getLore().clear();
        itemMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
    }
    
    public void addLore(ItemStack itemStack, String... lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return;
        
        itemMeta.getLore().addAll(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
    }
}
