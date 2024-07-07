package org.example.shared.eventsourcing.engine;

import org.example.shared.eventsourcing.Reducer;
import org.example.shared.eventsourcing.dispatcher.CommandDispatcher;
import org.example.shared.eventsourcing.data.Event;
import org.example.shared.eventsourcing.handlers.CommandHandler;
import org.example.shared.eventsourcing.handlers.CreateCommandHandler;
import org.example.shared.eventsourcing.handlers.UpdateCommandHandler;
import org.example.shared.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public class SimpleCommandEngine<STATE, COMMAND, EVENT> {

    private final CommandDispatcher dispatcher;
    private final Repository<STATE, String> store;
    private final Repository<EVENT, String> journal;
    private final Reducer<STATE, EVENT> reducer;

    public SimpleCommandEngine(
            CommandDispatcher dispatcher,
            Repository<STATE, String> store,
            Repository<EVENT, String> journal,
            Reducer<STATE, EVENT> reducer
    ) {
        this.dispatcher = dispatcher;
        this.store = store;
        this.journal = journal;
        this.reducer = reducer;
    }

    public Optional<Event<EVENT, STATE>> computeAndSave(
            COMMAND command,
            String entityId,
            String handlerName
    ) {
        var maybeEvent = computeEventState(command, entityId, handlerName);

        if (maybeEvent.isPresent()) {
            var event = maybeEvent.get();
            journal.upsertOne(event.event(), event.eventId());
            store.upsertOne(event.state(), event.entityId());
            return  maybeEvent;
        } else {
            return Optional.empty();
        }
    }


    public Optional<Event<EVENT, STATE>> computeEventState(
            COMMAND command,
            String entityId,
            String handlerName
    ) {
        var maybestate = store.findOne(entityId);

        var maybeevent = computeEvent(command, entityId, maybestate, handlerName);


        if (maybeevent.isPresent()) {
            var event = maybeevent.get();
            String eventId = UUID.randomUUID().toString();

            Optional<STATE> maybenewState = reducer.reduce(maybestate, event);

            if (maybenewState.isPresent()) {
                var newState = maybenewState.get();
                return Optional.of(
                        new Event<EVENT, STATE>(entityId, eventId, event, newState)
                );
            } else {
                // erreur
                return Optional.empty();
            }
        } else {
            // erreur
            return Optional.empty();
        }
    }

    private Optional<EVENT> computeEvent(
            COMMAND command,
            String entityId,
            Optional<STATE> maybeState,
            String handlerName
    ) {
        var maybehandler = dispatcher.get(handlerName);

        return maybehandler.flatMap((CommandHandler handler) -> {

            return switch (handler) {
                case CreateCommandHandler c -> {
                    Optional<EVENT> createEvent = c.onCommand(entityId, command);
                    yield createEvent;
                }
                case UpdateCommandHandler u -> maybeState.flatMap(state -> u.onCommand(entityId, command, state));
                default -> Optional.empty();
            };
        });
    }

}
