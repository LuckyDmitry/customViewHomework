package com.example.customviewhomework.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customviewhomework.R;
import com.example.customviewhomework.data.Person;
import com.example.customviewhomework.ui.MyCustomView;

import java.util.ArrayList;
import java.util.List;

public class AdapterPerson extends RecyclerView.Adapter<AdapterPerson.PersonViewHolder> {


    private final List<Person> mPersonsList = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;

    public AdapterPerson(Context context, List<Person> people) {
        mLayoutInflater = LayoutInflater.from(context);
        mPersonsList.addAll(people);
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = mPersonsList.get(position);
        holder.myCustomView.setText(person.getName());
    }

    @Override
    public int getItemCount() {
        return mPersonsList.size();
    }

    public void setPersonsList(List<Person> newList) {
        mPersonsList.clear();
        mPersonsList.addAll(newList);
        notifyDataSetChanged();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {

        private final MyCustomView myCustomView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            myCustomView = itemView.findViewById(R.id.item_person__mcv_personName);
        }
    }
}
