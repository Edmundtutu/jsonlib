package com.app;

import com.app.utils.JsonUtil;
import com.app.utils.WriteOptions;
import java.time.LocalDateTime;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * Demonstration class for the JSON Library.
 * This class provides examples of how to use the various features
 * of the JSON library.
 * 
 * @author Edmund Tutuma
 * @version 1.0
 * @since 2024-03-19
 */
public class Main {
    /**
     * Demonstrates various features of the JSON library.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        demonstrateBasicSerialization();
        demonstrateDeserialization();
        demonstrateFileOperations();
        demonstrateCollections();
        demonstrateErrorHandling();
    }

    private static void demonstrateBasicSerialization() {
        System.out.println("\n=== Basic Serialization ===");
        Event event = new Event();
        event.setId("evt-001");
        event.setTitle("Health Screening");
        event.setDateTime(LocalDateTime.now());
        
        String json = JsonUtil.toJson(event);
        System.out.println("Serialized JSON:");
        System.out.println(json);
    }

    private static void demonstrateDeserialization() {
        System.out.println("\n=== Deserialization ===");
        String json = "{\"id\":\"evt-001\",\"title\":\"Health Screening\"}";
        Event event = JsonUtil.fromJson(json, Event.class);
        System.out.println("Deserialized event title: " + event.getTitle());
    }

    private static void demonstrateFileOperations() {
        System.out.println("\n=== File Operations ===");
        Event event = new Event();
        event.setId("evt-002");
        event.setTitle("Annual Checkup");

        try {
            // Write to file with pretty printing
            WriteOptions options = new WriteOptions().setPrettyPrint(true);
            try (FileOutputStream fos = new FileOutputStream("event.json")) {
                JsonUtil.toJsonFile(fos, event, options);
                System.out.println("Event written to file successfully");
            }

            // Read from file
            try (FileInputStream fis = new FileInputStream("event.json")) {
                Event readEvent = JsonUtil.fromJsonFile(fis, Event.class);
                System.out.println("Event read from file: " + readEvent.getTitle());
            }
        } catch (IOException e) {
            System.err.println("Error during file operations: " + e.getMessage());
        }
    }

    private static void demonstrateCollections() {
        System.out.println("\n=== Collections Handling ===");
        List<Event> events = Arrays.asList(
            new Event("evt-003", "Morning Consultation"),
            new Event("evt-004", "Evening Consultation")
        );

        String json = JsonUtil.toJson(events);
        System.out.println("Serialized list:");
        System.out.println(json);

        List<Event> readEvents = JsonUtil.fromJson(json, new TypeReference<List<Event>>() {});
        System.out.println("Number of events deserialized: " + readEvents.size());
    }

    private static void demonstrateErrorHandling() {
        System.out.println("\n=== Error Handling ===");
        try {
            JsonUtil.fromJson("{invalid:json}", Event.class);
        } catch (RuntimeException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}