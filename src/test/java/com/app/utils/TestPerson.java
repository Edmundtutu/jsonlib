package com.app.utils;

import java.util.Objects;

public class TestPerson {
    private String name;
    private int age;

    // Default constructor needed for Jackson
    public TestPerson() {}

    public TestPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPerson that = (TestPerson) o;
        return age == that.age && Objects.equals(name, that.name);
    }
} 