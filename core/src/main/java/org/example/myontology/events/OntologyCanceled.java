package org.example.myontology.events;

import java.time.Instant;
import java.time.LocalDate;

public record OntologyCanceled(
        String by,
        Instant at
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
