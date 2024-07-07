package org.example.myontology.commandHandlers;

import org.example.myontology.CreateCommand;
import org.example.myontology.events.MyOntologyEvents;
import org.example.myontology.events.OntologyCreated;
import org.example.shared.eventsourcing.handlers.CreateCommandHandler;

import java.time.Instant;
import java.util.Optional;

public class CreateMyOntology implements CreateCommandHandler<CreateCommand, MyOntologyEvents> {

    public static String NAME = "create-ontology";

    @Override
    public Optional<MyOntologyEvents> onCommand(String id, CreateCommand cmd) {
        return Optional.of(new OntologyCreated("usr:bot", Instant.now(), cmd.name()));
    }

    @Override
    public String name() {
        return NAME;
    }
}
