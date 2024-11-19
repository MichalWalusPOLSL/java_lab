package com.mycompany.noticemenugui;

import java.util.stream.Stream;
import model.MyThrownException;
import model.Notice;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.*;


/**
 *
 * @author micha
 */
public class NoticeTest {
    
    static Stream<org.junit.jupiter.params.provider.Arguments> provideWrongConstructorArguments() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("", "World", Notice.Type.RENTAL, "Jello"),
                org.junit.jupiter.params.provider.Arguments.of("Foo", "", Notice.Type.SALE, "Zappka"),
                org.junit.jupiter.params.provider.Arguments.of("Java", "JUnit", null, "Monkey"),
                org.junit.jupiter.params.provider.Arguments.of("Java", "JUnit", Notice.Type.SERVICE, ""),
                org.junit.jupiter.params.provider.Arguments.of(null, "World", Notice.Type.RENTAL, "Jello"),
                org.junit.jupiter.params.provider.Arguments.of("Foo", null, Notice.Type.SALE, "Zappka"),
                org.junit.jupiter.params.provider.Arguments.of("Java", "JUnit", Notice.Type.SERVICE, null),
                org.junit.jupiter.params.provider.Arguments.of(null, null, null, null)
                
        );
    }
    
    
    @ParameterizedTest
    @MethodSource("provideWrongConstructorArguments")
    void testNoticeConstructorWrongData(String title, String author, Notice.Type type, String text) {
        assertThrows(MyThrownException.class, () -> new Notice(title, author, type, text), "This should throw MyThrownException");
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> provideCorrectConstructorArguments() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("Apple", "Author1", Notice.Type.RENTAL, "description"),
                org.junit.jupiter.params.provider.Arguments.of("Banana", "Author2", Notice.Type.SALE, "fruit"),
                org.junit.jupiter.params.provider.Arguments.of("Car", "Author3", Notice.Type.SERVICE, "Vehicle"),
                org.junit.jupiter.params.provider.Arguments.of("House", "Author4", Notice.Type.HIRE, "Property details"),
                org.junit.jupiter.params.provider.Arguments.of("Book", "Author5", Notice.Type.SALE, "item"),
                org.junit.jupiter.params.provider.Arguments.of("Chair", "Author6", Notice.Type.RENTAL, "Furniture")
        );
    }

    @ParameterizedTest
    @MethodSource("provideCorrectConstructorArguments")
    void testNoticeConstructorCorrectData(String title, String author, Notice.Type type, String text) {
        assertDoesNotThrow(() -> new Notice(title, author, type, text), "This should throw MyThrownException");
    } 
    
}
