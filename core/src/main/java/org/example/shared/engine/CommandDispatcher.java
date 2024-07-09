package org.example.shared.engine;

import org.example.shared.commandHandler.CommandHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandDispatcher {

    private final List<CommandHandler> handlers;

    public CommandDispatcher() {
        handlers = new ArrayList<>();
    }

    public CommandDispatcher add(CommandHandler handler) {
        handlers.add(handler);
        return this;
    }

    public Optional<CommandHandler> getHandler(String name) {
        return handlers
                .stream()
                .filter(current -> current.name().equalsIgnoreCase(name))
                .findFirst();
    }
}
