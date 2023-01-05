package model;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notepadapp.R;

import io.realm.Realm;

class MyClass {
    // Make the setID method static
    public static void setID(int id) {
        // Method code goes here
    }
}


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

        add.setOnClickListener(view -> {
            realm.beginTransaction();
            NotepadModel NotepadModel = new NotepadModel();
            Number newId = realm.where(NotepadModel.class).max("id");
            if(newId != null)
                model.NotepadModel.setId(newId.intValue()+1);
            else
                model.NotepadModel.setId(1);

            NotepadModel.setDescription(description.getText().toString());
            NotepadModel.setDay(day.getText().toString());
            NotepadModel.setDate(dateTime.getText().toString());
            realm.copyToRealmOrUpdate(NotepadModel);
            realm.commitTransaction();
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
