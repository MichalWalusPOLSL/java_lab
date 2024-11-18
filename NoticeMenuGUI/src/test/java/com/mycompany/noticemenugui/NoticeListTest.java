package com.mycompany.noticemenugui;

import model.MyThrownException;
import model.NoticeList;
import model.Notice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author micha
 */
public class NoticeListTest {
    
    private static NoticeList testList;
    
    @BeforeEach
    public void setUpClass() {
        testList = new NoticeList();
        testList.addNotice("Initial Notice", "User1", Notice.Type.OTHER, "Initial content");
        testList.addNotice("Second Notice", "User2", Notice.Type.RENTAL, "Second content");
    }
    
    @AfterEach
    public void clearList(){
        testList.getAll().clear();
    }
    
    @Test
    public void testAddNotice() {
        testList.addNotice("Test Title", "User1", Notice.Type.SALE, "Sample text");
        assertEquals(3, testList.getSize(), "Size should be 3 after adding a notice");
        Notice notice = testList.getOne(2);
        assertEquals("Test Title", notice.getTitle());
        assertEquals("User1", notice.getAuthor());
        assertEquals(Notice.Type.SALE, notice.getType());
        assertEquals("Sample text", notice.getText());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void testDeleteOneCorrect(int toDelete) {
        testList.deleteOne(testList.getOne(toDelete));
        assertEquals(1, testList.getSize(), "Size should be 1 after deletion");
    }
    
    /*
    @ParameterizedTest
    @ValueSource(ints = {1000, 100, 11})
    public void testDeleteOneIncorrect(int toDelete) {
        Exception exception = assertThrows(MyThrownException.class, () -> testList.deleteOne(testList.getOne(toDelete)));
    }*/
    
    
    
    @Test
    public void testGetNoticesByUser() {
        testList.addNotice("Title3", "User1", Notice.Type.SALE, "Content3");

        assertEquals(2, testList.getNoticesByUser("User1").size(), "User1 should have 2 notices");
        assertEquals(1, testList.getNoticesByUser("User2").size(), "User2 should have 1 notice");
        assertTrue(testList.getNoticesByUser("NonExistentUser").isEmpty(), "Non-existent user should have 0 notices");
    }
    
    
    
    //TODO: dodac rekord do modelu

    
}
