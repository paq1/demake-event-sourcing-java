package org.example.shared.eventsourcing.dispatcher;

import org.example.shared.eventsourcing.handlers.CommandHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class CommandDispatcher {
    private final List<CommandHandler> handlers;

    public CommandDispatcher() {
        handlers = new ArrayList<>();
    }

    public CommandDispatcher add(CommandHandler handler) {
        handlers.add(handler);
        return this;
    }

    public Optional<CommandHandler> get(String name) {
        return handlers
                .stream()
                .filter(p -> p.name().equalsIgnoreCase(name))
                .findFirst();
    }
}
