package org.example.shared.reducer;

import java.util.Optional;

public interface Reducer<STATE, EVENT> {
    Optional<STATE> reduce(Optional<STATE> state, EVENT event);
}
