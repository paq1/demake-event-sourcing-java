package org.example.shared.repository;

import java.util.List;
import java.util.Optional;

public interface ReadOnlyRepository<ENTITY, ID> {

    Optional<ENTITY> findOne(ID id);
    List<ENTITY> findAll();

}
