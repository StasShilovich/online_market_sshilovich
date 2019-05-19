package com.gmail.shilovich.stas.jd2.repositorymodule;

import java.sql.Connection;
import java.util.List;

public interface GenericRepository<I, T> {

    Connection getConnection();

    void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    T findById(I id);

    @SuppressWarnings({"unchecked", "rawtypes"})
    List<T> findAll(int offset, int limit);

    int getCountOfEntities();

}
