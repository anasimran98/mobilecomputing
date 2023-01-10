package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

class MyClass2 {
    // This is a non-static field
    private int id;

    // This is a non-static method
    public void setId(int id) {
        this.id = id;
    }


    public class NotepadModel extends RealmObject {
        @PrimaryKey
        private String title;
        private int id;
        private String date;
        private String description;
        private String day;
        private String time;

        public int getId() {
            return id;
        }

        public static void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}


