package it.thundyy.reverselib.utils.objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@Getter
public class BetterSound {
    private final Sound sound;
    private final float volume, pitch;
    
    public String getSoundName() {
        return sound.getKey().getNamespace();
    }
    
    public void playSound(Player player) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }
}
