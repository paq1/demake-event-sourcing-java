package org.example.shared.repository;

public interface WriteOnlyRepository<ENTITY, ID> {

    void upsertOne(ENTITY entity, ID id);

}
