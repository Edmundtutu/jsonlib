package com.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * A utility class that provides simplified JSON operations using Jackson ObjectMapper.
 * This class offers methods for serialization, deserialization, and JSON manipulation
 * with built-in null checks and exception handling.
 *
 * @author Edmund Tutuma
 * @version 1.0
 * @since 2024-03-19
 */
public class JsonUtil {
    /** Shared ObjectMapper instance for all JSON operations */
    private static final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());

    /**
     * Converts a Java object to its JSON string representation.
     *
     * @param obj the object to be converted to JSON
     * @return a JSON string representation of the object
     * @throws IllegalArgumentException if the input object is null
     * @throws RuntimeException if serialization fails
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Input object cannot be null");
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing object to JSON", e);
        }
    }

    /**
     * Converts a JSON string to a Java object of the specified class.
     *
     * @param <T> the type parameter for the target class
     * @param json the JSON string to deserialize
     * @param clazz the target class to convert the JSON into
     * @return an instance of T containing the deserialized data
     * @throws IllegalArgumentException if either json or clazz is null
     * @throws RuntimeException if deserialization fails
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null) {
            throw new IllegalArgumentException("JSON string cannot be null");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("Target class cannot be null");
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing JSON to object", e);
        }
    }
    

    /**
     * Writes a Java object as JSON to an output stream with optional pretty printing.
     *
     * @param outputStream the stream to write the JSON to
     * @param obj the object to be written as JSON
     * @param options configuration options for JSON writing (can be null)
     * @throws IllegalArgumentException if outputStream or obj is null
     * @throws RuntimeException if writing to the stream fails
     */
    public static void toJsonFile(OutputStream outputStream, Object obj, WriteOptions options) {
        if (outputStream == null) {
            throw new IllegalArgumentException("OutputStream cannot be null");
        }
        if (obj == null) {
            throw new IllegalArgumentException("Input object cannot be null");
        }
        try {
            if(options != null && options.isPrettyPrint()){
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputStream, obj);
            }else{
                objectMapper.writeValue(outputStream, obj);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error writing JSON to stream", e);
        }
    }
    
    /**
     * Reads JSON from an input stream and converts it to a Java object.
     *
     * @param <T> the type parameter for the target class
     * @param inputStream the stream containing JSON data
     * @param clazz the target class to convert the JSON into
     * @return an instance of T containing the deserialized data
     * @throws IllegalArgumentException if either inputStream or clazz is null
     * @throws RuntimeException if reading from the stream fails
     */
    public static <T> T fromJsonFile(InputStream inputStream, Class<T> clazz) {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("Target class cannot be null");
        }
        try {
            return objectMapper.readValue(inputStream, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error reading JSON from stream", e);
        }
    }

    /**
     * Converts a JSON array string to a List of objects of the specified class.
     *
     * @param <T> the type parameter for the list elements
     * @param json the JSON array string to deserialize
     * @param clazz the class type of the list elements
     * @return a List of type T containing the deserialized data
     * @throws RuntimeException if deserialization fails
     */
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing JSON array to object", e);
        }
    }

    /**
     * Converts a JSON string to a Java object using a TypeReference for complex types.
     * Useful for generic types like {@code List<CustomObject>} or {@code Map<String, CustomObject>}.
     *
     * @param <T> the type parameter for the target type
     * @param json the JSON string to deserialize
     * @param typeReference the TypeReference describing the target type
     * @return an instance of T containing the deserialized data
     * @throws RuntimeException if deserialization fails
     */
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing JSON to object", e);
        }
    }

    /**
     * Converts a Java object to a Jackson ObjectNode.
     * Useful for dynamic JSON manipulation.
     *
     * @param obj the object to convert to an ObjectNode
     * @return an ObjectNode representation of the input object
     * @throws RuntimeException if conversion fails
     */
    public static ObjectNode toJsonNode(Object obj) {
        try {
            return objectMapper.valueToTree(obj);
        } catch (Exception e) {
            throw new RuntimeException("Error converting object to JSON node", e);
        }
    }

    /**
     * Converts a Jackson JsonNode to a Java object of the specified class.
     *
     * @param <T> the type parameter for the target class
     * @param node the JsonNode to convert
     * @param clazz the target class to convert the node into
     * @return an instance of T containing the converted data
     * @throws RuntimeException if conversion fails
     */
    public static <T> T fromJsonNode(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON node to object", e);
        }
    }

}
