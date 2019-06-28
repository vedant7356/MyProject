package com.example.exploretogether;

public class NewEventsModel {
    private String Title;
    private String Description;
    private String Location;
    private String Date;
    private String Time;
    private String Image;

    public NewEventsModel(){}

    public NewEventsModel( String title, String description, String location,String date,String time,String image) {
        Title = title;
        Description = description;
        Location = location;
        Date=date;
        Time=time;
        Image=image;
    }


    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}

