package com.example.Docker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testNoArgConstructor() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User(1, "John", 30);
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("John", user.getName());
        assertEquals(30, user.getAge());
    }

    @Test
    void testGettersAndSetters() {
        User user = new User();
        user.setId(1);
        user.setName("John");
        user.setAge(30);

        assertEquals(1, user.getId());
        assertEquals("John", user.getName());
        assertEquals(30, user.getAge());
    }

    @Test
    void testNameValidation() {
        User user = new User();
        user.setName("John");
        assertEquals("John", user.getName());

        user.setName("");
        assertEquals("", user.getName());
    }

    @Test
    void testAgeValidation() {
        User user = new User();
        user.setAge(30);
        assertEquals(30, user.getAge());

        user.setAge(0);
        assertEquals(0, user.getAge());
    }
}
