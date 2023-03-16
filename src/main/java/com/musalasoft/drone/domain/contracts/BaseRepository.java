package com.musalasoft.drone.domain.contracts;

import java.util.List;

public interface BaseRepository<T> {

    T save(T model);

    List<T> findAll();

    void saveAll(List<T> models);
}
