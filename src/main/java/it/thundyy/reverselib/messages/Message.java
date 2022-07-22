package it.thundyy.reverselib.messages;

import it.thundyy.reverselib.messages.enums.MessageType;
import it.thundyy.reverselib.serializer.impl.SoundSerializer;
import it.thundyy.reverselib.utils.ChatUtils;
import it.thundyy.reverselib.utils.NMSUtils;
import it.thundyy.reverselib.utils.objects.BetterSound;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Message {
    private String path;
    
    @SuppressWarnings("unchecked")
    public void send(FileConfiguration configuration, CommandSender sender, Placeholder... placeholders) {
        Object execute = configuration.get(path);
        if (execute == null) return;
        
        // if the path contains a list, iterate over it and execute each message for its own type
        if (execute instanceof List<?>) {
            ((List<String>) execute).forEach(stringExecute -> {
                String firstExecuteElement = stringExecute.split(" ")[0];
                for (Placeholder placeholder : placeholders) {
                    stringExecute = stringExecute.replace(placeholder.getKey(), placeholder.getValue());
                }
                
                for (MessageType type : MessageType.values()) {
                    if (!type.stringIsPrefix(firstExecuteElement)) {
                        executeTypeString(sender, stringExecute, MessageType.SEND_MESSAGE);
                        continue;
                    }
                    
                    executeTypeString(sender, stringExecute, type);
                }
            });
            return;
        }
        
        // if the path contains a string, execute it for each type
        String stringExecute = (String) execute;
        for (Placeholder placeholder : placeholders) {
            stringExecute = stringExecute.replace(placeholder.getKey(), placeholder.getValue());
        }
        
        for (MessageType type : MessageType.values()) {
            if (!type.stringIsPrefix(stringExecute)) {
                executeTypeString(sender, stringExecute, MessageType.SEND_MESSAGE);
                continue;
            }
            
            executeTypeString(sender, stringExecute, type);
            break;
        }
    }
    
    private void executeTypeString(CommandSender sender, String toBeExecuted, MessageType messageType) {
        toBeExecuted = toBeExecuted.replace(messageType.getPrefix(), "");
        String colorizedExecute = ChatUtils.color(toBeExecuted);
        
        switch (messageType) {
            case SEND_MESSAGE -> sender.sendMessage(colorizedExecute);
            case SEND_SOUND -> {
                if (!(sender instanceof Player player)) return;
                
                BetterSound betterSound = SoundSerializer.getInstance().deserialize(toBeExecuted);
                betterSound.playSound(player);
            }
            case SEND_ACTION_BAR -> NMSUtils.sendActionBar((Player) sender, colorizedExecute);
            case PERFORM_COMMAND -> ((Player) sender).performCommand(toBeExecuted);
            case DISPATCH_COMMAND -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), toBeExecuted);
        }
    }
}
