package org.example.myontology.reducer;

import org.example.myontology.events.MyOntologyEvents;
import org.example.myontology.events.OntologyCanceled;
import org.example.myontology.events.OntologyCreated;
import org.example.myontology.states.MyOntologyStates;
import org.example.myontology.states.OntologyActifState;
import org.example.myontology.states.OntologyCancelState;
import org.example.shared.eventsourcing.Reducer;

import java.util.Optional;

public class MyOntologyReducer implements Reducer<MyOntologyStates, MyOntologyEvents> {
    @Override
    public Optional<MyOntologyStates> reduce(Optional<MyOntologyStates> myOntologyStates, MyOntologyEvents myOntologyEvents) {
        if (myOntologyStates.isPresent()) {
            var currentState = myOntologyStates.get();
            return switch (currentState) {
                case OntologyActifState active -> {
                    yield switch (myOntologyEvents) {
                        case OntologyCanceled canceled -> Optional.of(new OntologyCancelState());
                        default -> Optional.empty();
                    };
                }
                default -> Optional.empty();
            };
        } else {
            return switch (myOntologyEvents) {
                case OntologyCreated event -> Optional.of(new OntologyActifState(event.name()));
                default -> Optional.empty();
            };
        }
    }
}
