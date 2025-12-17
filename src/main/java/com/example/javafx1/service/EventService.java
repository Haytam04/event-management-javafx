package com.example.javafx1.service;

import com.example.javafx1.dao.EventDAO;
import com.example.javafx1.model.Event;

import java.sql.SQLException;
import java.util.List;

public class EventService {
    private final EventDAO eventDAO = new EventDAO();

    public List<Event> getAllEvents() {
        try {
            return eventDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addEvent(Event event) {
        try {
            eventDAO.save(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEvent(int id) {
        try {
            eventDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEvent(Event event) {
        try {
            eventDAO.update(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
