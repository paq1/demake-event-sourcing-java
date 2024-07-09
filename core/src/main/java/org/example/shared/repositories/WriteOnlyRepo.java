package org.example.shared.repositories;

public interface WriteOnlyRepo<DATA> {
    void upsert(String id, DATA data);
}


