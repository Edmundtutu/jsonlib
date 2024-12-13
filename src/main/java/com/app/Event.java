package com.app;

import java.time.LocalDateTime;

public class Event {
    private String id;
    private String title;
    private LocalDateTime dateTime;

    // Default constructor
    public Event() {}

    // Constructor with basic fields
    public Event(String id, String title) {
        this.id = id;
        this.title = title;
        this.dateTime = LocalDateTime.now();
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
} 