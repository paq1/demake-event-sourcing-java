package org.example.shared.repository;

public interface Repository<ENTITY, ID>
        extends ReadOnlyRepository<ENTITY, ID>, WriteOnlyRepository<ENTITY, ID> {
}
