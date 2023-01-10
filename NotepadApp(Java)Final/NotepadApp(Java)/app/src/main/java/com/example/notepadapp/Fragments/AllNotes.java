package com.example.notepadapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepadapp.R;
import com.example.notepadapp.model.AddNotes;
import com.example.notepadapp.model.Notepad_Adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.realm.Realm;
import kotlin.jvm.Synchronized;

public class AllNotes extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_notes;
    TextView textView;
    //Making Singleton Object For class
    private AllNotes(){}
    private static AllNotes instance = null;
    @Synchronized
    public static AllNotes getInstance() {
        if(instance == null) instance = new AllNotes();
        return instance;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<NotepadModel> realmList = Realm.getDefaultInstance().where(NotepadModel.class).findAll();
        if(realmList.size() >0){
            textView.setVisibility(View.GONE);
        }else{
            textView.setVisibility(View.VISIBLE);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Notepad_Adapter adapter = new Notepad_Adapter (getActivity(), realmList);
        add_notes.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AddNotes.class);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_note, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        add_notes = (FloatingActionButton) rootView.findViewById(R.id.add_notes);
        textView = (TextView) rootView.findViewById(R.id.textViewempty);
        return rootView;

    }
}