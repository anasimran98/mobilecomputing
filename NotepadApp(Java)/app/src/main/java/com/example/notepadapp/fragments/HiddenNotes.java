package com.example.notepadapp.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notepadapp.R;

import kotlin.jvm.Synchronized;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HiddenNotes#getInstance} factory method to
 * create an instance of this fragment.
 */
public class HiddenNotes extends Fragment {

    private static HiddenNotes instance = null;
    @Synchronized
    public static HiddenNotes getInstance() {
        if(instance == null) instance = new HiddenNotes();
        return instance;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hidden_notes, container, false);
    }
}