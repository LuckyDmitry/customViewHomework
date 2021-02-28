package com.example.customviewhomework.repository;

import com.example.customviewhomework.data.Person;

import java.util.List;

public interface Database<T> {


    List<T> retrieveData();

    void insert(final T entry);

    boolean update(final T oldEntry, final T newEntry);
}
