package com.example.javafx1.model;

import java.time.LocalDate;

public class Event {
    private int id;
    private String title;
    private String location;
    private LocalDate eventDate;

    // constructors
    public Event() {}

    public Event(int id, String title, String location, LocalDate eventDate) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.eventDate = eventDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }
}
