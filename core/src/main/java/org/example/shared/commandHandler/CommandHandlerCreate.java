package org.example.shared.commandHandler;

import java.util.Optional;

public interface CommandHandlerCreate<COMMAND, EVENT> extends CommandHandler {

    Optional<EVENT> onCommand(COMMAND cmd, String id);
}
