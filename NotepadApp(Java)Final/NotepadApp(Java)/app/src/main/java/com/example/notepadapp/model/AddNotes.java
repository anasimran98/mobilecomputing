package com.example.notepadapp.model;

import static com.example.notepadapp.Utils.Constant.ShowDateTimePickerView;
import static com.example.notepadapp.Utils.Constant.ShowStatusPickerViewScreen;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notepadapp.Fragments.AddReminderActivity;
import com.example.notepadapp.Fragments.NotepadModel;
import com.example.notepadapp.R;
import com.example.notepadapp.Utils.PickerViewClickListener;

import java.util.Date;

import io.realm.Realm;

public class AddNotes extends AppCompatActivity {
    Realm realm =Realm.getDefaultInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        EditText description = (EditText) findViewById(R.id.description);
        EditText day = (EditText) findViewById(R.id.day);
        EditText dateTime = (EditText) findViewById(R.id.date_time);
        Button add = (Button)findViewById(R.id.add_timetable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        day.setClickable(true);
        day.setFocusable(false);
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowStatusPickerViewScreen(AddNotes.this, "Done", new PickerViewClickListener() {
                    @Override
                    public void onDoneClick(String year, String month, String days, boolean isFinish) {

                    }

                    @Override
                    public void onDoneClick(Date status, boolean isFinish) {

                    }

                    @Override
                    public void onDoneClick(String status, boolean isFinish) {
                        day.setText(status);
                    }
                });
            }
        });

        dateTime.setClickable(true);
        dateTime.setFocusable(false);
        dateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDateTimePickerView(AddNotes.this, new PickerViewClickListener() {
                    @Override
                    public void onDoneClick(String year, String month, String days, boolean isFinish) {

                    }

                    @Override
                    public void onDoneClick(Date status, boolean isFinish) {
                        dateTime.setText(status.toString());
                    }

                    @Override
                    public void onDoneClick(String status, boolean isFinish) {

                    }
                });
            }
        });
        add.setOnClickListener(view -> {
            realm.beginTransaction();
            NotepadModel NotepadModel = new NotepadModel();
            Number newId = realm.where(NotepadModel.class).max("id");
            if(newId != null)
               NotepadModel.setId(newId.intValue()+1);
            else
                NotepadModel.setId(1);

            NotepadModel.setDescription(description.getText().toString());
            NotepadModel.setDay(day.getText().toString());
            NotepadModel.setDate(dateTime.getText().toString());
            realm.copyToRealmOrUpdate(NotepadModel);
            realm.commitTransaction();
            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
