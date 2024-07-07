package org.example.myontology.commandHandlers;

import org.example.myontology.CancelCommand;
import org.example.myontology.events.MyOntologyEvents;
import org.example.myontology.events.OntologyCanceled;
import org.example.myontology.states.MyOntologyStates;
import org.example.shared.eventsourcing.handlers.UpdateCommandHandler;

import java.time.Instant;
import java.util.Optional;

public class CancelMyOntology implements UpdateCommandHandler<MyOntologyStates, CancelCommand, MyOntologyEvents> {
    public final static String NAME = "update-ontology";

    @Override
    public Optional<MyOntologyEvents> onCommand(String id, CancelCommand cmd, MyOntologyStates currentState) {
        return Optional.of(new OntologyCanceled("usr:bot", Instant.now()));
    }

    @Override
    public String name() {
        return NAME;
    }
}
