package com.example.notepadapp;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.notepadapp.fragments.FavouriteNotes;
import com.example.notepadapp.fragments.HiddenNotes;
import com.example.notepadapp.fragments.ReminderNotes;
import com.google.android.material.navigation.NavigationView;

import com.example.notepadapp.fragments.AllNotes;

public class MainActivity extends AppCompatActivity {
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerlayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragments(AllNotes.getInstance());
        intViews();
        intDrawer();
    }

    private void intViews() {
        drawerlayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

    }

    private void intDrawer() {
        
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, R.string.nav_open, R.string.nav_close);
        drawerlayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            navView.setNavigationItemSelectedListener(menuItem -> {
                int id=menuItem.getItemId();
                if(id == R.id.all_notes)
                    replaceFragments(AllNotes.getInstance());
                else if(id == R.id.favourites)
                    replaceFragments(FavouriteNotes.getInstance());
                else if(id == R.id.hidden)
                    replaceFragments(HiddenNotes.getInstance());
                else if(id == R.id.reminder)
                    replaceFragments(ReminderNotes.getInstance());


                drawerlayout.closeDrawer(GravityCompat.START);
                return true;
            });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragments(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }

}

