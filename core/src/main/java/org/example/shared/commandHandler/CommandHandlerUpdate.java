package org.example.shared.commandHandler;

import java.util.Optional;

public interface CommandHandlerUpdate<STATE, COMMAND, EVENT> extends CommandHandler {

    Optional<EVENT> onCommand(COMMAND cmd, STATE state, String id);
}