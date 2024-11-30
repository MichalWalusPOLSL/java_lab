package com.mycompany.noticemenugui;

import java.util.stream.Stream;
import model.MyThrownException;
import model.Notice;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import model.Type;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.*;


/**
 * Unit tests for the Notice class.
 * This class uses parameterized tests to validate the constructor behavior.
 * 
 * @version 1.0
 * @author Michal Walus
 */
public class NoticeTest {
    
    /**
     * Provides invalid input data sets for the Notice constructor.
     * 
     * @return A stream of arguments containing invalid values for Notice fields.
     */
    static Stream<org.junit.jupiter.params.provider.Arguments> provideWrongConstructorArguments() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("", "World", Type.RENTAL, "Jello"),
                org.junit.jupiter.params.provider.Arguments.of("Foo", "", Type.SALE, "Zappka"),
                org.junit.jupiter.params.provider.Arguments.of("Java", "JUnit", null, "Monkey"),
                org.junit.jupiter.params.provider.Arguments.of("Java", "JUnit", Type.SERVICE, ""),
                org.junit.jupiter.params.provider.Arguments.of(null, "World", Type.RENTAL, "Jello"),
                org.junit.jupiter.params.provider.Arguments.of("Foo", null, Type.SALE, "Zappka"),
                org.junit.jupiter.params.provider.Arguments.of("Java", "JUnit", Type.SERVICE, null),
                org.junit.jupiter.params.provider.Arguments.of(null, null, null, null)
                
        );
    }
    
    /**
     * Tests the Notice constructor with invalid data.
     * Expects MyThrownException to be thrown.
     * 
     * @param title The title of the notice.
     * @param author The author of the notice.
     * @param type The type of the notice.
     * @param text The content of the notice.
     */
    @ParameterizedTest
    @MethodSource("provideWrongConstructorArguments")
    void testNoticeConstructorWrongData(String title, String author, Type type, String text) {
        assertThrows(MyThrownException.class, () -> new Notice(title, author, type, text), "This should throw MyThrownException");
    }

    /**
     * Provides valid input data sets for the Notice constructor.
     * 
     * @return A stream of arguments containing valid values for Notice fields.
     */
    static Stream<org.junit.jupiter.params.provider.Arguments> provideCorrectConstructorArguments() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("Apple", "Author1", Type.RENTAL, "description"),
                org.junit.jupiter.params.provider.Arguments.of("Banana", "Author2", Type.SALE, "fruit"),
                org.junit.jupiter.params.provider.Arguments.of("Car", "Author3", Type.SERVICE, "Vehicle"),
                org.junit.jupiter.params.provider.Arguments.of("House", "Author4", Type.HIRE, "Property details"),
                org.junit.jupiter.params.provider.Arguments.of("Book", "Author5", Type.SALE, "item"),
                org.junit.jupiter.params.provider.Arguments.of("Chair", "Author6", Type.RENTAL, "Furniture")
        );
    }

    /**
     * Tests the Notice constructor with valid data.
     * Expects no exception to be thrown.
     * 
     * @param title The title of the notice.
     * @param author The author of the notice.
     * @param type The type of the notice.
     * @param text The content of the notice.
     */
    @ParameterizedTest
    @MethodSource("provideCorrectConstructorArguments")
    void testNoticeConstructorCorrectData(String title, String author, Type type, String text) {
        assertDoesNotThrow(() -> new Notice(title, author, type, text), "This should not throw MyThrownException");
    } 
    
}
