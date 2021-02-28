package com.example.customviewhomework.repository;

import java.util.List;

public interface Database<T> {


    List<T> retrieveData();

    void insert(final T entry);

    boolean update(final T oldEntry, final T newEntry);
}
