package org.example.shared.eventsourcing.handlers;

import java.util.Optional;

public interface CreateCommandHandler<COMMAND, EVENT> extends CommandHandler {
    Optional<EVENT> onCommand(String id, COMMAND cmd);
}
