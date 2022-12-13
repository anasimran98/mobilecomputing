package com.example.notepadapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.notepadapp.R;

import kotlin.jvm.Synchronized;

public class AllNotes extends Fragment {

    //Making Singleton Object For class
    private AllNotes(){}
    private static AllNotes instance = null;
    @Synchronized
    public static AllNotes getInstance() {
        if(instance == null) instance = new AllNotes();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_note, container, false);

    }
}