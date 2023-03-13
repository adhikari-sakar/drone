package com.musalasoft.drone.domain.contracts;

public interface BaseRepository<T> {

    T save(T model);
}
