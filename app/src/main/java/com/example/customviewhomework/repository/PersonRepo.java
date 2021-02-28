package com.example.customviewhomework.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.customviewhomework.data.Person;

import java.util.List;

public class PersonRepo {

    private final MutableLiveData<List<Person>> mPersonsLiveData = new MutableLiveData<>();
    Database<Person> mPersonDatabase = new DatabasePerson();

    public PersonRepo() {
        setPersonValue(mPersonDatabase.retrieveData());
    }

    private void setPersonValue(List<Person> personsList) {
        mPersonsLiveData.setValue(personsList);
    }

    public MutableLiveData<List<Person>> getPersonsList() {
        return mPersonsLiveData;
    }

    public void insert(Person person) {
        mPersonDatabase.insert(person);
    }

    public boolean update(Person oldEntry, Person newEntry) {
        return mPersonDatabase.update(oldEntry, newEntry);
    }
}
