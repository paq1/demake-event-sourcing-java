package org.example.myOntology.repository;

import org.example.shared.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MockRepository<ENTITY, ID> implements Repository<ENTITY, ID> {

    private Map<ID, ENTITY> entities;

    public MockRepository() {
        entities = new HashMap<>();
    }

    @Override
    public Optional<ENTITY> findOne(ID id) {
        try {
            var value = entities.get(id);
            if (value == null) { return Optional.empty(); }
            return Optional.of(entities.get(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<ENTITY> findAll() {
        return entities.values().stream().toList();
    }

    @Override
    public void upsertOne(ENTITY entity, ID id) {
        entities.put(id, entity);
    }
}
