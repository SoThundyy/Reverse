package it.thundyy.reverselib.pattern;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Getter
public abstract class FlyWeight<K, V> {
    private final Map<K, V> cache = Maps.newHashMap();
    
    public V create(K key, Class<V> clazz) {
        return cache.computeIfAbsent(key, newKey -> {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
