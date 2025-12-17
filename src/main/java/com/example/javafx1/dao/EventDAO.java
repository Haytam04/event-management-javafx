package com.example.javafx1.dao;

import com.example.javafx1.config.DatabaseConfig;
import com.example.javafx1.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    public List<Event> findAll() throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events";

        try (Connection con = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                events.add(new Event(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("location"),
                        rs.getDate("event_date").toLocalDate()
                ));
            }
        }
        return events;
    }

    public void save(Event event) throws SQLException {
        String sql = "INSERT INTO events(title, location, event_date) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, event.getTitle());
            ps.setString(2, event.getLocation());
            ps.setDate(3, Date.valueOf(event.getEventDate()));
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM events WHERE id=?";

        try (Connection con = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void update(Event event) throws SQLException {
        String sql = "UPDATE events SET title=?, location=?, event_date=? WHERE id=?";

        try (Connection con = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, event.getTitle());
            ps.setString(2, event.getLocation());
            ps.setDate(3, java.sql.Date.valueOf(event.getEventDate()));
            ps.setInt(4, event.getId());
            ps.executeUpdate();
        }
    }
}
