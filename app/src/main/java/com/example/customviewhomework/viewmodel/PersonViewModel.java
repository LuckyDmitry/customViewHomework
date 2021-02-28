package com.example.customviewhomework.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.customviewhomework.data.Person;
import com.example.customviewhomework.repository.PersonRepo;

import java.util.List;

public class PersonViewModel extends ViewModel {


    private final PersonRepo mPersonRepository = new PersonRepo();


    public LiveData<List<Person>> getPersonData() {
        return mPersonRepository.getPersonsList();
    }

    public void addPerson(Person person) {
        mPersonRepository.insert(person);
    }

    public boolean updatePerson(Person oldEntry, Person newEntry) {
        return mPersonRepository.update(oldEntry, newEntry);  // for showing a toast
    }

}
