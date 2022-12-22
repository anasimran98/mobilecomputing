package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NotepadModel extends RealmObject {
    @PrimaryKey
    private String title;
    private String name;
    private String description;
    private String day;
    private String time;
    private String year;
    


}
