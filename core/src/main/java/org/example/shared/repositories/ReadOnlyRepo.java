package org.example.shared.repositories;

import java.util.List;
import java.util.Optional;

public interface ReadOnlyRepo<DATA> {
    Optional<DATA> fetchOne(String id);
    List<DATA> fetchAll();
}

