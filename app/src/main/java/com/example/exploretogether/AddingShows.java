package com.example.exploretogether;

public class AddingShows {
    String Show_id;
    String Title;
    String Description;
    String Location;
    String Date;
    String Time;
    String Organizer;
    String Guest;

    public AddingShows() {


    }

    public AddingShows(String show_id, String title, String description, String location, String date, String time, String organizer, String guest) {
        Show_id = show_id;
        Title = title;
        Description = description;
        Location = location;
        Date = date;
        Time = time;
        Organizer = organizer;
        Guest = guest;
    }

    public void setShow_id(String show_id) {
        Show_id = show_id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setOrganizer(String organizer) {
        Organizer = organizer;
    }

    public void setGuest(String guest) {
        Guest = guest;
    }
}