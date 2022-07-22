package it.thundyy.reverselib.messages.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MessageType {
    SEND_MESSAGE("[player]"),
    SEND_SOUND("[sound]"),
    SEND_TITLE("[title]"),
    SEND_ACTION_BAR("[actionbar]"),
    PERFORM_COMMAND("[player-command]"),
    DISPATCH_COMMAND("[command]");
    
    private final String prefix;
    
    public boolean stringIsPrefix(String string) {
        return string.startsWith(prefix);
    }
}
