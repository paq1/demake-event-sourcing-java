package org.example.myontology.states;

import org.example.myontology.events.MyOntologyEvents;
import org.example.myontology.events.OntologyCanceled;

import java.util.Optional;

public record OntologyActifState(String name) implements MyOntologyStates {

    public Optional<MyOntologyStates> reduce(MyOntologyEvents event) {
        return switch (event) {
            case OntologyCanceled canceled -> onCancel(canceled);
            default -> Optional.empty();
        };
    }

    public Optional<MyOntologyStates> onCancel(OntologyCanceled c) {
        return Optional.of(new OntologyCancelState());
    }
}
