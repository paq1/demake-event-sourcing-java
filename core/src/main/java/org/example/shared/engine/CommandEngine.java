package org.example.shared.engine;

import org.example.shared.commandHandler.CommandHandlerCreate;
import org.example.shared.commandHandler.CommandHandlerUpdate;
import org.example.shared.reducer.Reducer;
import org.example.shared.repositories.Repo;

import java.util.Optional;
import java.util.UUID;

public class CommandEngine<STATES, COMMANDS, EVENTS> {

    private Repo<STATES> store;
    private Repo<EVENTS> journal;
    private CommandDispatcher dispatcher;
    private Reducer<STATES, EVENTS> reducer;

    public CommandEngine(
            Repo<STATES> store,
            Repo<EVENTS> journal,
            CommandDispatcher dispatcher,
            Reducer<STATES, EVENTS> reducer
    ) {
        this.store = store;
        this.journal = journal;
        this.dispatcher = dispatcher;
        this.reducer = reducer;
    }

    void compute(COMMANDS command, String entityId, String handlerName) {
        var handler = dispatcher.getHandler(handlerName).get();
        Optional<STATES> maybeState = store.fetchOne(entityId);
        Optional<EVENTS> maybeEvent = switch (handler) {
            case CommandHandlerCreate createHandler -> createHandler.onCommand(command, entityId);
            case CommandHandlerUpdate updateHandler -> updateHandler.onCommand(command, maybeState.get(), entityId);
            default -> Optional.empty();
        };
        EVENTS event = maybeEvent.get();
        STATES newState = reducer.reduce(maybeState, event).get();
        journal.upsert(UUID.randomUUID().toString(), event);
        store.upsert(entityId, newState);
    }


    // todo def une methode permettant de "compute" (sauvegarde en db)
}
