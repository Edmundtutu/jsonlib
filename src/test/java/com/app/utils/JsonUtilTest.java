package com.app.utils;

import com.app.utils.JsonUtil;
import com.app.utils.TestPerson;
import com.app.utils.WriteOptions;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {
    
    @Test
    void testObjectToJson() {
        TestPerson person = new TestPerson("Ed Tutuma", 30);
        String json = JsonUtil.toJson(person);
        assertNotNull(json);
        assertTrue(json.contains("Ed Tutuma"));
        assertTrue(json.contains("30"));
    }

    @Test
    void testJsonToObject() {
        String json = "{\"name\":\"Ed Tutuma\",\"age\":30}";
        TestPerson person = JsonUtil.fromJson(json, TestPerson.class);
        assertNotNull(person);
        assertEquals("Ed Tutuma", person.getName());
        assertEquals(30, person.getAge());
    }

    @Test
    void testWriteToStream() {
        TestPerson person = new TestPerson("Ed Tutuma", 30);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        WriteOptions options = new WriteOptions().setPrettyPrint(true);
        JsonUtil.toJsonFile(outputStream, person, options);
        
        String result = outputStream.toString();
        assertNotNull(result);
        assertTrue(result.contains("Ed Tutuma"));
        assertTrue(result.contains("30"));
    }

    @Test
    void testReadFromStream() {
        String json = "{\"name\":\"Ed Tutuma\",\"age\":30}";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(json.getBytes());
        
        TestPerson person = JsonUtil.fromJsonFile(inputStream, TestPerson.class);
        assertNotNull(person);
        assertEquals("Ed Tutuma", person.getName());
        assertEquals(30, person.getAge());
    }

    @Test
    void testInvalidJson() {
        String invalidJson = "{invalid:json}";
        assertThrows(RuntimeException.class, () -> 
            JsonUtil.fromJson(invalidJson, TestPerson.class)
        );
    }

    @Test
    void testNullChecks() {
        // Test null object in toJson
        assertThrows(IllegalArgumentException.class, () -> 
            JsonUtil.toJson(null)
        );

        // Test null parameters in fromJson
        assertThrows(IllegalArgumentException.class, () -> 
            JsonUtil.fromJson(null, TestPerson.class)
        );
        assertThrows(IllegalArgumentException.class, () -> 
            JsonUtil.fromJson("{}", (Class<Object>) null)
        );

        // Test null parameters in toJsonFile
        assertThrows(IllegalArgumentException.class, () -> 
            JsonUtil.toJsonFile(null, new TestPerson(), null)
        );
        assertThrows(IllegalArgumentException.class, () -> 
            JsonUtil.toJsonFile(new ByteArrayOutputStream(), null, null)
        );

        // Test null parameters in fromJsonFile
        assertThrows(IllegalArgumentException.class, () -> 
            JsonUtil.fromJsonFile(null, TestPerson.class)
        );
        assertThrows(IllegalArgumentException.class, () -> 
            JsonUtil.fromJsonFile(new ByteArrayInputStream("{}".getBytes()), null)
        );
    }
} 