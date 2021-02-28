package com.example.customviewhomework.data;

import androidx.annotation.NonNull;

public class Person {

    private String name;
    public Person(@NonNull String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
