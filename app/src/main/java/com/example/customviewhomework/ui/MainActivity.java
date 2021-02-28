package com.example.customviewhomework.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.customviewhomework.R;
import com.example.customviewhomework.adapters.AdapterPerson;
import com.example.customviewhomework.data.Person;
import com.example.customviewhomework.viewmodel.PersonViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PersonViewModel personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.activity_main__rv_listOfPerson);
        AdapterPerson adapterPerson = new AdapterPerson(MainActivity.this, personViewModel.getPersonData().getValue() );
        recyclerView.setAdapter(adapterPerson);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        personViewModel.getPersonData().observe(this, adapterPerson::setPersonsList);

    }
}