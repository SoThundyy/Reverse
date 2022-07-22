package it.thundyy.reverselib.serializer.impl;

import it.thundyy.reverselib.serializer.Serializer;
import it.thundyy.reverselib.utils.objects.BetterSound;
import lombok.RequiredArgsConstructor;
import org.bukkit.Sound;

@RequiredArgsConstructor
public class SoundSerializer implements Serializer<BetterSound> {
    private static SoundSerializer instance;
    
    public static SoundSerializer getInstance() {
        synchronized (SoundSerializer.class) {
            if (instance == null) {
                instance = new SoundSerializer();
            }
        }
        
        return instance;
    }
    
    @Override
    public String serialize(BetterSound object) {
        return object.getSoundName() +
                SERIALIZER_SEPARATOR +
                object.getVolume() +
                SERIALIZER_SEPARATOR +
                object.getPitch();
    }
    
    @Override
    public BetterSound deserialize(String object) {
        String[] args = object.split(SERIALIZER_SEPARATOR);
        
        return new BetterSound(
                Sound.valueOf(args[0]),
                Float.parseFloat(args[1]),
                Float.parseFloat(args[2])
        );
    }
}
