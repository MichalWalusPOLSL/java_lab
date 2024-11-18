/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.noticemenugui;

import model.User;
import model.MyThrownException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author micha
 */
public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @ParameterizedTest
    @ValueSource(strings = {"JohnDoe", "Alice", "a12345", "Zilog"})
    public void testSetNameValidNames(String name) {
        assertDoesNotThrow(() -> user.setName(name));
        assertEquals(name, user.getName(), "The username should be set to " + name);
    }
    
    
    @ParameterizedTest
    @ValueSource(strings = {"123Name", "_underscore", "8051"})
    public void testSetNameInvalidNames(String name) {
        Exception exception = assertThrows(MyThrownException.class, () -> user.setName(name), "This should throw MyThrownException");
        assertEquals("Given username doesn't start with a letter", exception.getMessage(), "Incorrect exception: " + exception.getMessage());
    }
    
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", " ", "\t", "\n"})
    @ParameterizedTest
    public void testSetNameEmptyName(String name){
        Exception exception = assertThrows(MyThrownException.class, () -> user.setName(name), "This should throw MyThrownException");
        assertEquals("You have to pass your username", exception.getMessage(),  "Incorrect exception: " + exception.getMessage());
    }
  
}