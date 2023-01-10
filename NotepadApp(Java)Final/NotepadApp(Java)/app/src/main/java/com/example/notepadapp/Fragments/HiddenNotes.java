package com.example.notepadapp.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.notepadapp.R;
import com.example.notepadapp.model.FavoriteAdpater;
import com.example.notepadapp.model.HiddenAdapter;

import java.util.List;

import io.realm.Realm;
import kotlin.jvm.Synchronized;

public class HiddenNotes extends Fragment {
    RecyclerView recyclerView;
    TextView textView;
    EditText editTextpassword;
    Button submit;
    LinearLayout layout;
    private static HiddenNotes instance = null;
    @Synchronized
    public static HiddenNotes getInstance() {
        if(instance == null) instance = new HiddenNotes();
        return instance;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hidden_notes, container, false);

        editTextpassword = (EditText) rootView.findViewById(R.id.passwordeditext);
        submit = (Button) rootView.findViewById(R.id.verifybutton);
        layout = (LinearLayout) rootView.findViewById(R.id.linearmainview);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        layout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextpassword.getText().toString().equalsIgnoreCase("Password1")){
                    editTextpassword.setText("");
                    layout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    List<FavoriteModel> realmList = Realm.getDefaultInstance().where(FavoriteModel.class).findAll();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    HiddenAdapter adapter = new HiddenAdapter (getActivity(), realmList);
                    recyclerView.setAdapter(adapter);

                }else{
                    return;
                }
            }
        });
        return rootView;

    }
}