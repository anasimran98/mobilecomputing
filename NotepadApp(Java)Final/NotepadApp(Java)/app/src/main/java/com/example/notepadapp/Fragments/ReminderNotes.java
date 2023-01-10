package com.example.notepadapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notepadapp.R;
import com.example.notepadapp.model.AddNotes;
import com.example.notepadapp.model.Notepad_Adapter;
import com.example.notepadapp.model.Reminders_Adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.realm.Realm;
import kotlin.jvm.Synchronized;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReminderNotes#getInstance} factory method to
 * create an instance of this fragment.
 */
public class ReminderNotes extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton add_notes;
    TextView textView;
    private static ReminderNotes instance = null;
    @Synchronized
    public static ReminderNotes getInstance() {
        if(instance == null) instance = new ReminderNotes();
        return instance;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<RemindersModel> realmList = Realm.getDefaultInstance().where(RemindersModel.class).findAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Reminders_Adapter adapter = new Reminders_Adapter (getActivity(), realmList);
        add_notes.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AddReminderActivity.class);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reminders, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        add_notes = (FloatingActionButton) rootView.findViewById(R.id.add_notes);
        textView = (TextView) rootView.findViewById(R.id.textViewempty);
        return rootView;

    }
}