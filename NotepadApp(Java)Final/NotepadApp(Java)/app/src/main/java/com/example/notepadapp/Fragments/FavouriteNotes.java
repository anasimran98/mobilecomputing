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
import com.example.notepadapp.model.FavoriteAdpater;
import com.example.notepadapp.model.Notepad_Adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.realm.Realm;
import kotlin.jvm.Synchronized;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteNotes#getInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteNotes extends Fragment {
    RecyclerView recyclerView;
    TextView textView;
    private static FavouriteNotes instance = null;
    @Synchronized
    public static FavouriteNotes getInstance() {
        if(instance == null) instance = new FavouriteNotes();
        return instance;
    }


    @Override
    public void onResume() {
        super.onResume();
        List<FavoriteModel> realmList = Realm.getDefaultInstance().where(FavoriteModel.class).findAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FavoriteAdpater adapter = new FavoriteAdpater (getActivity(), realmList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        textView = (TextView) rootView.findViewById(R.id.textViewempty);
        return rootView;

    }
}