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
 * Unit tests for the User class.
 * This class validates the functionality of the setName method using parameterized tests.
 * 
 * @version 1.0
 * @author Michal Walus
 */
public class UserTest {

    /** user object defined for tests */
    private User user;
    
    /**
     * Initializes the User object before each test.
     */
    @BeforeEach
    public void setUp() {
        user = new User();
    }
    
    /**
     * Tests the User setName(String) method with valid names.
     * Expects no exceptions and validates that the name is correctly set.
     * 
     * @param name The valid name to set for the user.
     */
    @ParameterizedTest
    @ValueSource(strings = {"JohnDoe", "Alice", "a12345", "Zilog"})
    public void testSetNameValidNames(String name) {
        assertDoesNotThrow(() -> user.setName(name));
        assertEquals(name, user.getName(), "The username should be set to " + name);
    }
    
    /**
     * Tests the User setName(String) method with invalid names.
     * Expects MyThrownException to be thrown with a specific error message.
     * 
     * @param name The invalid name that should cause an exception.
     */
    @ParameterizedTest
    @ValueSource(strings = {"123Name", "_underscore", "8051"})
    public void testSetNameInvalidNames(String name) {
        Exception exception = assertThrows(MyThrownException.class, () -> user.setName(name), "This should throw MyThrownException");
        assertEquals("Given username doesn't start with a letter", exception.getMessage(), "Incorrect exception: " + exception.getMessage());
    }
    
    /**
     * Tests the User setName(String) method with null, empty, or blank names.
     * Expects MyThrownException to be thrown with a specific error message.
     * 
     * @param name The name that should cause an exception due to being null, empty, or blank.
     */
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", " ", "\t", "\n"})
    @ParameterizedTest
    public void testSetNameEmptyName(String name){
        Exception exception = assertThrows(MyThrownException.class, () -> user.setName(name), "This should throw MyThrownException");
        assertEquals("You have to pass your username", exception.getMessage(),  "Incorrect exception: " + exception.getMessage());
    }
  
}