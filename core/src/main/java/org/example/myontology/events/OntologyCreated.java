package org.example.myontology.events;

import java.time.Instant;

public record OntologyCreated(
        String by,
        Instant at,
        String name
) implements MyOntologyEvents {

    @Override
    public String by() {
        return by;
    }

    @Override
    public Instant at() {
        return at;
    }
}
