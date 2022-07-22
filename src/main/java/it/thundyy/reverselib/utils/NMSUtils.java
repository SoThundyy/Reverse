package it.thundyy.reverselib.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.network.chat.ChatMessageType;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

@UtilityClass
public class NMSUtils {
    
    public void sendActionBar(Player player, String message) {
        PacketPlayOutChat chatPacket = new PacketPlayOutChat(new ChatComponentText(message), ChatMessageType.c,
                player.getUniqueId());
        
        sendPacket(player, chatPacket);
    }
    
    public void sendPacket(Player player, Packet<?>... packets) {
        for (Packet<?> packet : packets) {
            ((CraftPlayer) player).getHandle().b.a(packet);
        }
    }
}
