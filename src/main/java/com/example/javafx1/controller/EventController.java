package com.example.javafx1.controller;

import com.example.javafx1.model.Event;
import com.example.javafx1.service.EventService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EventController {
    @FXML private TableView<Event> eventTable;

    @FXML private TableColumn<Event, String> titleCol;
    @FXML private TableColumn<Event, String> locationCol;
    @FXML private TableColumn<Event, java.time.LocalDate> dateCol;

    @FXML private TextField titleField;
    @FXML private TextField locationField;
    @FXML private DatePicker datePicker;
    @FXML private TableColumn<Event, Void> actionCol;

    private final EventService eventService = new EventService();
    private Event selectedEventForUpdate = null;

    @FXML
    public void initialize() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        setupActionButtons();
        loadEvents();
    }

    private void loadEvents() {
        List<Event> events = eventService.getAllEvents();
        System.out.println("Events found: " + events.size());
        for(Event e : events) System.out.println(e);

        eventTable.setItems(FXCollections.observableArrayList(
                eventService.getAllEvents()
        ));
    }

    @FXML
    private void addEvent() {
        Event event = new Event(
                0,
                titleField.getText(),
                locationField.getText(),
                datePicker.getValue()
        );
        eventService.addEvent(event);
        loadEvents();
    }

    private void setupActionButtons() {
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button updateBtn = new Button("Edit");

            {
                updateBtn.setOnAction(event -> {
                    selectedEventForUpdate = getTableView().getItems().get(getIndex());
                    // Fill the fields with existing data
                    titleField.setText(selectedEventForUpdate.getTitle());
                    locationField.setText(selectedEventForUpdate.getLocation());
                    datePicker.setValue(selectedEventForUpdate.getEventDate());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : updateBtn);
            }
        });
    }

    @FXML
    private void saveEvent() {
        if (selectedEventForUpdate == null) {
            Event event = new Event(0, titleField.getText(), locationField.getText(), datePicker.getValue());
            eventService.addEvent(event);
        } else {
            // Logic for UPDATING existing event
            selectedEventForUpdate.setTitle(titleField.getText());
            selectedEventForUpdate.setLocation(locationField.getText());
            selectedEventForUpdate.setEventDate(datePicker.getValue());

            eventService.updateEvent(selectedEventForUpdate);
            selectedEventForUpdate = null; // Reset after update
        }
        clearFields();
        loadEvents();
    }

    private void clearFields() {
        titleField.clear();
        locationField.clear();
        datePicker.setValue(null);
    }



    @FXML
    private void deleteEvent() {
        Event selected = eventTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            eventService.deleteEvent(selected.getId());
            loadEvents();
        }
    }
}
