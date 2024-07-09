package org.example.shared.repositories;

public interface Repo<DATA> extends ReadOnlyRepo<DATA>, WriteOnlyRepo<DATA> { }
