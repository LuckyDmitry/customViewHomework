package com.example.customviewhomework.repository;

import com.example.customviewhomework.data.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DatabasePerson implements Database<Person>{

    private final Set<Person> mPersonSet = new HashSet<>();


    DatabasePerson(){

        for(Person person : generatePersons(10)){
            insert(person);
        }
    }

    @Override
    public List<Person> retrieveData() {
        return new ArrayList<>(mPersonSet);
    }

    @Override
    public void insert(final Person personEntry) {
        if(personEntry != null){
            mPersonSet.add(personEntry);
        }
    }

    @Override
    public boolean update(final Person oldEntry, final Person newEntry) {

        if(mPersonSet.contains(oldEntry) && !mPersonSet.contains(newEntry)){
            mPersonSet.remove(oldEntry);
            mPersonSet.add(newEntry);
            return true;
        }
        return false;
    }

    private List<Person> generatePersons(int count){
        List<Person> personList = new LinkedList<>();

        for(int i = 0;i < count; ++i){
            personList.add(new Person("Heley" + i));
        }
        return personList;
    }

}
