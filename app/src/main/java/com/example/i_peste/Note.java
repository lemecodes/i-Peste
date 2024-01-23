package com.example.i_peste;

public class Note {
    private String title;
    private String description;
    private String date;
    private String email;
    private String id;

    public Note() {
        //empty constructor needed
    }

    public Note(String title, String description, String date, String email, String id) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.email = email;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }
}
